# Exception Handling in Spring Boot (@ExceptionHandler)

## Prerequisites

Before learning Exception Handling, you should understand:

- Spring Boot Basics
- REST APIs
- Controllers
- HTTP Status Codes
- Java Exceptions

---

# Learning Objectives

By the end of this guide, you will understand:

- What Exception Handling is
- Why Exception Handling is important
- How `@ExceptionHandler` works
- Creating Custom Exceptions
- Returning Custom Error Responses
- Best Practices
- Interview Questions

---

# Introduction

Exception Handling is an essential feature in every Spring Boot application.

Unexpected situations such as invalid requests, missing resources, or business rule violations should be handled gracefully instead of crashing the application.

Spring Boot provides the `@ExceptionHandler` annotation to intercept exceptions and return meaningful HTTP responses.

---

# What is Exception Handling?

Exception Handling is the process of detecting and handling runtime errors in a controlled manner.

Instead of exposing stack traces to clients, applications return meaningful error messages.

---

# Why Do We Need Exception Handling?

Without Exception Handling:

```text
java.lang.RuntimeException
User Not Found
```

Client receives:

```
HTTP 500 Internal Server Error
```

Not useful.

---

With Exception Handling:

```json
{
  "status":404,
  "message":"User not found",
  "timestamp":"2026-07-08T10:30:15"
}
```

Now the client clearly understands what happened.

---

# Custom Exception

```java
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String message){
        super(message);
    }

}
```

---

# Handling Exception

```java
@ExceptionHandler(UserNotFoundException.class)
public ResponseEntity<String> handleUserNotFound(
        UserNotFoundException ex){

    return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ex.getMessage());

}
```

---

# How Exception Handling Works

```text
Client Request
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
@ExceptionHandler
      │
      ▼
Custom Error Response
```

---

# Real-World Scenario

Imagine an Online Banking Application.

Customer transfers ₹10,000.

Available Balance = ₹2,000.

The application throws:

```
InsufficientBalanceException
```

Instead of returning an internal server error, the API responds:

```json
{
   "status":400,
   "message":"Insufficient account balance."
}
```

This provides a better user experience and keeps the API consistent.

---

# Advantages

- Prevents application crashes
- Returns meaningful responses
- Improves API consistency
- Better user experience
- Easier debugging

---

# Common Mistakes

## 1. Catching Every Exception

Wrong

```java
catch(Exception e){
    e.printStackTrace();
}
```

Correct

Create custom exceptions and let Spring handle them.

---

## 2. Returning Generic Errors

Avoid returning only:

```
Something went wrong.
```

Return meaningful error messages.

---

## 3. Exposing Internal Details

Never expose:

- Stack traces
- Database errors
- SQL queries
- Server paths

to API clients.

---

# Best Practices

✅ Create custom exceptions.

✅ Return meaningful HTTP status codes.

✅ Use `@ExceptionHandler`.

✅ Keep error responses consistent.

✅ Log exceptions before returning responses.

---

# Interview Questions

### Q1. What is Exception Handling?

### Q2. Why do we use `@ExceptionHandler`?

### Q3. Difference between checked and unchecked exceptions?

### Q4. What happens if an exception is not handled?

### Q5. Can multiple `@ExceptionHandler` methods exist?

### Q6. Which HTTP status should be returned for "Resource Not Found"?

### Q7. Why shouldn't stack traces be exposed?

### Q8. When should custom exceptions be created?

---

# Practice Exercise

Create a User Management REST API.

Implement:

- UserNotFoundException
- InvalidUserException
- Custom Error Response
- HTTP 404
- HTTP 400

Verify responses using Postman.

---

# Key Takeaways

- Exception Handling prevents application crashes.
- `@ExceptionHandler` intercepts controller exceptions.
- Custom exceptions improve readability.
- Meaningful responses improve API quality.
- Every enterprise REST API uses proper Exception Handling.

---

# Summary

Exception Handling is one of the most important concepts in Spring Boot REST APIs.

It allows applications to return meaningful responses instead of exposing internal implementation details.

Using `@ExceptionHandler` with custom exceptions results in cleaner, more maintainable, and production-ready applications.

---

# What's Next?

📌 Global Exception Handling using `@ControllerAdvice`
