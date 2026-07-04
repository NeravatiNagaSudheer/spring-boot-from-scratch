package com.example.logging.controller;

import com.example.logging.service.LoggingExample;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private final LoggingExample loggingExample;

    public UserController(LoggingExample loggingExample) {
        this.loggingExample = loggingExample;
    }

    @GetMapping("/users")
    public String getUsers() {

        loggingExample.fetchUsers();

        return "Users Retrieved Successfully";

    }

}

