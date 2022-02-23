package web.uitc.uitcbackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.uitc.uitcbackend.entity.Course;
import web.uitc.uitcbackend.payload.ApiResponse;
import web.uitc.uitcbackend.payload.CourseDto;
import web.uitc.uitcbackend.repository.CourseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    CourseRepository courseRepository;

    public ApiResponse add(CourseDto courseDto) {
        if (courseRepository.existsByName(courseDto.getName()))
            return new ApiResponse("Such course already exists !", false);
        courseRepository.save(new Course(courseDto.getName(), courseDto.getDescription()));
        return new ApiResponse("Course successfully created !", true);
    }

    public ApiResponse getAll() {
        List<Course> courseList = courseRepository.findAll();
        if (courseList.isEmpty()) return new ApiResponse("empty", false, "No courses found !");
        return new ApiResponse("have", true, courseList);
    }

    public ApiResponse getOne(Long id) {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if (!optionalCourse.isPresent()) return new ApiResponse("not found", false, "Course by this id not found !");
        return new ApiResponse("found", true, optionalCourse.get());
    }

    public ApiResponse edit(Long id, CourseDto courseDto) {
        Optional<Course> optionalCourse = courseRepository.findById(id);
        if (!optionalCourse.isPresent()) return new ApiResponse("Course by this id not found !", false);
        Course course = optionalCourse.get();
        course.setName(courseDto.getName());
        course.setDescription(course.getDescription());
        courseRepository.save(course);
        return new ApiResponse("Course successfully edited !", true);
    }

    public ApiResponse delete(Long id) {
        try {
            courseRepository.deleteById(id);
            return new ApiResponse("Successfully deleted !", true);
        } catch (Exception e) {
            return new ApiResponse("Error while deleting !", false);
        }
    }
}
