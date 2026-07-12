package com.example.validation.controller;

import com.example.validation.model.User;
import com.example.validation.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String> createUser(
            @Valid @RequestBody User user) {

        userService.saveUser(user);

        return ResponseEntity.ok("User Created Successfully");

    }

}
