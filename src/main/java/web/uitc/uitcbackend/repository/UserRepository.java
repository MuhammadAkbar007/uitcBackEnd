package web.uitc.uitcbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.uitc.uitcbackend.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByPhoneNumber(String phoneNumber);

    boolean existsByPhoneNumber(String phoneNumber);

    boolean deleteByPhoneNumber(String phoneNumber);
}
