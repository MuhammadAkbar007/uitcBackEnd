package web.uitc.uitcbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import web.uitc.uitcbackend.payload.ApiResponse;
import web.uitc.uitcbackend.payload.UserDto;
import web.uitc.uitcbackend.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @PreAuthorize("hasAuthority('ADD_USER')")
    @PostMapping
    public ResponseEntity<?> addUser(@Valid @RequestBody UserDto userDto) {
        ApiResponse apiResponse = userService.add(userDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse.getMessage());
    }

    @PreAuthorize("hasAuthority('VIEW_USERS')")
    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        ApiResponse apiResponse = userService.getAll();
        return ResponseEntity.ok(apiResponse.getObject());
    }

    @PreAuthorize("hasAuthority('VIEW_USERS')")
    @GetMapping("/{phoneNumber}")
    public ResponseEntity<?> getByPhoneNumber(@PathVariable String phoneNumber) {
        ApiResponse apiResponse = userService.getByPhoneNumber(phoneNumber);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 204).body(apiResponse.getObject());
    }

    @PreAuthorize("hasAuthority('EDIT_USER')")
    @PutMapping("/{phoneNumber}")
    public ResponseEntity<?> editByPhoneNumber(@PathVariable String phoneNumber, @Valid @RequestBody UserDto userDto) {
        ApiResponse apiResponse = userService.editByPhoneNumber(phoneNumber, userDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 202 : 409).body(apiResponse.getMessage());
    }

    @PreAuthorize("hasAuthority('DELETE_USER')")
    @DeleteMapping("/{phoneNumber}")
    public ResponseEntity<?> deleteByPhoneNumber(@PathVariable String phoneNumber) {
        ApiResponse apiResponse = userService.deleteByPhoneNumber(phoneNumber);
        return ResponseEntity.status(apiResponse.isSuccess() ? 204 : 409).body(apiResponse.getMessage());
    }
}
