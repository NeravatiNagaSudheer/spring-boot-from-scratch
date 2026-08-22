# 🔄 Spring Transaction Management & ACID

Day 42 of my **Spring Boot From Scratch** learning series.

In this module, I learned how **transaction management works in Spring Boot**, why transactions are important in backend applications, and how Spring's `@Transactional` annotation is used to define transaction boundaries.

The concepts were implemented in my **NeoBank Customer Service** project.

---

## 📚 What is a Database Transaction?

A transaction is a group of database operations treated as a **single logical unit of work**.

A transaction follows the principle:

```text
All operations succeed
        ↓
      COMMIT

If an operation fails
        ↓
     ROLLBACK
