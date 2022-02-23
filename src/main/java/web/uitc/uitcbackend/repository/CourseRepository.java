package web.uitc.uitcbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.uitc.uitcbackend.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {
    boolean existsByName(String name);
}
