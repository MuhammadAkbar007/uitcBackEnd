package web.uitc.uitcbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import web.uitc.uitcbackend.payload.ApiResponse;
import web.uitc.uitcbackend.payload.CourseDto;
import web.uitc.uitcbackend.service.CourseService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    @Autowired
    CourseService courseService;

    @PreAuthorize("hasAuthority('ADD_COURSE')")
    @PostMapping
    public ResponseEntity<?> addCourse(@Valid @RequestBody CourseDto courseDto) {
        ApiResponse apiResponse = courseService.add(courseDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse.getMessage());
    }

    @PreAuthorize("hasAuthority('VIEW_COURSES')")
    @GetMapping
    public ResponseEntity<?> getCourses() {
        ApiResponse apiResponse = courseService.getAll();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 204).body(apiResponse.getObject());
    }

    @PreAuthorize("hasAuthority('VIEW_COURSES')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getOneCourse(@PathVariable Long id) {
        ApiResponse apiResponse = courseService.getOne(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse.getObject());
    }

    @PreAuthorize("hasAuthority('EDIT_COURSE')")
    @PutMapping("/{id}")
    public ResponseEntity<?> editCourse(@PathVariable Long id, @Valid @RequestBody CourseDto courseDto) {
        ApiResponse apiResponse = courseService.edit(id, courseDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 202 : 409).body(apiResponse.getMessage());
    }

    @PreAuthorize("hasAuthority('DELETE_COURSE')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long id) {
        ApiResponse apiResponse = courseService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 204 : 409).body(apiResponse.getMessage());
    }
}