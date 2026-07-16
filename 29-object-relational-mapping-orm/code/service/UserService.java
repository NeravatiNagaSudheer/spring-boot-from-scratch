package com.example.orm.service;

import com.example.orm.model.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public void saveUser(User user) {

        /*
         * This is only a demonstration.
         *
         * In JDBC we would manually:
         * 1. Open Connection
         * 2. Create PreparedStatement
         * 3. Execute SQL
         * 4. Close Resources
         *
         * In ORM we simply work with objects.
         */

        System.out.println("Saving User...");

        System.out.println("Id    : " + user.getId());
        System.out.println("Name  : " + user.getName());
        System.out.println("Email : " + user.getEmail());

    }

}
