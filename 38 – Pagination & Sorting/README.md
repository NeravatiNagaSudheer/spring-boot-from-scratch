# Pagination & Sorting with Spring Data JPA

## 📌 Overview

In this module, I implemented **Pagination** and **Sorting** using **Spring Data JPA** in the Customer Service.

Returning all records from the database is inefficient for large datasets. Pagination allows clients to retrieve data page by page, while Sorting enables records to be ordered dynamically based on user requirements.

These are essential features in production-grade REST APIs because they improve scalability, performance, and user experience.

---

# 🚀 Features Implemented

- ✅ Pagination using `Pageable`
- ✅ Dynamic Sorting using `Sort`
- ✅ `PageRequest` for pagination requests
- ✅ `Page<Customer>` for retrieving paginated data
- ✅ Custom Pagination Response DTO
- ✅ Dynamic Query Parameters
- ✅ Application Constants (`AppConstants`)
- ✅ DTO Mapping using MapStruct
- ✅ Tested using Postman

---

# 📂 Project Structure

```
customer-service
│
├── controller
├── dto
│   ├── CustomerRequestDto
│   ├── CustomerResponseDto
│   └── CustomerPageResponse
│
├── entity
├── enums
├── exception
├── mapper
├── repository
├── service
├── util
│   └── AppConstants
│
└── CustomerServiceApplication
```

---

# 📚 Concepts Covered

## 1️⃣ Pageable

Represents pagination information.

It contains:

- Page Number
- Page Size
- Sorting Information

```java
Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
```

---

## 2️⃣ PageRequest

Creates an implementation of `Pageable`.

```java
PageRequest.of(pageNo, pageSize, sort);
```

---

## 3️⃣ Page

Spring Data JPA returns data as a `Page<T>`.

Using Page we can retrieve:

- Current Page
- Total Pages
- Total Elements
- Current Page Content
- Is Last Page

```java
Page<Customer> page =
customerRepository.findAll(pageable);
```

---

## 4️⃣ Sort

Sorting can be performed dynamically.

Ascending

```java
Sort.by(sortBy).ascending();
```

Descending

```java
Sort.by(sortBy).descending();
```

---

## 5️⃣ Custom Pagination Response

Instead of exposing Spring's default Page object, I created a custom response.

```java
CustomerPageResponse
```

It contains:

- Customers
- Page Number
- Page Size
- Total Elements
- Total Pages
- Last Page

This produces a cleaner REST API response.

---

## 6️⃣ AppConstants

Created a utility class to centralize default pagination values.

```java
public final class AppConstants {

    public static final String DEFAULT_PAGE_NUMBER = "0";

    public static final String DEFAULT_PAGE_SIZE = "5";

    public static final String DEFAULT_SORT_BY = "customerId";

    public static final String DEFAULT_SORT_DIRECTION = "asc";

    private AppConstants() {
        throw new IllegalStateException("Utility class");
    }
}
```

Benefits:

- No hardcoded values
- Easy maintenance
- Reusable across controllers

---

# 🛠 Implementation Flow

```
Client Request
      │
      ▼
Controller
      │
      ▼
Read pageNo, pageSize,
sortBy & sortDir
      │
      ▼
Create Sort Object
      │
      ▼
Create Pageable
(PageRequest)
      │
      ▼
Repository.findAll(pageable)
      │
      ▼
Page<Customer>
      │
      ▼
MapStruct
      │
      ▼
CustomerResponseDto
      │
      ▼
CustomerPageResponse
      │
      ▼
Return JSON Response
```

---

# 🌐 REST Endpoints

## Default Pagination

```
GET /api/v1/customers/page
```

---

## Pagination

```
GET /api/v1/customers/page?pageNo=1&pageSize=5
```

---

## Sorting (Ascending)

```
GET /api/v1/customers/page?sortBy=firstName&sortDir=asc
```

---

## Sorting (Descending)

```
GET /api/v1/customers/page?sortBy=customerId&sortDir=desc
```

---

## Pagination + Sorting

```
GET /api/v1/customers/page?pageNo=1&pageSize=5&sortBy=firstName&sortDir=desc
```

---

# 📥 Sample Response

```json
{
  "customers": [
    {
      "customerId": 6,
      "firstName": "Rahul",
      "lastName": "Sharma",
      "fullName": "Rahul Sharma",
      "email": "rahul@gmail.com",
      "phoneNumber": "9876543210",
      "dateOfBirth": "1995-08-15",
      "status": "ACTIVE"
    }
  ],
  "pageNumber": 0,
  "pageSize": 5,
  "totalElements": 24,
  "totalPages": 5,
  "last": false
}
```

---

# ✅ Benefits

- Faster API responses
- Handles large datasets efficiently
- Better database performance
- Cleaner REST APIs
- Dynamic sorting
- Reusable pagination logic
- Production-ready implementation

---

# 🧠 Key Takeaways

- Learned the difference between `Page`, `Pageable`, `PageRequest`, and `Sort`.
- Built scalable REST APIs using Spring Data JPA.
- Created a custom paginated response instead of exposing Spring's default `Page`.
- Centralized configuration using `AppConstants`.
- Improved maintainability by following clean architecture principles.

---

# 🛠 Technologies Used

- Java 17
- Spring Boot
- Spring Data JPA
- Hibernate
- PostgreSQL
- MapStruct
- Maven
- Lombok
- Postman

---

# 📚 Next Module

➡️ **Day 39 – Filtering & Searching with Spring Data JPA**

Topics:

- Derived Query Methods
- Searching by First Name
- Searching by Email
- Searching by Status
- Multiple Search Criteria
- Filtering + Pagination
- Filtering + Sorting
- Spring Data JPA Best Practices

---

⭐ If you found this project helpful, feel free to star the repository.
