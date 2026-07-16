package com.example.orm.controller;

import com.example.orm.model.User;
import com.example.orm.service.UserService;
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
            @RequestBody User user) {

        userService.saveUser(user);

        return ResponseEntity.ok(
                "User saved successfully (ORM Example).");

    }

}
