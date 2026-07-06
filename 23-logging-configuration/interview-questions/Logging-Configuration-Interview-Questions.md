# Logging Configuration Interview Questions

## Beginner Level

### Q1. What is Logging Configuration in Spring Boot?

**Answer:**

Logging Configuration allows developers to control how log messages are generated, displayed, and stored in a Spring Boot application. It includes configuring log levels, log files, and log patterns using `application.properties` or `application.yml`.

---

### Q2. Which logging framework does Spring Boot use by default?

**Answer:**

Spring Boot uses:

- **SLF4J (Simple Logging Facade for Java)** – Logging API
- **Logback** – Default Logging Implementation

---

### Q3. Which file is commonly used to configure logging?

**Answer:**

- `application.properties`
- `application.yml`

Example:

```properties
logging.level.root=INFO
```

---

### Q4. How do you change the logging level?

**Answer:**

```properties
logging.level.root=DEBUG
```

or

```properties
logging.level.com.example=TRACE
```

---

### Q5. What are the available logging levels?

**Answer:**

- TRACE
- DEBUG
- INFO
- WARN
- ERROR

---

## Intermediate Level

### Q6. How do you write logs to a file?

**Answer:**

```properties
logging.file.name=logs/application.log
```

Spring Boot automatically creates the log file.

---

### Q7. How do you customize the console log format?

**Answer:**

```properties
logging.pattern.console=%d{yyyy-MM-dd HH:mm:ss} %-5level %logger{36} - %msg%n
```

---

### Q8. What is the difference between Console Logging and File Logging?

| Console Logging | File Logging |
|-----------------|--------------|
| Displayed on console | Stored in a file |
| Temporary | Persistent |
| Useful during development | Useful in production |

---

### Q9. Can different packages have different logging levels?

**Answer:**

Yes.

Example:

```properties
logging.level.root=INFO

logging.level.com.example.service=DEBUG

logging.level.com.example.controller=TRACE
```

---

### Q10. Why is File Logging important in Production?

**Answer:**

Because console logs may disappear after the application stops, while log files remain available for troubleshooting and auditing.

---

## Advanced Level

### Q11. What happens if the Root Logger is INFO and a package logger is DEBUG?

**Answer:**

The specified package uses DEBUG logging, while the rest of the application uses INFO.

---

### Q12. Why shouldn't TRACE be enabled in Production?

**Answer:**

TRACE generates a large amount of log data, which can:

- Reduce performance
- Increase storage usage
- Make important logs difficult to find

---

### Q13. What information should never be logged?

**Answer:**

Never log:

- Passwords
- JWT Tokens
- API Keys
- Credit Card Numbers
- OTPs
- Sensitive Personal Information

---

### Q14. How can logging improve debugging?

**Answer:**

Logging records application events, making it easier to identify where failures occur without reproducing the issue.

---

### Q15. Which logging level is commonly used for business events?

**Answer:**

INFO

Example:

```java
logger.info("Order placed successfully");
```

---

# Scenario-Based Questions

### Q16. A payment transaction fails in production. Which logging level should be used?

**Answer:**

ERROR

---

### Q17. You want to investigate why an API is slow in the development environment. Which logging level would you enable?

**Answer:**

DEBUG or TRACE

---

### Q18. A third-party API is responding slowly, but your application is still working. Which logging level should be used?

**Answer:**

WARN

---

### Q19. How would you configure different logging levels for different packages?

**Answer:**

```properties
logging.level.com.example.controller=INFO

logging.level.com.example.service=DEBUG

logging.level.com.example.repository=ERROR
```

---

### Q20. Explain the complete logging flow in Spring Boot.

**Answer:**

```
Application

↓

Logger

↓

SLF4J

↓

Logback

↓

Console / Log File
```
