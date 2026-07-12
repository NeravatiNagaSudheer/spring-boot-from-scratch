# Bean Validation in Spring Boot (@Valid)

## Prerequisites

Before learning Bean Validation, you should understand:

- Spring Boot Basics
- REST APIs
- Java Objects (POJOs)
- Global Exception Handling
- HTTP Status Codes

---

# Learning Objectives

By the end of this guide, you will understand:

- What Bean Validation is
- Why Bean Validation is important
- How @Valid works
- Common Validation Annotations
- Validation Flow
- Best Practices
- Interview Questions

---

# Introduction

Validating incoming request data is one of the most important aspects of every REST API.

Without validation, invalid or incomplete data may reach the service layer and eventually be stored in the database.

Spring Boot provides Bean Validation using the `@Valid` annotation together with Jakarta Validation constraints.

---

# What is Bean Validation?

Bean Validation is the process of validating object fields before business logic is executed.

Instead of writing multiple `if` statements, Spring automatically validates incoming data.

---

# Why Do We Need Bean Validation?

Imagine a User Registration API.

Without Validation:

- Empty Name
- Invalid Email
- Negative Age

These invalid values could be stored in the database.

With Bean Validation:

- Invalid requests are rejected.
- Clients receive meaningful validation messages.
- Business logic only receives valid data.

---

# Example

```java
public class User {

    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Invalid Email")
    private String email;

    @Min(value = 18, message = "Age must be at least 18")
    private int age;

}
```

Controller

```java
@PostMapping
public String createUser(
        @Valid @RequestBody User user){

    return "User Created Successfully";

}
```

---

# How Validation Works

```text
Client Request
        │
        ▼
@RequestBody
        │
        ▼
@Valid
        │
        ▼
Validation Rules
        │
 ┌──────┴──────┐
 │             │
Valid      Invalid
 │             │
 ▼             ▼
Service    Validation Error
```

---

# Common Validation Annotations

| Annotation | Description |
|------------|-------------|
| @NotNull | Value cannot be null |
| @NotBlank | String cannot be null or blank |
| @NotEmpty | Collection/String cannot be empty |
| @Size | Minimum and maximum length |
| @Email | Valid email format |
| @Pattern | Matches a regular expression |
| @Min | Minimum numeric value |
| @Max | Maximum numeric value |
| @Positive | Positive numbers only |
| @Negative | Negative numbers only |

---

# Real-World Scenario

Imagine an E-Commerce application.

User Registration Request

```json
{
   "name":"",
   "email":"abc@",
   "age":15
}
```

Spring automatically validates the request.

Response

```json
{
   "status":400,
   "message":"Validation Failed"
}
```

The invalid request never reaches the service layer.

---

# Validation Flow

```text
Client
      │
      ▼
Controller
      │
      ▼
@Valid
      │
      ▼
Validation Engine
      │
 ┌────┴────┐
 │         │
Pass     Fail
 │         │
 ▼         ▼
Service   Exception
           │
           ▼
GlobalExceptionHandler
           │
           ▼
JSON Response
```

---

# Advantages

- Prevents Invalid Data
- Cleaner Code
- Better API Design
- Automatic Validation
- Improved User Experience

---

# Common Mistakes

## 1. Forgetting @Valid

Without `@Valid`, validation annotations are ignored.

---

## 2. Performing Validation Manually

Avoid multiple `if` statements.

Use Bean Validation annotations.

---

## 3. Missing Validation Dependency

Remember to include:

```xml
spring-boot-starter-validation
```

---

# Best Practices

✅ Validate request DTOs.

✅ Use meaningful validation messages.

✅ Keep validation separate from business logic.

✅ Handle validation errors using Global Exception Handling.

---

# Interview Questions

### Q1. What is Bean Validation?

### Q2. What is @Valid?

### Q3. Difference between @NotNull and @NotBlank?

### Q4. What happens when validation fails?

### Q5. Which exception is thrown by @Valid?

### Q6. Why do we use DTOs with validation?

### Q7. Can validation be customized?

### Q8. Explain the validation flow.

---

# Practice Exercise

Create a User Registration API.

Validate:

- Name
- Email
- Age
- Phone Number

Return validation errors using GlobalExceptionHandler.

---

# Key Takeaways

- Bean Validation prevents invalid data.
- @Valid triggers validation automatically.
- Validation annotations simplify input validation.
- Global Exception Handling provides consistent validation responses.
- Bean Validation is used in almost every enterprise Spring Boot REST API.

---

# Summary

Bean Validation is a core feature of Spring Boot that ensures only valid data enters the application.

By combining `@Valid`, validation annotations, and Global Exception Handling, developers can build secure, maintainable, and production-ready REST APIs.

---

# What's Next?

📌 **Validation Annotations in Depth (@NotNull, @NotBlank, @Email, @Size, @Pattern)**
