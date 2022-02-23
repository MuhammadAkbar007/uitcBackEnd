package web.uitc.uitcbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import web.uitc.uitcbackend.entity.SystemRole;
import web.uitc.uitcbackend.entity.User;
import web.uitc.uitcbackend.payload.ApiResponse;
import web.uitc.uitcbackend.payload.UserDto;
import web.uitc.uitcbackend.repository.SystemRoleRepository;
import web.uitc.uitcbackend.repository.UserRepository;
import web.uitc.uitcbackend.utils.AppConstants;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    SystemRoleRepository systemRoleRepository;

    public ApiResponse add(UserDto userDto) {
        if (userRepository.existsByPhoneNumber(userDto.getPhoneNumber()))
            return new ApiResponse("Such user already exists !", false);
        Optional<SystemRole> optionalSystemRole = systemRoleRepository.findByName(AppConstants.ADMIN);
        if (!optionalSystemRole.isPresent()) return new ApiResponse("Error while creating !", false);
        userRepository.save(new User(
                userDto.getFirstName(),
                userDto.getLastName(),
                userDto.getPhoneNumber(),
                passwordEncoder.encode(userDto.getPassword()),
                optionalSystemRole.get(), true));
        return new ApiResponse("Admin User successfully created !", true);
    }

    public ApiResponse getAll() {
        return new ApiResponse("All users", true, userRepository.findAll());
    }

    public ApiResponse getByPhoneNumber(String phoneNumber) {
        Optional<User> optionalUser = userRepository.findByPhoneNumber(phoneNumber);
        if (!optionalUser.isPresent())
            return new ApiResponse("not found", false, "User by " + phoneNumber + " not found !");
        return new ApiResponse("found", true, optionalUser.get());
    }

    public ApiResponse editByPhoneNumber(String phoneNumber, UserDto userDto) {
        Optional<User> optionalUser = userRepository.findByPhoneNumber(phoneNumber);
        if (!optionalUser.isPresent())
            return new ApiResponse("User by " + phoneNumber + " not found !", false);
        User user = optionalUser.get();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPassword(userDto.getPassword());
        user.setPhoneNumber(userDto.getPhoneNumber());
        userRepository.save(user);
        return new ApiResponse("User successfully edited !", true);
    }

    public ApiResponse deleteByPhoneNumber(String phoneNumber) {
        if (userRepository.deleteByPhoneNumber(phoneNumber))
            return new ApiResponse("User successfully deleted !", true);
        return new ApiResponse("Error while deleting !", false);
    }
}
