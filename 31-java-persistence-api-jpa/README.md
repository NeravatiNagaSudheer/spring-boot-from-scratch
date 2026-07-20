# Java Persistence API (JPA)

## Prerequisites

Before learning JPA, you should understand:

- Java Classes & Objects
- SQL Basics
- Relational Databases
- JDBC
- ORM (Object Relational Mapping)
- Hibernate

---

# Learning Objectives

By the end of this guide, you will understand:

- What JPA is
- Why JPA was introduced
- Difference between JPA and Hibernate
- How JPA works
- Advantages of JPA
- Real-world use cases
- Interview Questions

---

# Introduction

**JPA (Java Persistence API)** is a Java Specification that defines a standard way to map Java Objects to Relational Databases.

It provides a set of interfaces, annotations, and rules that ORM frameworks follow to perform database operations.

Unlike Hibernate, **JPA is not a framework**.

It is simply a specification.

The actual implementation is provided by ORM frameworks like:

- Hibernate
- EclipseLink
- OpenJPA

Hibernate is the most widely used implementation.

---

# Why Was JPA Introduced?

Before JPA, every ORM framework had its own APIs.

If a company switched from one ORM framework to another, developers had to rewrite a significant amount of code.

JPA solved this problem by introducing a common standard.

Applications can now be written against the JPA specification while choosing any compatible implementation.

---

# What is a Specification?

A specification defines **what should be done**, but not **how it should be done**.

Think of it as a contract.

Example:

A driving license tells you the rules of driving.

It does not manufacture the car.

Similarly,

JPA defines the rules for persistence.

Hibernate implements those rules.

---

# JPA vs Hibernate

| JPA | Hibernate |
|------|-----------|
| Specification | ORM Framework |
| Defines Rules | Implements Rules |
| No Implementation | Actual Implementation |
| Standard API | Hibernate API |
| Vendor Independent | Vendor Specific |

---

# How JPA Works

```text
Spring Boot Application

↓

Spring Data JPA

↓

JPA Specification

↓

Hibernate

↓

JDBC

↓

Database
```

---

# JPA Architecture

```text
Java Object

↓

JPA Annotations

↓

Hibernate

↓

SQL Generation

↓

Database
```

---

# Common JPA Annotations

## @Entity

Represents a database table.

```java
@Entity
public class User {

}
```

---

## @Id

Represents the Primary Key.

```java
@Id
private Long id;
```

---

## @GeneratedValue

Automatically generates primary key values.

```java
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
```

We'll learn these annotations in detail in the next lesson.

---

# Real-World Example

Imagine a Digital Banking Application.

A Customer class represents customer information.

```java
@Entity
public class Customer {

    @Id
    @GeneratedValue
    private Long customerId;

    private String name;

}
```

JPA defines how this object should be mapped.

Hibernate performs the actual database operations.

---

# Advantages of JPA

- Standard API
- Reduces Vendor Lock-In
- Easy Migration
- Cleaner Code
- Works with Multiple ORM Frameworks
- Easy Spring Boot Integration

---

# JPA vs JDBC

| JDBC | JPA |
|------|-----|
| Manual SQL | Automatic Mapping |
| Manual ResultSet Conversion | Automatic Object Mapping |
| More Boilerplate | Less Boilerplate |
| Harder Maintenance | Easier Maintenance |

---

# Common Mistakes

## 1. JPA is NOT Hibernate

Many developers think JPA and Hibernate are the same.

They are not.

JPA is a specification.

Hibernate is one implementation.

---

## 2. JPA Does NOT Access the Database

JPA itself never communicates with the database.

Hibernate performs the actual database operations.

---

## 3. JPA Does NOT Replace SQL

JPA generates SQL using an implementation like Hibernate.

Understanding SQL is still very important.

---

# Interview Questions

### Q1. What is JPA?

### Q2. Is JPA a Framework?

### Q3. What is the difference between JPA and Hibernate?

### Q4. Why was JPA introduced?

### Q5. What are some JPA implementations?

### Q6. Can Hibernate work without JPA?

### Q7. Can JPA work without Hibernate?

### Q8. What are JPA annotations?

### Q9. What is @Entity?

### Q10. What is @Id?

---

# Best Practices

- Program against the JPA specification.
- Avoid Hibernate-specific APIs unless necessary.
- Keep Entity classes simple.
- Understand generated SQL.
- Follow JPA standards.

---

# Key Takeaways

- JPA is a Specification.
- Hibernate is an Implementation.
- JPA defines rules for persistence.
- Hibernate follows those rules.
- Spring Boot uses Hibernate as the default JPA implementation.

---

# Summary

JPA (Java Persistence API) provides a standard specification for object-relational mapping in Java applications.

Instead of tying applications to a specific ORM framework, developers can write code using the JPA specification while relying on implementations like Hibernate to perform the actual database operations.

Understanding JPA is essential before learning Spring Data JPA because Spring Data JPA builds directly on top of the JPA specification.

---

# What's Next?

📌 **Next Topic:**

**Spring Data JPA – Simplifying Database Operations in Spring Boot**

---
