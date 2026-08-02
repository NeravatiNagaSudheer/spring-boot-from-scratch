# Spring Data JPA - Advanced Derived Query Methods

## 📖 Introduction

In this module, I explored **Advanced Spring Data JPA Derived Query Methods** to build flexible and maintainable search APIs without writing SQL or JPQL queries.

Spring Data JPA automatically derives SQL queries from repository method names, allowing developers to focus on business logic while reducing boilerplate code.

This project demonstrates several commonly used derived query methods that are widely used in real-world Spring Boot applications.

---

# 🚀 Topics Covered

- AND Query
- OR Query
- BETWEEN Query
- StartingWith
- EndingWith
- OrderBy
- IN Query

---

# 📂 Project Structure

```
Controller
     │
     ▼
Service
     │
     ▼
Repository
     │
     ▼
Spring Data JPA
     │
     ▼
PostgreSQL Database
```

---

# 1️⃣ AND Query

Returns records only when **both conditions** are satisfied.

### Repository

```java
List<Customer> findByFirstNameAndLastName(
        String firstName,
        String lastName
);
```

### Example

```
GET /api/v1/customers/search?firstName=Virat&lastName=Kohli
```

Generated SQL

```sql
SELECT *
FROM customer
WHERE first_name = ?
AND last_name = ?;
```

---

# 2️⃣ OR Query

Returns records when **either condition** matches.

### Repository

```java
List<Customer> findByFirstNameOrLastName(
        String firstName,
        String lastName
);
```

### Example

```
GET /api/v1/customers/search/or?firstName=Virat&lastName=Sharma
```

Generated SQL

```sql
SELECT *
FROM customer
WHERE first_name = ?
OR last_name = ?;
```

---

# 3️⃣ BETWEEN Query

Retrieve customers whose date of birth falls within a specific range.

### Repository

```java
List<Customer> findByDateOfBirthBetween(
        LocalDate startDate,
        LocalDate endDate
);
```

### Example

```
GET /api/v1/customers/search/dob?startDate=1990-01-01&endDate=2000-12-31
```

Generated SQL

```sql
SELECT *
FROM customer
WHERE date_of_birth
BETWEEN ? AND ?;
```

---

# 4️⃣ StartingWith

Search customers whose first name starts with specific text.

### Repository

```java
List<Customer> findByFirstNameStartingWithIgnoreCase(
        String firstName
);
```

### Example

```
GET /api/v1/customers/search/firstname/startsWith?firstName=su
```

Generated SQL

```sql
SELECT *
FROM customer
WHERE LOWER(first_name)
LIKE LOWER('su%');
```

---

# 5️⃣ EndingWith

Search customers whose email ends with a specific value.

### Repository

```java
List<Customer> findByEmailEndingWithIgnoreCase(
        String email
);
```

### Example

```
GET /api/v1/customers/search/email/endsWith?email=gmail.com
```

Generated SQL

```sql
SELECT *
FROM customer
WHERE LOWER(email)
LIKE LOWER('%gmail.com');
```

---

# 6️⃣ OrderBy

Retrieve customers sorted by first name.

### Repository

```java
List<Customer> findByStatusOrderByFirstNameAsc(
        CustomerStatus status
);
```

### Example

```
GET /api/v1/customers/search/status/orderBy/firstName?status=ACTIVE
```

Generated SQL

```sql
SELECT *
FROM customer
WHERE status = ?
ORDER BY first_name ASC;
```

---

# 7️⃣ IN Query

Retrieve customers whose status matches multiple values.

### Repository

```java
List<Customer> findByStatusIn(
        List<CustomerStatus> statuses
);
```

### Example

```
GET /api/v1/customers/search/status/in?statuses=ACTIVE,BLOCKED
```

Generated SQL

```sql
SELECT *
FROM customer
WHERE status IN (?, ?);
```

---

# 🧪 API Testing

All APIs were tested using **Postman**.

### Tested Endpoints

```
GET /search
GET /search/or
GET /search/dob
GET /search/firstname/startsWith
GET /search/email/endsWith
GET /search/status/orderBy/firstName
GET /search/status/in
```

---

# 📚 What I Learned

- How Spring Data JPA derives SQL queries from method names.
- Building advanced search APIs without writing SQL.
- Combining multiple search conditions using AND and OR.
- Filtering data within a range using BETWEEN.
- Implementing prefix and suffix searches.
- Sorting results using OrderBy.
- Filtering multiple values using IN.
- Keeping repository code clean and maintainable.

---

# 💡 Key Takeaways

- No manual SQL or JPQL for common queries.
- Cleaner Repository layer.
- Improved code readability.
- Less boilerplate code.
- Better maintainability.
- Faster development.
- Production-ready search APIs.

---

# 🛠️ Tech Stack

- Java 21
- Spring Boot
- Spring Data JPA
- PostgreSQL
- MapStruct
- Maven
- Bean Validation
- Lombok
- Postman

---

# 📌 Repository

**Spring Boot From Scratch**

Every module in this repository builds upon the previous one, gradually creating a production-ready Spring Boot backend while following clean architecture and best practices.

---

⭐ If you found this project helpful, consider giving it a star!
