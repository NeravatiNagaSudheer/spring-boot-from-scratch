package com.example.exceptionhandling.service;

import com.example.exceptionhandling.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public String getUserById(Long id) {

        if (id != 1) {

            throw new UserNotFoundException(
                    "User with ID " + id + " not found.");

        }

        return "Welcome Sudheer";

    }

}
