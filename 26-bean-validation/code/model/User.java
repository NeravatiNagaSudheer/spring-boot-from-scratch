package com.example.validation.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class User {

    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Please enter a valid email")
    private String email;

    @Min(value = 18, message = "Age must be at least 18")
    private int age;

    public User() {
    }

    public User(String name,
                String email,
                int age) {

        this.name = name;
        this.email = email;
        this.age = age;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
