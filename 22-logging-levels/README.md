
# Logging Levels in Spring Boot (TRACE, DEBUG, INFO, WARN & ERROR)

## Prerequisites

Before learning Logging Levels, you should understand:

- Spring Boot Basics
- Logging in Spring Boot
- SLF4J
- Logback

---

# Learning Objectives

By the end of this guide, you will understand:

- What Logging Levels are
- Why Logging Levels are important
- Different Logging Levels in Spring Boot
- When to use each Logging Level
- Best practices
- Common mistakes
- Interview questions

---

# Introduction

Logging is an essential part of every Spring Boot application.

However, not every log message has the same importance.

Some messages help developers during debugging, while others record business events or application failures.

To solve this problem, Spring Boot provides different Logging Levels.

These levels help categorize log messages based on their severity.

---

# What are Logging Levels?

Logging Levels define the priority of log messages.

Instead of printing everything, developers can choose which level should be displayed.

Spring Boot supports five primary logging levels.

```text
Most Detailed
      │
      ▼
TRACE
  │
DEBUG
  │
INFO
  │
WARN
  │
ERROR
      ▲
      │
Most Critical
```

---

# Why Do We Need Logging Levels?

Imagine an application serving thousands of users every minute.

Without Logging Levels:

- Every message appears in the console.
- Important errors get lost.
- Debugging becomes difficult.

Logging Levels allow developers to filter messages based on importance.

---

# Logging Levels Explained

## TRACE

Used for highly detailed information.

Mostly used while debugging framework internals.

Example:

```java
logger.trace("Entering fetchUsers() method");
```

---

## DEBUG

Useful while developing applications.

```java
logger.debug("Fetching users from database");
```

---

## INFO

Used for normal business events.

```java
logger.info("User logged in successfully");
```

---

## WARN

Used when something unexpected happens but the application can continue.

```java
logger.warn("Disk space is running low");
```

---

## ERROR

Used when an exception or application failure occurs.

```java
logger.error("Database connection failed", exception);
```

---

# How Logging Levels Work

```text
Application Starts
        │
        ▼
Controller
        │
        ▼
Service
        │
        ▼
Logger
        │
        ▼
Compare Logging Level
        │
        ▼
Display Matching Logs
```

---

# Real-World Scenario

Imagine an E-Commerce application.

```text
Customer Places Order
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

Generated Logs

```text
INFO  → Order placed successfully

DEBUG → Fetching customer details

WARN  → Payment processing is slow

ERROR → Payment gateway unavailable

TRACE → Entering PaymentService.processPayment()
```

---

# Development vs Production

| Development | Production |
|-------------|------------|
| TRACE | ❌ Usually Disabled |
| DEBUG | ❌ Usually Disabled |
| INFO | ✅ Enabled |
| WARN | ✅ Enabled |
| ERROR | ✅ Enabled |

During development, developers enable TRACE and DEBUG.

In production, applications generally log INFO, WARN, and ERROR.

---

# Configuring Logging Levels

application.properties

```properties
logging.level.root=INFO

logging.level.com.example=DEBUG
```

Enable TRACE

```properties
logging.level.com.example=TRACE
```

---

# Console Output

```text
INFO  User logged in successfully

DEBUG Fetching user details

WARN  Low disk space

ERROR Database connection failed

TRACE Entering UserService
```

---

# Advantages

- Better Debugging
- Easier Monitoring
- Cleaner Logs
- Better Production Support
- Easy Troubleshooting

---

# Common Mistakes

## 1. Logging Everything as ERROR

Wrong

Every message is logged as ERROR.

Correct

Use the appropriate Logging Level.

---

## 2. Enabling TRACE in Production

TRACE generates a huge amount of logs.

It should normally be enabled only while debugging.

---

## 3. Logging Sensitive Information

Never log:

- Passwords
- JWT Tokens
- Credit Card Numbers
- API Secrets
- OTPs

---

# Best Practices

✅ Use TRACE for internal debugging.

✅ Use DEBUG during development.

✅ Use INFO for business events.

✅ Use WARN for recoverable issues.

✅ Use ERROR for failures and exceptions.

---

# Interview Questions

### Q1. What are Logging Levels?

### Q2. Which Logging Levels are available in Spring Boot?

### Q3. Which Logging Levels are commonly used in Production?

### Q4. Difference between DEBUG and TRACE?

### Q5. Difference between WARN and ERROR?

### Q6. Which Logging Level should be used for business events?

### Q7. Why shouldn't TRACE be enabled in Production?

### Q8. How do you configure Logging Levels?

---

# Practice Exercise

Build a User Management API.

Log:

- User Login → INFO

- Fetch User → DEBUG

- Invalid Request → WARN

- Database Exception → ERROR

- Method Entry → TRACE

Observe how changing the Logging Level affects the output.

---

# Key Takeaways

- Logging Levels categorize log messages by severity.
- TRACE provides the most detailed information.
- DEBUG is useful during development.
- INFO records normal application events.
- WARN indicates recoverable issues.
- ERROR records failures and exceptions.
- Proper Logging Levels make applications easier to debug and maintain.

---

# Summary

Logging Levels help developers organize application logs based on their importance.

By using the appropriate level, applications become easier to monitor, debug, and troubleshoot.

Spring Boot provides five primary Logging Levels—TRACE, DEBUG, INFO, WARN, and ERROR—which are widely used in enterprise applications.

Understanding when and where to use each level is an essential skill for every Java Backend Developer.

---

# What's Next?

📌 **Spring Boot Logging Configuration – Console Logs, File Logs & Custom Log Patterns**
