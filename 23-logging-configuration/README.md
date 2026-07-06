# Logging Configuration in Spring Boot (Console Logs, File Logs & Custom Log Patterns)

## Prerequisites

Before learning Logging Configuration, you should understand:

- Spring Boot Basics
- Logging in Spring Boot
- SLF4J
- Logback
- Logging Levels

---

# Learning Objectives

By the end of this guide, you will understand:

- What Logging Configuration is
- Why Logging Configuration is important
- Configure Logging Levels
- Configure File Logging
- Configure Console Logging
- Customize Log Patterns
- Best Practices
- Interview Questions

---

# Introduction

Logging is one of the most important features in enterprise applications.

However, simply generating logs is not enough.

Developers must decide:

- Where logs should be stored
- Which messages should be logged
- How log messages should appear
- Which packages should generate logs

Spring Boot provides built-in support for configuring logging through `application.properties` or `application.yml`.

---

# Why Do We Need Logging Configuration?

Imagine an application handling thousands of requests every minute.

Without configuration:

- Logs are difficult to read
- Important messages are mixed with debug information
- Logs disappear when the application restarts
- Troubleshooting production issues becomes difficult

Logging Configuration solves these problems.

---

# Logging Architecture

```text
Spring Boot Application
          │
          ▼
Reads application.properties
          │
          ▼
SLF4J
          │
          ▼
Logback
          │
          ▼
Console / Log File
```

---

# Configure Logging Levels

application.properties

```properties
logging.level.root=INFO

logging.level.com.example=DEBUG
```

Explanation

- Root logger prints INFO, WARN and ERROR.
- Package `com.example` prints DEBUG and above.

---

# Configure Log File

```properties
logging.file.name=logs/application.log
```

Spring Boot automatically creates

```text
logs
│
└── application.log
```

Now every log is stored in the file.

---

# Configure Log Pattern

```properties
logging.pattern.console=%d{yyyy-MM-dd HH:mm:ss} %-5level %logger{36} - %msg%n
```

Console Output

```text
2026-07-06 11:20:30 INFO UserService - User Created Successfully
```

---

# How Logging Configuration Works

```text
Application Starts
        │
        ▼
Read application.properties
        │
        ▼
Configure Logging
        │
        ▼
Generate Logs
        │
        ▼
Console
        │
        ▼
Log File
```

---

# Real-World Scenario

Imagine an Online Banking application.

```text
Customer Transfers Money
        │
        ▼
TransactionController
        │
        ▼
TransactionService
        │
        ▼
Payment Gateway
```

Generated Logs

```text
INFO  Transaction Started

DEBUG Validating Account

WARN  Payment Gateway Response Slow

ERROR Transaction Failed
```

These logs are written to

```text
logs/application.log
```

When a customer reports an issue, developers can inspect the log file to identify the exact cause.

---

# Console Logging vs File Logging

| Console Logging | File Logging |
|-----------------|--------------|
| Visible during application execution | Stored permanently |
| Useful during development | Useful in production |
| Lost after application stops | Available for later analysis |

---

# Advantages

- Better monitoring
- Easier debugging
- Production support
- Centralized logging
- Cleaner console output

---

# Common Mistakes

## 1. Using Only Console Logging

Console logs disappear after the application stops.

Always configure file logging for production.

---

## 2. Logging Too Much

Enabling TRACE everywhere creates very large log files.

---

## 3. Logging Sensitive Data

Never log:

- Passwords
- JWT Tokens
- OTPs
- Credit Card Numbers
- API Keys

---

# Best Practices

✅ Configure separate logging levels for different packages.

✅ Store production logs in files.

✅ Use meaningful log messages.

✅ Rotate log files in production.

✅ Never log sensitive information.

---

# Interview Questions

### Q1. What is Logging Configuration?

### Q2. Which file is used to configure logging?

### Q3. How do you write logs into a file?

### Q4. How do you change the logging level?

### Q5. Difference between Console Logging and File Logging?

### Q6. How do you customize log patterns?

### Q7. Which logging implementation does Spring Boot use by default?

### Q8. Why should production applications use file logging?

---

# Practice Exercise

Create a REST API.

Configure:

- INFO logs
- DEBUG logs
- ERROR logs
- Console Logging
- File Logging

Trigger the API and verify that logs appear both in the console and in `logs/application.log`.

---

# Key Takeaways

- Logging Configuration controls how logs are generated and stored.
- Spring Boot allows configuration using `application.properties`.
- Logs can be written to the console and to files.
- Custom log patterns improve readability.
- Proper logging configuration simplifies debugging and production support.

---

# Summary

Logging Configuration is a fundamental part of every enterprise Spring Boot application. It allows developers to control logging behavior, store logs for future analysis, and improve monitoring and troubleshooting.

By configuring logging levels, log files, and log patterns correctly, applications become easier to debug, maintain, and support in production.

---

# What's Next?

📌 **Exception Handling in Spring Boot (@ExceptionHandler)**
