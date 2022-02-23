package web.uitc.uitcbackend.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import web.uitc.uitcbackend.entity.SystemRole;
import web.uitc.uitcbackend.entity.User;
import web.uitc.uitcbackend.entity.enums.Permission;
import web.uitc.uitcbackend.repository.SystemRoleRepository;
import web.uitc.uitcbackend.repository.UserRepository;
import web.uitc.uitcbackend.utils.AppConstants;

import java.util.Arrays;

import static web.uitc.uitcbackend.entity.enums.Permission.*;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;
    @Autowired
    SystemRoleRepository systemRoleRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Value("${spring.datasource.initialization-mode}")
    private String initialMode;

    @Override
    public void run(String... args) {
        if (initialMode.equals("always")) {
            Permission[] permissions = Permission.values();
            SystemRole admin = systemRoleRepository.save(new SystemRole(
                    AppConstants.ADMIN, Arrays.asList(permissions), "Sistema egasi"));
            SystemRole user = systemRoleRepository.save(new SystemRole(
                    AppConstants.USER, Arrays.asList(VIEW_TEACHERS, VIEW_COURSES, VIEW_ATTACHMENTS), "Tizim foydalanuvchisi"));

            userRepository.save(new User("adminjon", "adminov", "+998945060749",
                    passwordEncoder.encode("rootAdmin123"), admin, true));
            userRepository.save(new User("userjon", "userov", "+998938785313",
                    passwordEncoder.encode("rootUser123"), user, true));
        }
    }
}
