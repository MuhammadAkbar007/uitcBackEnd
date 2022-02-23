package web.uitc.uitcbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.uitc.uitcbackend.entity.SystemRole;

import java.util.Optional;

public interface SystemRoleRepository extends JpaRepository<SystemRole, Long> {
    Optional<SystemRole> findByName(String name);
}
