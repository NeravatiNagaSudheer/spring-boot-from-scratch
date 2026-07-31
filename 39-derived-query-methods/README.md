# Spring Data JPA Derived Query Methods

## 📖 Introduction

In enterprise applications, searching data is one of the most common operations. Writing SQL queries manually for every search requirement can quickly become repetitive and difficult to maintain.

Spring Data JPA simplifies this process by providing **Derived Query Methods**, where queries are automatically generated based on repository method names. This allows developers to build clean, readable, and maintainable data access layers without writing SQL or JPQL for common operations.

In this module, I implemented both **Exact Search** and **Partial Search** using Spring Data JPA's method naming conventions.

---

# 🎯 Learning Objectives

By the end of this module, you will understand:

- How Spring Data JPA generates queries automatically.
- How to implement exact search using derived query methods.
- How to implement partial search using `ContainingIgnoreCase`.
- The difference between exact and partial search.
- How to expose search functionality through REST APIs.
- How to follow a clean layered architecture using Controller, Service, and Repository.

---

# 🤔 What are Derived Query Methods?

Derived Query Methods are repository methods whose names follow Spring Data JPA naming conventions.

Instead of writing SQL like:

```sql
SELECT *
FROM customer
WHERE first_name = 'Sudheer';
```

Spring Data JPA automatically generates the query when it encounters a method such as:

```java
findByFirstName(String firstName)
```

The framework parses the method name, identifies the entity field, and constructs the appropriate SQL query during application startup.

This reduces boilerplate code while improving readability and developer productivity.

---

# ⚙️ How Derived Query Methods Work

```
Repository Method
        │
        ▼
Spring Data JPA
        │
        ▼
Parses Method Name
        │
        ▼
Generates SQL Query
        │
        ▼
Executes Query
        │
        ▼
Returns Entity Objects
```

The generated implementation is created automatically at runtime, so developers only need to define the repository interface.

---

# 🔍 Exact Search

Exact search retrieves records that exactly match the provided value.

Repository Methods

```java
List<Customer> findByFirstName(String firstName);

List<Customer> findByLastName(String lastName);

List<Customer> findByEmail(String email);

List<Customer> findByStatus(CustomerStatus status);
```

Example

```
Input:

firstName = Sudheer
```

Returns

```
Sudheer
```

If the database contains:

```
Sudheer
Sudeep
```

Searching for:

```
Sud
```

returns no results because an exact match is required.

---

# 🔎 Partial Search

Partial search is more user-friendly because users rarely know the exact value stored in the database.

Spring Data JPA provides the `ContainingIgnoreCase` keyword to perform case-insensitive searches using SQL `LIKE`.

Repository Methods

```java
findByFirstNameContainingIgnoreCase()

findByLastNameContainingIgnoreCase()

findByEmailContainingIgnoreCase()
```

Example

```
Search:

su
```

Returns

```
Sudheer
Sudeep
```

The search is not affected by uppercase or lowercase letters.

Examples:

```
SU
su
Su
sU
```

All return the same results.

---

# 📊 Exact Search vs Partial Search

| Exact Search | Partial Search |
|--------------|----------------|
| Requires complete value | Accepts partial text |
| Usually case-sensitive | Case-insensitive |
| Returns exact matches | Returns all matching records |
| Uses `findBy...()` | Uses `ContainingIgnoreCase()` |
| Best for unique fields | Best for search functionality |

---

# 🏗️ Layered Architecture

The application follows the standard layered architecture.

```
Client
   │
   ▼
Controller
   │
   ▼
Service
   │
   ▼
Repository
   │
   ▼
Database
```

Each layer has a single responsibility, making the application easier to maintain, test, and scale.

---

# 💻 REST APIs

## Exact Search

```
GET /api/v1/customers/search/firstName?firstName=Sudheer

GET /api/v1/customers/search/lastName?lastName=kohli

GET /api/v1/customers/search/email?email=sudheer@gmail.com

GET /api/v1/customers/search/status?status=ACTIVE
```

---

## Partial Search

```
GET /api/v1/customers/search/firstName/contains?firstName=su

GET /api/v1/customers/search/lastName/contains?lastName=koh

GET /api/v1/customers/search/email/contains?email=gmail
```

---

# 🧪 Postman Testing

Successfully verified:

- ✅ Exact Search
- ✅ Partial Search
- ✅ Email Search
- ✅ Status Search
- ✅ First Name Search
- ✅ Last Name Search

---

# 💡 Key Takeaways

- Learned how Spring Data JPA derives SQL queries from repository method names.
- Eliminated the need to write SQL for common search operations.
- Built exact and partial search REST APIs.
- Improved search functionality using `ContainingIgnoreCase`.
- Continued following clean architecture and Spring Boot best practices.
- Used MapStruct for DTO conversion.

---

# 🚀 Technologies Used

- Java 17
- Spring Boot
- Spring Data JPA
- Hibernate
- PostgreSQL
- MapStruct
- Lombok
- Maven
- Postman

---

# 📚 What's Next?

➡️ **Day 40 – Advanced Derived Query Methods**

We'll explore:

- `And`
- `Or`
- `Between`
- `LessThan`
- `GreaterThan`
- `OrderBy`
- `StartingWith`
- `EndingWith`
- `In`
- `NotIn`

These methods allow us to build more powerful search capabilities while still avoiding manual SQL.
