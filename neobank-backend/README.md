# 🏦 NeoBank Backend - Customer Management Module

Welcome to **Day 33** of the **Spring Boot From Scratch** series!

After learning **ORM**, **Hibernate**, **JPA**, and **Spring Data JPA**, it's time to apply those concepts by building a real-world backend application.

In this chapter, we'll develop the **Customer Management Module** of our **NeoBank Backend**, which serves as the foundation for the entire Digital Banking Platform.

---

# 📚 What You'll Learn

By the end of this project, you'll understand how to:

- Design a layered Spring Boot application
- Create REST APIs
- Perform CRUD operations
- Connect Spring Boot with PostgreSQL
- Use Spring Data JPA repositories
- Organize production-ready project structure
- Build the first module of an enterprise banking application

---

# 🏦 Project Overview

Every banking application starts with customers.

Before a customer can:

- Open a bank account
- Transfer money
- Apply for loans
- Request a debit card
- View transactions

the system must first register and manage customer information.

This module implements the Customer Management functionality.

---

# 🛠 Tech Stack

| Technology | Version |
|------------|----------|
| Java | 21+ |
| Spring Boot | 3.x |
| Spring Data JPA | Latest |
| Hibernate | ORM Provider |
| PostgreSQL | Database |
| Maven | Build Tool |

---

# 📂 Project Structure

```text
customer-management

│
├── controller
│      CustomerController.java
│
├── service
│      CustomerService.java
│
├── repository
│      CustomerRepository.java
│
├── entity
│      Customer.java
│
├── exception
│
├── config
│
├── resources
│      application.properties
│
└── NeoBankApplication.java
```

---

# 🏗 Architecture

```text
                Client

                   │

                   ▼

        CustomerController

                   │

                   ▼

         CustomerService

                   │

                   ▼

      CustomerRepository

                   │

                   ▼

             PostgreSQL
```

---

# 📦 Customer Entity

```java
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNumber;

}
```

---

# 📚 Repository Layer

```java
public interface CustomerRepository
        extends JpaRepository<Customer, Long> {

}
```

Spring Data JPA automatically provides:

- save()
- findAll()
- findById()
- deleteById()
- count()
- existsById()

No implementation is required.

---

# 🌐 REST APIs

## Create Customer

```
POST /customers
```

---

## Get All Customers

```
GET /customers
```

---

## Get Customer By ID

```
GET /customers/{id}
```

---

## Update Customer

```
PUT /customers/{id}
```

---

## Delete Customer

```
DELETE /customers/{id}
```

---

# 📨 Sample Request

```json
{
  "firstName":"Sudheer",
  "lastName":"Neravati",
  "email":"sudheer@gmail.com",
  "phoneNumber":"9876543210"
}
```

---

# 📤 Sample Response

```json
{
  "customerId":1,
  "firstName":"Sudheer",
  "lastName":"Neravati",
  "email":"sudheer@gmail.com",
  "phoneNumber":"9876543210"
}
```

---

# 🚀 Features Implemented

✅ Create Customer

✅ Read Customer

✅ Update Customer

✅ Delete Customer

✅ PostgreSQL Integration

✅ Spring Data JPA

✅ Layered Architecture

---

# 💼 Real-World Scenario

Imagine a customer installs the NeoBank mobile application.

The first action is registration.

Once the customer profile is created, the system can later support:

- Savings Accounts
- Current Accounts
- Fixed Deposits
- Debit Cards
- Credit Cards
- UPI Integration
- Transaction History
- Money Transfers
- Loan Management

This module acts as the entry point for all future banking features.

---

# 🎯 Interview Questions

### 1. Why do we use a layered architecture?

### 2. What is JpaRepository?

### 3. Why don't we implement CRUD methods manually?

### 4. Why do we use @Entity?

### 5. Why is Service placed between Controller and Repository?

### 6. What is the responsibility of the Repository layer?

### 7. What is GenerationType.IDENTITY?

### 8. Why is PostgreSQL a good choice for banking systems?

---

# 📈 What's Next?

In the next chapter, we'll make this module production-ready by adding:

- ✅ Bean Validation
- ✅ Global Exception Handling
- ✅ Custom Error Responses

---

# 📖 Series Progress

- ✅ Day 29 – ORM
- ✅ Day 30 – Hibernate
- ✅ Day 31 – JPA
- ✅ Day 32 – Spring Data JPA
- ✅ Day 33 – Customer Management Module
- ⏳ Day 34 – Input Validation
- ⏳ Day 35 – Global Exception Handling

---

# ⭐ Support the Project

If you found this repository helpful:

⭐ Star the repository

🍴 Fork it

📢 Share it with others

Happy Coding! 🚀
