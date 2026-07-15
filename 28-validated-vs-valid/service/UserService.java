package com.example.validation.service;

import com.example.validation.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public void createUser(User user) {

        System.out.println("Saving User...");

        System.out.println(user.getName());
        System.out.println(user.getEmail());
        System.out.println(user.getAge());
        System.out.println(user.getMobile());

    }

    public String getUserById(Long id) {

        return "User Found with Id : " + id;

    }

}
