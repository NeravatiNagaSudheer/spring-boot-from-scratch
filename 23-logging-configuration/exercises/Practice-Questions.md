# Practice Questions – Logging Configuration

## Beginner

### Exercise 1

Create a Spring Boot project.

Configure:

```properties
logging.level.root=INFO
```

Observe which log messages are displayed.

---

### Exercise 2

Change the logging level to:

```properties
logging.level.root=DEBUG
```

Compare the output with INFO.

---

### Exercise 3

Enable TRACE logging.

Observe how many additional messages are displayed.

---

### Exercise 4

Configure logs to be written into a file.

```properties
logging.file.name=logs/application.log
```

Run the application and verify that the file is created.

---

## Intermediate

### Exercise 5

Create a REST API:

```
GET /users
```

Generate logs for:

- INFO
- DEBUG
- WARN
- ERROR

Observe the console output.

---

### Exercise 6

Configure different logging levels.

```properties
logging.level.com.example.controller=INFO

logging.level.com.example.service=DEBUG
```

Verify that each package logs according to its configured level.

---

### Exercise 7

Customize the log format.

```properties
logging.pattern.console=%d{HH:mm:ss} %-5level %logger - %msg%n
```

Observe the new console output.

---

### Exercise 8

Generate an exception intentionally.

Example:

```java
int result = 10 / 0;
```

Log the exception using:

```java
logger.error("Exception occurred", ex);
```

---

## Advanced

### Exercise 9

Create a User Management API.

Log:

- API Request Received
- User Validation
- Database Call
- Successful Response
- Exception

Use the correct logging level for each.

---

### Exercise 10

Create separate logging levels for:

- Controller
- Service
- Repository

Verify the output.

---

### Exercise 11

Store logs in a file.

Restart the application.

Verify that previous logs are still available.

---

### Exercise 12

Create meaningful log messages.

Instead of:

```java
logger.info("Done");
```

Use:

```java
logger.info("User with ID {} registered successfully", userId);
```

---

# Challenge Project

Build a Student Management REST API.

Implement logging for:

- Student Registration
- Fetch Student
- Update Student
- Delete Student
- Invalid Requests
- Database Errors

Requirements:

- Console Logging
- File Logging
- Custom Log Pattern
- INFO, DEBUG, WARN, and ERROR Levels
- Meaningful Log Messages

---

# Expected Learning Outcomes

After completing these exercises, you should be able to:

- Configure logging levels
- Write logs to files
- Customize log patterns
- Use appropriate logging levels
- Debug applications using logs
- Apply logging best practices in Spring Boot projects
