# Global Exception Handling in Spring Boot (@RestControllerAdvice)

## Prerequisites

Before learning Global Exception Handling, you should understand:

- Spring Boot Basics
- REST APIs
- Java Exceptions
- @ExceptionHandler
- HTTP Status Codes

---

# Learning Objectives

By the end of this guide, you will understand:

- What Global Exception Handling is
- Why @RestControllerAdvice is needed
- Difference between @ExceptionHandler and @RestControllerAdvice
- Creating centralized exception handling
- Returning consistent error responses
- Best Practices
- Interview Questions

---

# Introduction

As Spring Boot applications grow, they often contain multiple REST controllers.

If each controller contains its own exception handling logic, the application becomes difficult to maintain and results in duplicated code.

Spring Boot solves this problem using **@RestControllerAdvice**, which centralizes exception handling for the entire application.

---

# What is @RestControllerAdvice?

`@RestControllerAdvice` is a specialized Spring annotation that provides **global exception handling** for all REST controllers.

Instead of writing `@ExceptionHandler` methods inside every controller, we place them in a single class annotated with `@RestControllerAdvice`.

---

# Why Do We Need Global Exception Handling?

Imagine an application with multiple APIs:

```text
UserController

ProductController

OrderController

PaymentController
```

Each controller can throw exceptions.

Without Global Exception Handling:

- Duplicate code
- Difficult maintenance
- Inconsistent error responses

With `@RestControllerAdvice`:

- Centralized exception handling
- Reusable code
- Consistent API responses

---

# Without @RestControllerAdvice

```text
UserController
      │
      ├── @ExceptionHandler

ProductController
      │
      ├── @ExceptionHandler

OrderController
      │
      ├── @ExceptionHandler
```

Every controller contains duplicate exception handling logic.

---

# With @RestControllerAdvice

```text
UserController
      │

ProductController
      │

OrderController
      │
      ▼

@RestControllerAdvice
      │
      ▼

GlobalExceptionHandler
```

One class handles exceptions for the entire application.

---

# Example

```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFound(
            UserNotFoundException ex){

        ErrorResponse response = new ErrorResponse(
                404,
                ex.getMessage(),
                LocalDateTime.now());

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);

    }

}
```

---

# How It Works

```text
Client Request
        │
        ▼
DispatcherServlet
        │
        ▼
Controller
        │
        ▼
Service
        │
        ▼
Exception Thrown
        │
        ▼
@RestControllerAdvice
        │
        ▼
GlobalExceptionHandler
        │
        ▼
Custom JSON Response
```

---

# Real-World Scenario

Consider an E-Commerce application.

```
User API

Product API

Order API

Payment API
```

If any API throws:

- UserNotFoundException
- ProductNotFoundException
- OrderNotFoundException

The `GlobalExceptionHandler` automatically creates a consistent error response.

Clients receive the same response format regardless of which controller throws the exception.

---

# @ExceptionHandler vs @RestControllerAdvice

| @ExceptionHandler | @RestControllerAdvice |
|-------------------|-----------------------|
| Controller-specific | Application-wide |
| Used inside a controller | Used in a separate class |
| Handles one controller | Handles all controllers |
| More duplication | Centralized approach |

---

# Advantages

- Centralized Exception Handling
- Better Maintainability
- Cleaner Controllers
- Consistent Error Responses
- Less Code Duplication

---

# Common Mistakes

## 1. Creating Exception Handlers in Every Controller

Avoid duplicate code.

Use `@RestControllerAdvice`.

---

## 2. Returning Different Error Formats

Maintain a single `ErrorResponse` model.

---

## 3. Exposing Stack Traces

Never expose:

- SQL Queries
- Server Paths
- Stack Traces
- Internal Exceptions

---

# Best Practices

✅ Use one GlobalExceptionHandler.

✅ Create custom exceptions.

✅ Return meaningful HTTP status codes.

✅ Keep error responses consistent.

✅ Log exceptions before returning responses.

---

# Interview Questions

### Q1. What is @RestControllerAdvice?

### Q2. Difference between @ControllerAdvice and @RestControllerAdvice?

### Q3. Difference between @ExceptionHandler and @RestControllerAdvice?

### Q4. Why do enterprise applications use Global Exception Handling?

### Q5. Can multiple exception types be handled in one class?

### Q6. What is the purpose of ErrorResponse?

### Q7. Why shouldn't stack traces be exposed?

### Q8. Explain the complete exception handling flow.

---

# Practice Exercise

Build a User Management REST API.

Create:

- UserNotFoundException
- ProductNotFoundException
- OrderNotFoundException

Handle all exceptions using one `GlobalExceptionHandler`.

Return a common `ErrorResponse`.

---

# Key Takeaways

- @RestControllerAdvice provides global exception handling.
- Eliminates duplicate exception handling code.
- Returns consistent JSON responses.
- Improves maintainability.
- Widely used in enterprise Spring Boot applications.

---

# Summary

`@RestControllerAdvice` is one of the most important annotations in Spring Boot.

It centralizes exception handling across the application, resulting in cleaner controllers, reusable logic, and consistent API responses.

Every production-ready Spring Boot application should use Global Exception Handling.

---

# What's Next?

📌 Bean Validation using @Valid
