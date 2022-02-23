package web.uitc.uitcbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import web.uitc.uitcbackend.payload.ApiResponse;
import web.uitc.uitcbackend.payload.TeacherDto;
import web.uitc.uitcbackend.service.TeacherService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {

    @Autowired
    TeacherService teacherService;

    @PreAuthorize("hasAuthority('ADD_TEACHER')")
    @PostMapping
    public ResponseEntity<?> addTeacher(@Valid @RequestBody TeacherDto teacherDto) {
        ApiResponse apiResponse = teacherService.add(teacherDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse.getMessage());
    }

//    @PreAuthorize("hasAuthority('VIEW_TEACHERS')")
//    @GetMapping
//    public ResponseEntity<?> getTeachers() {
//        ApiResponse apiResponse = teacherService.getAll();
//        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 204).body(apiResponse.getObject());
//    }

    @PreAuthorize("hasAuthority('EDIT_TEACHER')")
    @PutMapping("/{id}")
    public ResponseEntity<?> editTeachers(@Valid @RequestBody TeacherDto teacherDto, @PathVariable Long id) {
        ApiResponse apiResponse = teacherService.editTeacher(teacherDto, id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 202 : 404).body(apiResponse.getMessage());
    }

    @PreAuthorize("hasAuthority('DELETE_TEACHER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTeachers(@PathVariable Long id) {
        ApiResponse apiResponse = teacherService.deleteTeacher(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 204 : 409).body(apiResponse.getMessage());
    }
}