package com.example.logging.controller;

import com.example.logging.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public String getUsers() {

        service.getUsers();

        return "Users Retrieved Successfully";

    }

}
