package com.example.validation.controller;

import com.example.validation.model.User;
import com.example.validation.service.UserService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Validated
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Demonstrates @Valid
    @PostMapping
    public ResponseEntity<String> createUser(
            @Valid @RequestBody User user) {

        userService.createUser(user);

        return ResponseEntity.ok("User created successfully.");

    }

    // Demonstrates @Validated
    @GetMapping("/{id}")
    public ResponseEntity<String> getUserById(
            @PathVariable
            @Min(value = 1, message = "Id must be greater than zero")
            Long id) {

        return ResponseEntity.ok(
                userService.getUserById(id));

    }

}
