package com.example.exceptionhandling.service;

import com.example.exceptionhandling.exception.UserNotFoundException;
import com.example.exceptionhandling.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public User getUserById(Long id) {

        if (id == 1L) {

            return new User(
                    1L,
                    "Sudheer",
                    "sudheer@example.com"
            );

        }

        throw new UserNotFoundException(
                "User with ID " + id + " not found.");

    }

}
