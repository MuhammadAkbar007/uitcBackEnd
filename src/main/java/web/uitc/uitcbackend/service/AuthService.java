package web.uitc.uitcbackend.service;

import antlr.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import web.uitc.uitcbackend.entity.SystemRole;
import web.uitc.uitcbackend.entity.User;
import web.uitc.uitcbackend.payload.ApiResponse;
import web.uitc.uitcbackend.payload.LoginDto;
import web.uitc.uitcbackend.payload.RegisterDTO;
import web.uitc.uitcbackend.payload.TokenResponse;
import web.uitc.uitcbackend.repository.SystemRoleRepository;
import web.uitc.uitcbackend.repository.UserRepository;
import web.uitc.uitcbackend.security.JwtProvider;
import web.uitc.uitcbackend.utils.AppConstants;

import java.util.Optional;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    SystemRoleRepository systemRoleRepository;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtProvider jwtProvider;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber).orElseThrow(() -> new UsernameNotFoundException(phoneNumber));
    }

    public ApiResponse registerUser(RegisterDTO registerDTO) {
        if (userRepository.existsByPhoneNumber(registerDTO.getPhoneNumber()))
            return new ApiResponse("Bunday user mavjud", false);
        Optional<SystemRole> optionalSystemRole = systemRoleRepository.findByName(AppConstants.USER);
        if (!optionalSystemRole.isPresent()) return new ApiResponse("Xatolik", false);
        User user = new User(registerDTO.getFirstName(), registerDTO.getLastName(), registerDTO.getPhoneNumber(),
                passwordEncoder.encode(registerDTO.getPassword()), optionalSystemRole.get(), true);
        userRepository.save(user);
        return new ApiResponse("User saqlandi", true);
    }

    public TokenResponse login(LoginDto loginDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getPhoneNumber(), loginDto.getPassword()));
            User user = (User) authentication.getPrincipal();
            String token = jwtProvider.generateToken(user.getUsername(), user.getSystemRole());
            return new TokenResponse( true, token);
        } catch (BadCredentialsException e) {
            return new TokenResponse(false, "Parol yoki login xato !");
        }
    }
}
