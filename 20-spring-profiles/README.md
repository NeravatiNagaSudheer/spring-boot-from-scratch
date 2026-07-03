
# Spring Profiles (@Profile) in Spring Boot

## Prerequisites

Before learning Spring Profiles, you should understand:

- Spring IoC Container
- Spring Beans
- Dependency Injection
- application.properties
- @Value
- @ConfigurationProperties

---

# Learning Objectives

By the end of this guide, you will understand:

- What Spring Profiles are
- Why Spring Profiles are needed
- How Spring Boot manages multiple environments
- How to use `@Profile`
- Different profile configuration files
- Best practices and interview questions

---

# Introduction

In real-world software development, an application usually runs in multiple environments.

For example:

- Development (dev)
- Testing (test)
- User Acceptance Testing (UAT)
- Production (prod)

Each environment requires different configurations such as:

- Database URL
- API Keys
- Logging Level
- External Service URLs

Instead of modifying the source code every time we move between environments, Spring Boot provides **Profiles**.

A Profile allows Spring Boot to load environment-specific Beans and configuration automatically.

---

# Why Do We Need Spring Profiles?

Imagine an application running in three environments.

```text
Development
      │
      ▼
Local PostgreSQL
      │
      ▼
Testing
      │
      ▼
Shared Test Database
      │
      ▼
Production
      │
      ▼
Cloud Database
```

Every environment has different configuration values.

Without Profiles, developers would need to change the configuration manually before every deployment.

Profiles eliminate this problem.

---

# Profile Configuration Files

Spring Boot supports separate configuration files.

```text
src/main/resources
│
├── application.properties
├── application-dev.properties
├── application-test.properties
└── application-prod.properties
```

Each file contains configuration specific to one environment.

---

# Activating a Profile

### application.properties

```properties
spring.profiles.active=dev
```

Spring Boot automatically loads:

```text
application-dev.properties
```

Similarly,

```properties
spring.profiles.active=test
```

loads

```text
application-test.properties
```

---

# Example

## Development Configuration

```java
@Configuration
@Profile("dev")
public class DevConfiguration {

}
```

---

## Production Configuration

```java
@Configuration
@Profile("prod")
public class ProductionConfiguration {

}
```

Only the Bean matching the active profile is created.

---

# How Spring Profiles Work

```text
Application Starts
        │
        ▼
Reads Active Profile
        │
        ▼
spring.profiles.active=dev
        │
        ▼
Loads Dev Beans
        │
        ▼
Application Ready
```

---

# Architecture

```text
             Spring Boot
                  │
        Reads Active Profile
                  │
      ┌───────────┼───────────┐
      ▼           ▼           ▼
    dev         test        prod
      │           │           │
Local DB      Test DB    Production DB
      │           │           │
      └───────────┴───────────┘
                  │
          Application Ready
```

---

# Real-World Example

Suppose an E-Commerce application integrates with an Email Service.

### Development

Instead of sending real emails, developers use a mock service.

```java
@Service
@Profile("dev")
public class MockEmailService implements EmailService {

}
```

### Production

In production, real emails are sent.

```java
@Service
@Profile("prod")
public class SmtpEmailService implements EmailService {

}
```

Spring automatically creates the correct Bean depending on the active profile.

---

# Another Example

Payment Gateway

Development

```text
Mock Payment Gateway
```

Production

```text
Razorpay / Stripe / PayPal
```

Using Profiles:

```text
Development
        │
        ▼
MockPaymentService

Production
        │
        ▼
RazorpayPaymentService
```

No source code changes are required.

---

# Advantages of Spring Profiles

- Environment-specific configuration
- Cleaner code
- Better maintainability
- Easier deployment
- Improved flexibility
- Commonly used in enterprise applications

---

# Common Mistakes

## 1. Forgetting to Set the Active Profile

Without:

```properties
spring.profiles.active=dev
```

Spring uses the default configuration.

---

## 2. Wrong Profile Name

Wrong

```java
@Profile("development")
```

Correct

```java
@Profile("dev")
```

The active profile name and annotation value must match.

---

## 3. Putting Everything Inside One Configuration File

Instead of:

```text
application.properties
```

use separate configuration files for each environment.

---

# Interview Questions

### Q1. What is a Spring Profile?

A Spring Profile allows applications to load Beans and configuration based on the active environment.

---

### Q2. Why do we use Profiles?

To separate Development, Testing, and Production configurations.

---

### Q3. How do you activate a Profile?

```properties
spring.profiles.active=dev
```

---

### Q4. Where are Profiles commonly used?

- Database Configuration
- Email Services
- Payment Gateways
- Cloud Configuration
- External APIs

---

### Q5. Can we have multiple profile configuration files?

Yes.

For example:

- application-dev.properties
- application-test.properties
- application-prod.properties

---

### Q6. Is `@Profile` used in every Spring Boot project?

No.

Small projects often use only `application.properties`.

Profiles become valuable when applications are deployed across multiple environments.

---

# Best Practices

✅ Create separate configuration files for each environment.

✅ Never hardcode production credentials.

✅ Use meaningful profile names such as `dev`, `test`, and `prod`.

✅ Use `@Profile` only when Bean implementations differ between environments.

✅ Keep environment-specific configuration outside Java code whenever possible.

---

# Key Takeaways

- Spring Profiles support multiple environments.
- Different configuration files can be used for Development, Testing, and Production.
- `@Profile` enables environment-specific Beans.
- The same application can behave differently based on the active profile.
- Profiles are widely used in enterprise Spring Boot applications.

---

# Summary

Spring Profiles are an essential feature for managing applications across different environments. They allow developers to separate configuration and Bean creation without modifying application code.

While many beginner projects use only `application.properties`, enterprise applications commonly use profiles such as `dev`, `test`, and `prod` to manage databases, external services, logging, and cloud configuration.

Understanding Spring Profiles prepares you to work on real-world Spring Boot applications where environment-specific behavior is a standard requirement.

---

# What's Next?

📌 **Next Topic:**

**Spring Boot Logging (SLF4J & Logback)**

---


