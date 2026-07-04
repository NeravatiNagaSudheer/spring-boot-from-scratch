
# Logging in Spring Boot (SLF4J & Logback)

## Prerequisites

Before learning Spring Boot Logging, you should understand:

- Spring Boot Basics
- Spring Beans
- Spring Boot Starter Dependencies
- REST APIs
- Exception Handling (Basic Knowledge)

---

# Learning Objectives

By the end of this guide, you will understand:

- What Logging is
- Why Logging is important
- What SLF4J is
- What Logback is
- Logging Levels
- How to configure logging in Spring Boot
- Best practices and interview questions

---

# Introduction

Logging is one of the most important features of any enterprise application.

It helps developers monitor application behavior, debug issues, troubleshoot production problems, and record important application events.

Instead of using `System.out.println()`, Spring Boot provides a professional logging framework out of the box.

By default, Spring Boot uses:

- **SLF4J (Simple Logging Facade for Java)** – Logging API
- **Logback** – Default Logging Implementation

---

# Why Do We Need Logging?

Imagine an application serving thousands of users.

Questions developers often ask include:

- Which API failed?
- What exception occurred?
- Which user made the request?
- How long did the request take?
- When did the error happen?

Without logging, finding answers to these questions becomes very difficult.

Logging records these events so developers can quickly diagnose and resolve issues.

---

# Logging Architecture

```text
Spring Boot Application
          │
          ▼
SLF4J (Logging API)
          │
          ▼
Logback (Implementation)
          │
          ▼
Console / Log File / External Logging System
```

---

# Creating a Logger

```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class UserController {

    private static final Logger logger =
            LoggerFactory.getLogger(UserController.class);

}
```

Spring Boot recommends creating one logger per class.

---

# Logging Example

```java
@GetMapping("/users")
public String getUsers() {

    logger.info("Fetching all users");

    logger.debug("Calling User Service");

    logger.warn("Low disk space");

    logger.error("Database connection failed");

    return "Users Retrieved";

}
```

---

# Logging Levels

Spring Boot supports five primary logging levels.

```text
TRACE
   │
DEBUG
   │
INFO
   │
WARN
   │
ERROR
```

### TRACE

Very detailed information.

Mostly used while debugging framework internals.

---

### DEBUG

Useful during development.

Example:

```java
logger.debug("User ID = {}", id);
```

---

### INFO

General application events.

Example:

```java
logger.info("Application Started");
```

---

### WARN

Unexpected situations that do not stop the application.

Example:

```java
logger.warn("Disk space is running low");
```

---

### ERROR

Application failures and exceptions.

Example:

```java
logger.error("Database connection failed", exception);
```

---

# Configuring Logging

Spring Boot allows configuration through `application.properties`.

```properties
logging.level.root=INFO

logging.level.com.example=DEBUG
```

---

# Logging to a File

```properties
logging.file.name=logs/application.log
```

Spring Boot automatically creates the log file.

---

# Custom Log Pattern

```properties
logging.pattern.console=%d{yyyy-MM-dd HH:mm:ss} %-5level %logger{36} - %msg%n
```

Example Output:

```text
2026-07-04 10:30:15 INFO UserController - Fetching all users
```

---

# Real-World Example

Consider an E-Commerce application.

```text
User Places Order
        │
        ▼
OrderController
        │
        ▼
OrderService
        │
        ▼
PaymentService
        │
        ▼
Database
```

Logs:

```text
INFO  Order received

DEBUG Validating payment

INFO  Payment successful

INFO  Order saved

ERROR Email service unavailable
```

Even if email sending fails, the logs clearly identify where the issue occurred.

---

# Why Logging is Better than System.out.println()

| Logging | System.out.println() |
|----------|----------------------|
| Configurable log levels | No log levels |
| Supports log files | Console only |
| Better performance | Less efficient |
| Used in enterprise applications | Suitable only for simple debugging |
| Structured logging | Plain text output |

---

# Advantages of Logging

- Easier debugging
- Production monitoring
- Error tracking
- Performance analysis
- Audit trail
- Better application maintenance

---

# Common Mistakes

## 1. Using System.out.println()

Avoid:

```java
System.out.println("User Created");
```

Prefer:

```java
logger.info("User Created");
```

---

## 2. Logging Sensitive Information

Never log:

- Passwords
- OTPs
- Credit Card Numbers
- JWT Tokens
- API Secrets

---

## 3. Logging Everything as ERROR

Use the correct logging level.

Not every message should be logged as an error.

---

# Interview Questions

### Q1. What is Logging?

Logging is the process of recording application events for monitoring, debugging, and troubleshooting.

---

### Q2. Which logging framework does Spring Boot use by default?

Spring Boot uses:

- SLF4J
- Logback

---

### Q3. What is SLF4J?

SLF4J is a logging API (facade).

---

### Q4. What is Logback?

Logback is the default logging implementation used by Spring Boot.

---

### Q5. Name the logging levels.

- TRACE
- DEBUG
- INFO
- WARN
- ERROR

---

### Q6. Why should we avoid System.out.println()?

Because it lacks log levels, structured output, and production-level capabilities.

---

# Best Practices

✅ Create one Logger per class.

✅ Use appropriate log levels.

✅ Never log sensitive information.

✅ Store logs in files for production.

✅ Write meaningful log messages.

---

# Key Takeaways

- Logging is essential in enterprise applications.
- Spring Boot uses SLF4J and Logback by default.
- Logging helps monitor, debug, and troubleshoot applications.
- Use different log levels appropriately.
- Avoid using `System.out.println()` in production code.

---

# Summary

Logging is a fundamental feature of every Spring Boot application. It enables developers to understand application behavior, monitor production systems, and diagnose issues efficiently.

Spring Boot simplifies logging by integrating SLF4J and Logback out of the box. By using the correct logging levels and following best practices, developers can build applications that are easier to maintain and troubleshoot.

Mastering logging is an essential skill for every Java Backend Developer because it is used in almost every enterprise application.

---

# What's Next?

📌 **Next Topic:**

**Spring Boot Logging Levels – TRACE, DEBUG, INFO, WARN & ERROR (In Depth)**

---


