# Hibernate - The Most Popular ORM Framework for Java

## Prerequisites

Before learning Hibernate, you should understand:

- Java Classes & Objects
- SQL Basics
- Relational Databases
- JDBC
- ORM (Object Relational Mapping)

---

# Learning Objectives

By the end of this guide, you will understand:

- What Hibernate is
- Why Hibernate was created
- Problems Hibernate solves
- How Hibernate works internally
- Hibernate Architecture
- Advantages of Hibernate
- Interview Questions
- Best Practices

---

# Introduction

Hibernate is an **Open Source ORM (Object Relational Mapping) Framework** for Java.

It simplifies database interaction by allowing developers to work with Java Objects instead of writing SQL queries for every database operation.

Hibernate automatically converts Java objects into SQL statements and maps database records back into Java objects.

It is one of the most popular ORM frameworks and is widely used in enterprise Java applications.

---

# Why Hibernate?

Before Hibernate, developers used JDBC.

For every CRUD operation they had to:

- Open a database connection
- Write SQL queries
- Execute SQL
- Convert ResultSet into Java Objects
- Close resources

This resulted in a large amount of repetitive code.

Hibernate eliminates most of this boilerplate.

---

# Without Hibernate (Using JDBC)

```java
Connection connection =
DriverManager.getConnection(url, username, password);

PreparedStatement statement =
connection.prepareStatement(
"INSERT INTO users(name,email) VALUES(?,?)");

statement.setString(1,"Sudheer");
statement.setString(2,"sudheer@gmail.com");

statement.executeUpdate();

statement.close();
connection.close();
```

Notice how much code is required just to insert one record.

---

# With Hibernate

```java
User user = new User();

user.setName("Sudheer");
user.setEmail("sudheer@gmail.com");

session.persist(user);
```

Hibernate automatically generates the SQL required to save the object.

---

# How Hibernate Works

```text
Java Object
      │
      ▼
Hibernate
      │
      ▼
Generates SQL
      │
      ▼
Database
```

Developers work with Java Objects.

Hibernate handles the database communication behind the scenes.

---

# Hibernate Architecture

```text
Application

↓

Business Logic

↓

Hibernate Framework

↓

JDBC

↓

Database
```

Hibernate internally uses JDBC to communicate with the database.

---

# Key Components

## SessionFactory

A heavyweight object responsible for creating Session objects.

Usually created only once during application startup.

---

## Session

Represents a connection between the application and the database.

Used to perform CRUD operations.

Example:

```java
Session session = sessionFactory.openSession();
```

---

## Transaction

Ensures database operations are completed successfully.

```java
Transaction transaction = session.beginTransaction();

transaction.commit();
```

---

## Entity

A simple Java class that represents a database table.

We'll learn Entities in detail in the upcoming lessons.

---

# Real-World Example

Imagine a Digital Banking Application.

When a customer creates a new bank account:

```java
Customer customer = new Customer();

customer.setName("Sudheer");

session.persist(customer);
```

Hibernate automatically generates the required INSERT statement and stores the customer details in the database.

Developers don't need to manually write SQL.

---

# Advantages of Hibernate

- Less Boilerplate Code
- Automatic SQL Generation
- Database Independent
- Object-Oriented Programming Support
- Built-in Caching
- Lazy Loading Support
- Better Performance
- Easier Maintenance

---

# Hibernate vs JDBC

| JDBC | Hibernate |
|------|-----------|
| Manual SQL | Automatic SQL |
| Manual Mapping | Automatic Mapping |
| More Code | Less Code |
| Low Productivity | High Productivity |
| Hard to Maintain | Easy to Maintain |

---

# Common Mistakes

## 1. Hibernate is NOT JPA

Hibernate is an ORM Framework.

JPA is a Specification.

Hibernate is one implementation of JPA.

---

## 2. Hibernate Does NOT Replace SQL

Hibernate generates SQL automatically.

Understanding SQL is still essential.

---

## 3. Hibernate Internally Uses JDBC

Hibernate is built on top of JDBC.

JDBC knowledge is still valuable.

---

# Interview Questions

### Q1. What is Hibernate?

### Q2. Why do we use Hibernate?

### Q3. What problems does Hibernate solve?

### Q4. What is SessionFactory?

### Q5. What is Session?

### Q6. What is Transaction?

### Q7. Does Hibernate replace JDBC?

### Q8. What are the advantages of Hibernate?

### Q9. Is Hibernate a framework or specification?

### Q10. What is the difference between Hibernate and ORM?

---

# Best Practices

- Create only one SessionFactory.
- Always close Sessions.
- Use Transactions for database updates.
- Keep Entities simple.
- Understand the generated SQL.
- Don't ignore SQL fundamentals.

---

# Key Takeaways

- Hibernate is an ORM Framework.
- Hibernate reduces JDBC boilerplate code.
- Hibernate automatically generates SQL.
- Hibernate internally uses JDBC.
- Hibernate simplifies database development.

---

# Summary

Hibernate is one of the most popular ORM frameworks in Java. It enables developers to interact with databases using Java objects instead of writing repetitive SQL code.

By automating object-to-table mapping and SQL generation, Hibernate makes applications easier to develop, maintain, and scale.

Understanding Hibernate is an important step before learning JPA and Spring Data JPA.

---

# What's Next?

📌 **Next Topic:**

**JPA (Java Persistence API) — Understanding Specification vs Implementation**

---
