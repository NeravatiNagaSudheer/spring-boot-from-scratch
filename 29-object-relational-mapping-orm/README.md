# Object Relational Mapping (ORM) in Spring Boot

## Prerequisites

Before learning ORM, you should understand:

- Java Classes & Objects
- Relational Databases
- SQL Basics (CRUD Operations)
- JDBC (Basic Knowledge)

---

# Learning Objectives

By the end of this guide, you will understand:

- What ORM is
- Why ORM was introduced
- Problems with JDBC
- How ORM works
- Advantages of ORM
- Real-world use cases
- Interview Questions

---

# Introduction

Object Relational Mapping (ORM) is a technique that bridges the gap between **Object-Oriented Programming** and **Relational Databases**.

Instead of writing SQL queries manually for every database operation, developers work with Java objects. The ORM framework automatically converts these objects into SQL queries.

ORM significantly reduces boilerplate code and allows developers to focus on business logic rather than database interactions.

---

# Why Do We Need ORM?

Before ORM, developers had to write repetitive JDBC code for every database operation.

Typical JDBC workflow:

- Create a database connection
- Write SQL queries
- Execute SQL statements
- Convert ResultSet into Java objects
- Close database resources

This approach increases development time and makes applications harder to maintain.

---

# Without ORM

```java
Connection connection =
DriverManager.getConnection(url, username, password);

PreparedStatement statement =
connection.prepareStatement(
    "INSERT INTO users(name, email) VALUES(?, ?)"
);

statement.setString(1, "Sudheer");
statement.setString(2, "sudheer@gmail.com");

statement.executeUpdate();

statement.close();
connection.close();
```

Every CRUD operation requires similar boilerplate code.

---

# With ORM

```java
User user = new User();

user.setName("Sudheer");
user.setEmail("sudheer@gmail.com");

repository.save(user);
```

The ORM framework automatically generates the SQL and stores the data in the database.

---

# How ORM Works

```text
Java Object
      │
      ▼
ORM Framework
      │
      ▼
SQL Queries
      │
      ▼
Relational Database
```

ORM acts as a bridge between Java objects and database tables.

---

# ORM Architecture

```text
Spring Boot Application
          │
          ▼
Business Logic
          │
          ▼
ORM Framework
(Hibernate)
          │
          ▼
SQL Generation
          │
          ▼
PostgreSQL / MySQL
```

---

# Real-World Example

Imagine a **Digital Banking Application**.

When a customer registers:

```java
Customer customer = new Customer();

customer.setName("Sudheer");

customerRepository.save(customer);
```

The ORM framework automatically generates the SQL required to insert the customer into the database.

Developers don't need to manually write SQL for every operation.

---

# Advantages of ORM

- Reduces boilerplate code
- Improves developer productivity
- Database independent
- Easier maintenance
- Better readability
- Supports Object-Oriented Programming
- Automatic SQL generation

---

# JDBC vs ORM

| JDBC | ORM |
|------|-----|
| Manual SQL | Automatic SQL Generation |
| More Boilerplate Code | Less Boilerplate Code |
| Manual Object Mapping | Automatic Object Mapping |
| Harder Maintenance | Easier Maintenance |
| Lower Productivity | Higher Productivity |

---

# Common Mistakes

## 1. Thinking ORM is a Framework

❌ Incorrect

ORM is **not** a framework.

ORM is a **technique (concept)**.

Hibernate is one implementation of the ORM concept.

---

## 2. Thinking ORM Replaces the Database

ORM never replaces the database.

It simply converts Java objects into SQL queries that are executed on the database.

---

# Interview Questions

### Q1. What is ORM?

### Q2. Why was ORM introduced?

### Q3. What problems does ORM solve?

### Q4. Does ORM replace SQL?

### Q5. Name some ORM frameworks in Java.

### Q6. Is Hibernate an ORM framework?

### Q7. What are the advantages of ORM?

### Q8. What is the difference between JDBC and ORM?

---

# Best Practices

✅ Work with Java objects instead of SQL whenever possible.

✅ Keep business logic separate from persistence logic.

✅ Use ORM for enterprise applications.

✅ Understand generated SQL to optimize performance.

---

# Key Takeaways

- ORM maps Java Objects to Database Tables.
- ORM reduces repetitive JDBC code.
- ORM automatically generates SQL queries.
- Hibernate is one of the most popular ORM frameworks.
- ORM improves productivity and application maintainability.

---

# Summary

ORM (Object Relational Mapping) is a technique that simplifies database interaction by mapping Java objects to relational database tables.

Instead of writing repetitive SQL statements, developers interact with Java objects while the ORM framework generates and executes SQL automatically.

Understanding ORM is essential before learning Hibernate, JPA, and Spring Data JPA because these technologies are built on the ORM concept.

---

# What's Next?

📌 **Next Topic:**

**Hibernate – The Most Popular ORM Framework for Java**

---
