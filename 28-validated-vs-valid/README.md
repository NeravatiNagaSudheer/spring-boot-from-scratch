# @Validated vs @Valid in Spring Boot

## Prerequisites

Before learning `@Validated`, you should understand:

- Spring Boot Basics
- REST APIs
- Bean Validation
- `@Valid`
- Common Validation Annotations (`@NotNull`, `@NotBlank`, `@Email`, etc.)

---

# Learning Objectives

By the end of this guide, you will understand:

- What `@Valid` is
- What `@Validated` is
- Differences between `@Valid` and `@Validated`
- When to use each annotation
- Validation Groups
- Best Practices
- Interview Questions

---

# Introduction

Validation is one of the most important aspects of any Spring Boot application.

Spring provides two commonly used annotations for validation:

- `@Valid`
- `@Validated`

Although they appear similar, they have different capabilities and are designed for different use cases.

Understanding when to use each annotation is essential for building robust enterprise applications.

---

# What is @Valid?

`@Valid` is part of the **Jakarta Bean Validation** specification.

It validates an entire object before the request reaches the business logic.

Example:

```java
@PostMapping("/users")
public ResponseEntity<String> createUser(
        @Valid @RequestBody User user) {

    return ResponseEntity.ok("User Created");

}
```

When the request is received, Spring automatically validates all fields inside the `User` object.

---

# What is @Validated?

`@Validated` is a Spring Framework annotation.

It supports everything `@Valid` does and additionally provides support for:

- Validation Groups
- Method Parameter Validation

Example:

```java
@RestController
@Validated
public class UserController {

    @GetMapping("/{id}")
    public String getUser(
            @Min(1)
            @PathVariable Long id){

        return "User Found";

    }

}
```

Here, Spring validates the path variable before executing the method.

---

# Key Differences

| Feature | @Valid | @Validated |
|----------|--------|------------|
| Package | Jakarta Validation | Spring Framework |
| Object Validation | ✅ | ✅ |
| Method Parameter Validation | ❌ | ✅ |
| Validation Groups | ❌ | ✅ |
| Common Usage | Request Body | Controllers & Services |

---

# Validation Flow

```text
Client Request
        │
        ▼
Controller
        │
        ▼
@Valid / @Validated
        │
        ▼
Validation Engine
        │
 ┌──────┴──────┐
 │             │
Valid      Invalid
 │             │
 ▼             ▼
Business   Validation Exception
Logic             │
                  ▼
        GlobalExceptionHandler
                  │
                  ▼
           JSON Response
```

---

# Real-World Scenario

Imagine a **Digital Banking Application**.

### Customer Registration

The customer submits:

```json
{
   "name":"Sudheer",
   "email":"sudheer@gmail.com",
   "age":25
}
```

The request body is validated using:

```java
@Valid
```

---

### Account Lookup

The customer requests:

```text
GET /accounts/1001
```

The account ID is validated using:

```java
@Validated
```

This prevents invalid request parameters from reaching the service layer.

---

# Advantages of @Valid

- Simple to use
- Standard Bean Validation
- Ideal for RequestBody validation
- Automatically validates DTOs

---

# Advantages of @Validated

- Supports Validation Groups
- Supports Method Parameter Validation
- Spring-specific features
- Better suited for enterprise applications

---

# Common Mistakes

## 1. Using @Valid for Method Parameters

```java
@GetMapping("/{id}")
public String getUser(
        @Valid @PathVariable Long id){
}
```

❌ Not Recommended

Use:

```java
@RestController
@Validated
public class UserController {
}
```

---

## 2. Thinking Both are Identical

Although they perform validation, `@Validated` provides additional capabilities such as Validation Groups and parameter validation.

---

# Best Practices

✅ Use `@Valid` for validating request bodies.

✅ Use `@Validated` for validating method parameters.

✅ Use Validation Groups only when required.

✅ Combine validation with `@RestControllerAdvice`.

---

# Interview Questions

### Q1. What is `@Valid`?

### Q2. What is `@Validated`?

### Q3. What is the difference between `@Valid` and `@Validated`?

### Q4. Which annotation supports Validation Groups?

### Q5. Can `@Validated` validate method parameters?

### Q6. Which annotation is commonly used with `@RequestBody`?

### Q7. Is `@Validated` a replacement for `@Valid`?

### Q8. Which annotation belongs to Jakarta Bean Validation?

---

# Practice Exercise

Create a User API.

Validate:

- Request Body using `@Valid`
- Path Variable using `@Validated`
- Request Parameter using `@Validated`

Return validation errors using `GlobalExceptionHandler`.

---

# Key Takeaways

- `@Valid` validates objects.
- `@Validated` extends validation capabilities in Spring.
- Use `@Valid` for request body validation.
- Use `@Validated` for method parameter validation and Validation Groups.
- Both annotations are important in enterprise Spring Boot applications.

---

# Summary

Both `@Valid` and `@Validated` play a vital role in request validation.

While `@Valid` is sufficient for validating request bodies, `@Validated` provides additional Spring-specific features such as method parameter validation and Validation Groups.

Understanding the differences helps developers choose the appropriate validation approach and build cleaner, more reliable REST APIs.

---

# What's Next?

📌 **Spring Data JPA – Introduction & Why We Need It**

---
