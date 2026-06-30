
 @Value Annotation in Spring Boot

Prerequisites

Before learning  @Value, you should understand:

- Spring IoC Container
- Spring Beans
- Dependency Injection
- @Autowired
- @Primary
- application.properties
- Spring Boot Configuration

---

Learning Objectives

By the end of this guide, you will understand:

- What @Value is
- Why @Value is used
- How Spring injects configuration values
- How to use @Value with different data types
- Default values in @Value
- Best practices and interview questions

---

# Introduction

One of the biggest advantages of Spring Boot is its ability to separate configuration from application code.

Instead of hardcoding values like database URLs, application names, API keys, or server ports inside Java classes, Spring Boot stores them in configuration files such as:

- application.properties
- application.yml

The @Value annotation allows Spring to read these values and inject them directly into Spring Beans.

This makes applications more flexible, maintainable, and environment-independent.

---

Why Do We Need @Value?

Imagine writing code like this:
@Component
public class AppInfo {

    private String appName = "Spring Boot From Scratch";

}


