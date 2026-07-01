# @ConfigurationProperties in Spring Boot

## Prerequisites

Before learning `@ConfigurationProperties`, you should understand:

- Spring IoC Container
- Spring Beans
- Dependency Injection
- @Value Annotation
- application.properties
- Spring Boot Configuration

---

# Learning Objectives

By the end of this guide, you will understand:

- What `@ConfigurationProperties` is
- Why it is used
- How Spring Boot binds configuration properties
- Difference between `@Value` and `@ConfigurationProperties`
- Best practices and interview questions

---

# Introduction

Spring Boot applications often contain many configuration values such as:

- Database Settings
- API URLs
- JWT Configuration
- Email Configuration
- Cloud Configuration

Using multiple `@Value` annotations works well for a few properties, but it becomes difficult to maintain as the application grows.

Spring Boot provides **@ConfigurationProperties** to bind a group of related properties directly into a Java class.

This results in cleaner, strongly typed, and more maintainable configuration.

---

# Why Do We Need @ConfigurationProperties?

Suppose your application contains the following properties.

```properties
app.name=Spring Boot From Scratch
app.version=1.0.0
app.author=Sudheer
app.description=Learning Spring Boot
```

Using `@Value`:

```java
@Value("${app.name}")
private String name;

@Value("${app.version}")
private String version;

@Value("${app.author}")
private String author;

@Value("${app.description}")
private String description;
```

As the number of properties increases, this approach becomes repetitive.

A better solution is to group all related properties into one configuration class.

---

# Using @ConfigurationProperties

### application.properties

```properties
app.name=Spring Boot From Scratch
app.version=1.0.0
app.author=Sudheer
app.description=Learning Spring Boot
```

### Configuration Class

```java
@Component
@ConfigurationProperties(prefix = "app")
public class AppProperties {

    private String name;
    private String version;
    private String author;
    private String description;

    // Getters and Setters

}
```

Spring automatically maps all properties starting with **app** into this class.

---

# How @ConfigurationProperties Works

```text
Application Starts
        │
        ▼
Reads application.properties
        │
        ▼
Looks for prefix "app"
        │
        ▼
Maps Properties
        │
        ▼
Creates AppProperties Bean
        │
        ▼
Application Ready
```

---

# Architecture

```text
application.properties
        │
        ▼
app.name
app.version
app.author
app.description
        │
        ▼
@ConfigurationProperties(prefix="app")
        │
        ▼
AppProperties Bean
        │
        ▼
Inject Anywhere in Application
```

---

# Injecting the Configuration Bean

```java
@Service
public class AppService {

    private final AppProperties properties;

    public AppService(AppProperties properties) {
        this.properties = properties;
    }

}
```

Spring automatically injects the configuration Bean.

---

# Real-World Example

Consider an E-Commerce application.

```properties
payment.url=https://payment.example.com

payment.timeout=30

payment.api-key=abcd1234

payment.currency=INR
```

Instead of four `@Value` annotations, create:

```java
@Component
@ConfigurationProperties(prefix = "payment")
public class PaymentProperties {

    private String url;

    private int timeout;

    private String apiKey;

    private String currency;

}
```

Now all payment-related settings are available through a single Bean.

---

# @Value vs @ConfigurationProperties

| @Value | @ConfigurationProperties |
|----------|--------------------------|
| Injects one property | Binds multiple related properties |
| More repetitive | Cleaner and organized |
| Best for small configurations | Best for enterprise applications |
| Less scalable | Highly scalable |
| Suitable for a few values | Suitable for grouped configuration |

---

# Advantages

- Cleaner code
- Strongly typed configuration
- Easy to maintain
- Reduces repetitive code
- Better for enterprise applications
- Supports nested properties
- Easy to test

---

# Common Mistakes

## 1. Forgetting @Component

Without registering the class as a Spring Bean, Spring cannot create it.

---

## 2. Wrong Prefix

Wrong

```java
@ConfigurationProperties(prefix="application")
```

Correct

```java
@ConfigurationProperties(prefix="app")
```

The prefix must match the property names.

---

## 3. Missing Getters and Setters

Spring cannot bind the values without getters and setters (unless using constructor binding or records).

---

# Interview Questions

### Q1. What is @ConfigurationProperties?

It binds a group of related configuration properties into a Java object.

---

### Q2. Why do we use @ConfigurationProperties?

To manage multiple related properties in a clean and scalable way.

---

### Q3. What is the difference between @Value and @ConfigurationProperties?

`@Value` injects individual properties.

`@ConfigurationProperties` binds multiple related properties into one Bean.

---

### Q4. Which is better for enterprise applications?

`@ConfigurationProperties`

---

### Q5. Can nested properties be mapped?

Yes.

It supports nested objects as well.

---

### Q6. Can we use Constructor Injection with @ConfigurationProperties?

Yes.

It works perfectly with Constructor Injection.

---

# Best Practices

✅ Use `@Value` for one or two properties.

✅ Use `@ConfigurationProperties` for grouped configuration.

✅ Keep related properties under one prefix.

✅ Use meaningful property names.

✅ Avoid hardcoding configuration values.

---

# Key Takeaways

- `@ConfigurationProperties` groups related properties.
- It produces cleaner and more maintainable code.
- It is strongly typed.
- It reduces repetitive `@Value` annotations.
- It is the preferred approach for enterprise Spring Boot applications.

---

# Summary

The `@ConfigurationProperties` annotation is the recommended way to manage large sets of configuration properties in Spring Boot. By binding related properties into a dedicated configuration class, it keeps applications organized, type-safe, and easy to maintain.

While `@Value` is suitable for injecting a small number of individual values, `@ConfigurationProperties` is the better choice for enterprise applications where configuration grows over time.

Understanding this annotation is essential because almost every production Spring Boot application uses grouped configuration for databases, security, cloud services, messaging systems, and external APIs.

---

# What's Next?

📌 **Next Topic:**

**Spring Profiles (@Profile) – Managing Multiple Environments**


