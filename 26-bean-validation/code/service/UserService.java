package com.example.validation.service;

import com.example.validation.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public void saveUser(User user) {

        // Simulate saving the user
        System.out.println("Saving User...");

        System.out.println("Name  : " + user.getName());
        System.out.println("Email : " + user.getEmail());
        System.out.println("Age   : " + user.getAge());

    }

}
