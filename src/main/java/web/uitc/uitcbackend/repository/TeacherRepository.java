package web.uitc.uitcbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.uitc.uitcbackend.entity.Teacher;

import java.util.Optional;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    boolean existsByFirstNameAndLastNameAndStack(String firstName, String lastName, String stack);
//    Optional<Teacher> existsByFirstNameAndLastNameAndStack(String firstName, String lastName, String stack);
}
