# Common Validation Annotations in Spring Boot

## Prerequisites

Before learning validation annotations, you should understand:

- Spring Boot Basics
- REST APIs
- Bean Validation
- @Valid Annotation
- Java POJOs

---

# Learning Objectives

By the end of this guide, you will understand:

- Why validation annotations are important
- Difference between @NotNull, @NotEmpty and @NotBlank
- How to validate Strings, Collections and Numbers
- Commonly used validation annotations
- Best practices
- Interview Questions

---

# Introduction

Validating user input is an essential part of every Spring Boot application.

Instead of writing multiple `if` statements to validate incoming requests, Spring Boot provides built-in validation annotations through Jakarta Bean Validation.

These annotations help developers write clean, maintainable, and reliable code.

---

# Why Do We Need Validation Annotations?

Imagine a User Registration API.

A client sends:

```json
{
   "name":"",
   "email":"abc",
   "age":15,
   "mobile":"123"
}
```

Without validation:

- Invalid data reaches the service layer.
- Incorrect data may be stored in the database.
- Business logic becomes cluttered with validation checks.

With validation annotations:

- Invalid requests are rejected automatically.
- Users receive meaningful error messages.
- Business logic remains clean.

---

# Common Validation Annotations

## 1. @NotNull

Ensures the value is not `null`.

```java
@NotNull
private String name;
```

Suitable for any object type.

---

## 2. @NotBlank

Used for String values.

Rejects:

- null
- ""
- "   " (only spaces)

```java
@NotBlank(message = "Name is required")
private String name;
```

Recommended for user input fields.

---

## 3. @NotEmpty

Used for:

- String
- List
- Set
- Map
- Arrays

Rejects:

- null
- Empty values

```java
@NotEmpty
private List<String> skills;
```

---

## 4. @Size

Validates minimum and maximum length.

```java
@Size(min = 3, max = 20)
private String username;
```

Useful for:

- Username
- Password
- Lists
- Arrays

---

## 5. @Email

Validates email format.

```java
@Email
private String email;
```

---

## 6. @Pattern

Validates using a Regular Expression.

```java
@Pattern(
    regexp = "^[0-9]{10}$",
    message = "Invalid Mobile Number"
)
private String mobile;
```

Useful for:

- Mobile Numbers
- Password Rules
- PAN Numbers
- ZIP Codes

---

## 7. @Min

Specifies the minimum allowed value.

```java
@Min(18)
private int age;
```

---

## 8. @Max

Specifies the maximum allowed value.

```java
@Max(60)
private int age;
```

---

# Difference Between @NotNull, @NotEmpty and @NotBlank

| Annotation | Allows Null | Allows Empty | Allows Blank Spaces |
|------------|-------------|--------------|---------------------|
| @NotNull | ❌ | ✅ | ✅ |
| @NotEmpty | ❌ | ❌ | ✅ |
| @NotBlank | ❌ | ❌ | ❌ |

---

# Validation Flow

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
Validation Annotations
        │
 ┌──────┴──────┐
 │             │
Valid      Invalid
 │             │
 ▼             ▼
Service   MethodArgumentNotValidException
                │
                ▼
GlobalExceptionHandler
                │
                ▼
JSON Response
```

---

# Real-World Scenario

Imagine an E-Commerce application.

Customer Registration Request:

```json
{
   "name":"",
   "email":"abc",
   "age":15,
   "mobile":"123"
}
```

Spring automatically validates the request.

Response:

```json
{
   "status":400,
   "message":"Validation Failed",
   "errors":{
      "name":"Name is required",
      "email":"Please enter a valid email",
      "age":"Age must be at least 18",
      "mobile":"Invalid Mobile Number"
   }
}
```

The invalid request never reaches the service layer.

---

# Advantages

- Prevents invalid data
- Cleaner business logic
- Automatic validation
- Better user experience
- Consistent API responses

---

# Common Mistakes

## 1. Using @NotNull for String fields

Prefer:

```java
@NotBlank
```

for user-entered Strings.

---

## 2. Forgetting @Valid

Without `@Valid`, validation annotations will not execute.

---

## 3. Writing Manual Validation

Avoid:

```java
if(name == null){
}
```

Use validation annotations instead.

---

# Best Practices

✅ Use `@NotBlank` for String fields.

✅ Use `@Email` for email validation.

✅ Use `@Pattern` for custom formats.

✅ Keep validation in DTO classes.

✅ Handle validation errors using `@RestControllerAdvice`.

---

# Interview Questions

### Q1. What is Bean Validation?

### Q2. What is the difference between @NotNull, @NotEmpty and @NotBlank?

### Q3. What does @Email validate?

### Q4. What is @Pattern used for?

### Q5. Which annotation validates string length?

### Q6. What exception is thrown when validation fails?

### Q7. Why is @Valid required?

### Q8. Can validation annotations be used on collections?

---

# Practice Exercise

Create a User Registration API.

Validate:

- Name
- Email
- Age
- Mobile Number
- Password

Return validation errors using `GlobalExceptionHandler`.

---

# Key Takeaways

- Validation annotations simplify request validation.
- Different annotations solve different validation problems.
- Use the appropriate annotation based on the field type.
- Combine validation annotations with `@Valid` and `@RestControllerAdvice`.
- Bean Validation is widely used in enterprise Spring Boot applications.

---

# Summary

Spring Boot's validation annotations provide a simple yet powerful way to ensure that only valid data enters your application.

By using annotations such as `@NotBlank`, `@Email`, `@Pattern`, and `@Size`, developers can eliminate repetitive validation logic, improve code readability, and build more reliable REST APIs.

Mastering these annotations is essential for developing production-ready Spring Boot applications.

---

# What's Next?

📌 **@Validated vs @Valid – Understanding the Differences**

---


