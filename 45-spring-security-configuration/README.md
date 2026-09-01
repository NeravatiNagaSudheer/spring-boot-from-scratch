# 🚀 Spring Boot From Scratch – Day 45
# Spring Security Configuration & Endpoint Authorization 🔐

Today I moved from understanding the fundamentals of Spring Security to implementing the first security layer in my **NeoBank Customer Service** project.

The main focus was understanding how Spring Security intercepts incoming HTTP requests and how we can configure which endpoints are public and which endpoints require authentication.

---

# 📚 Topics Covered

Today I learned and implemented:

- Spring Security
- Security Configuration
- `SecurityFilterChain`
- `HttpSecurity`
- `authorizeHttpRequests()`
- `requestMatchers()`
- `permitAll()`
- `authenticated()`
- Public endpoints
- Protected endpoints
- Spring Security Filter Chain
- Request authorization
- CSRF configuration
- Disabling form login for REST APIs
- Disabling HTTP Basic authentication for the current REST API setup

---

# 🔐 What is Spring Security?

Spring Security is a framework used to secure Spring applications.

It provides mechanisms for:

- Authentication
- Authorization
- Protection against common security attacks
- Request filtering
- Session management
- Password security
- Role-based access control

In a REST API, Spring Security sits between the client and the application.

Without Spring Security:

```text
Client
   ↓
Controller
   ↓
Service
   ↓
Repository
   ↓
Database
