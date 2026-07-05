package com.example.logging.controller;

import com.example.logging.service.LoggingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final LoggingService loggingService;

    public UserController(LoggingService loggingService) {
        this.loggingService = loggingService;
    }

    @GetMapping("/users")
    public String getUsers() {

        loggingService.fetchUsers();

        return "Users Retrieved Successfully";
    }
}
