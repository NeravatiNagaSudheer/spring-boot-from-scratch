## 📚 Day 41 – Spring Boot From Scratch

# 🚀 Spring Data JPA – Dynamic Search with JPA Specifications

This module demonstrates how to implement **Dynamic Searching using Spring Data JPA Specifications**, along with **Pagination, Sorting, Input Validation, and Global Exception Handling**.

In the previous days, we used **Spring Data JPA Derived Query Methods** such as:

```java
findByFirstNameContainingIgnoreCase()
findByFirstNameAndLastName()
findByStatusIn()
findByStatusOrderByFirstNameAsc()
```

Derived Query Methods are simple and powerful, but when the number of possible search combinations increases, creating a separate repository method for every combination can become difficult to maintain.

JPA Specifications solve this problem by allowing us to **build database queries dynamically based on the search criteria provided by the client**.

---

# 🎯 Learning Objectives

By completing this module, you will understand:

* What JPA Specifications are
* Why Specifications are useful for dynamic searching
* How to create reusable Specification methods
* How to combine Specifications dynamically
* How to implement multiple optional search filters
* How to combine Specifications with Pagination
* How to combine Specifications with Sorting
* How to validate pagination parameters
* How to validate sorting fields and direction
* How to handle invalid requests using Global Exception Handling
* How to return a consistent paginated response

---

# 🏗️ Architecture

The request flows through the application like this:

```text
Client / Postman
       │
       ▼
CustomerController
       │
       ▼
CustomerSearchRequest
       │
       ▼
CustomerService
       │
       ▼
CustomerSpecification
       │
       ├── firstName
       ├── lastName
       ├── email
       └── status
       │
       ▼
Pagination + Sorting
       │
       ▼
CustomerRepository
       │
       ▼
PostgreSQL
       │
       ▼
CustomerPageResponse
```

---

# 🔎 What is Dynamic Searching?

Dynamic searching means that the client can provide **zero, one, or multiple search criteria**, and the application builds the database query according to the supplied values.

For example:

### Search only by first name

```text
firstName=su
```

### Search by first name and status

```text
firstName=su
status=ACTIVE
```

### Search by email and status

```text
email=gmail.com
status=ACTIVE
```

The application does not need a separate repository method for every combination.

Instead, Specifications are combined dynamically.

---

# 🧩 JPA Specification

A Specification represents a condition that can be applied to a JPA query.

Example:

```java
public static Specification<Customer> hasFirstName(String firstName) {

    return (root, query, criteriaBuilder) ->
            criteriaBuilder.equal(
                    root.get("firstName"),
                    firstName
            );
}
```

Another Specification:

```java
public static Specification<Customer> hasStatus(
        CustomerStatus status) {

    return (root, query, criteriaBuilder) ->
            criteriaBuilder.equal(
                    root.get("status"),
                    status
            );
}
```

These Specifications can then be combined:

```java
specification = specification
        .and(CustomerSpecification.hasFirstName(firstName));

specification = specification
        .and(CustomerSpecification.hasStatus(status));
```

This allows the query to be constructed dynamically.

---

# 📦 Repository Configuration

To use Specifications, the repository extends:

```java
JpaRepository<Customer, Long>
```

and:

```java
JpaSpecificationExecutor<Customer>
```

Example:

```java
@Repository
public interface CustomerRepository
        extends JpaRepository<Customer, Long>,
                JpaSpecificationExecutor<Customer> {

    // Derived Query Methods
}
```

`JpaSpecificationExecutor` provides methods such as:

```java
findAll(Specification<T> specification)
```

and:

```java
findAll(
    Specification<T> specification,
    Pageable pageable
)
```

The second form is particularly useful because it allows us to combine:

**Dynamic Filtering + Pagination + Sorting**

---

# 🔍 Supported Search Filters

The dynamic search currently supports:

| Filter      | Description                         |
| ----------- | ----------------------------------- |
| `firstName` | Search customers by first name      |
| `lastName`  | Search customers by last name       |
| `email`     | Search customers by email           |
| `status`    | Search customers by customer status |

All filters are optional.

---

# 📄 Pagination

Pagination is implemented using Spring Data's:

```java
Pageable
```

Example:

```java
Pageable pageable =
        PageRequest.of(
                pageNo,
                pageSize,
                sort
        );
```

The database query is then executed using:

```java
Page<Customer> page =
        customerRepository.findAll(
                specification,
                pageable
        );
```

The response contains:

* Current page number
* Page size
* Total elements
* Total pages
* Whether it is the last page
* Customer results

---

# ↕️ Dynamic Sorting

The client can dynamically specify:

```text
sortBy
sortDir
```

Example:

```text
sortBy=firstName
sortDir=asc
```

or:

```text
sortBy=firstName
sortDir=desc
```

The service creates the appropriate `Sort` object:

```java
Sort sort = "asc".equalsIgnoreCase(sortDir)
        ? Sort.by(sortBy).ascending()
        : Sort.by(sortBy).descending();
```

---

# 🛡️ Input Validation

The dynamic search API validates pagination and sorting parameters.

### Page number

```java
if (pageNo < 0) {
    throw new IllegalArgumentException(
            "pageNo cannot be negative"
    );
}
```

### Page size

```java
if (pageSize <= 0 || pageSize > 50) {
    throw new IllegalArgumentException(
            "pageSize must be between 1 and 50"
    );
}
```

### Allowed sorting fields

```java
List<String> allowedSortFields = List.of(
        "customerId",
        "firstName",
        "lastName",
        "email",
        "status"
);

if (!allowedSortFields.contains(sortBy)) {
    throw new IllegalArgumentException(
            "Invalid sortBy field"
    );
}
```

### Sort direction

```java
if (!"asc".equalsIgnoreCase(sortDir) &&
        !"desc".equalsIgnoreCase(sortDir)) {

    throw new IllegalArgumentException(
            "sortDir must be either 'asc' or 'desc'"
    );
}
```

These validations prevent invalid input from reaching the database layer.

---

# 🌐 API Endpoint

## Dynamic Customer Search

```http
GET /api/v1/customers/dynamic-search
```

### Example

```http
GET /api/v1/customers/dynamic-search?firstName=su&status=ACTIVE&pageNo=0&pageSize=2&sortBy=firstName&sortDir=asc
```

---

# 📮 Postman Example

### Request

```text
GET http://localhost:8080/api/v1/customers/dynamic-search
```

Query Parameters:

```text
firstName = su
status    = ACTIVE
pageNo    = 0
pageSize  = 2
sortBy    = firstName
sortDir   = asc
```

---

# 📤 Example Response

```json
{
    "customers": [
        {
            "customerId": 2,
            "firstName": "Sudheer",
            "lastName": "Neravati",
            "fullName": "Sudheer Neravati",
            "email": "sudheer@gmail.com",
            "phoneNumber": "9876543210",
            "dateOfBirth": "2002-08-15",
            "status": "ACTIVE"
        }
    ],
    "pageNumber": 0,
    "pageSize": 2,
    "totalElements": 1,
    "totalPages": 1,
    "last": true
}
```

---

# ❌ Validation Examples

### Invalid page number

```http
?pageNo=-1
```

Response:

```json
{
    "status": 400,
    "message": "pageNo cannot be negative"
}
```

### Invalid page size

```http
?pageSize=100
```

Response:

```json
{
    "status": 400,
    "message": "pageSize must be between 1 and 50"
}
```

### Invalid sort field

```http
?sortBy=invalidField
```

Response:

```json
{
    "status": 400,
    "message": "Invalid sortBy field"
}
```

### Invalid sort direction

```http
?sortDir=invalid
```

Response:

```json
{
    "status": 400,
    "message": "sortDir must be either 'asc' or 'desc'"
}
```

These exceptions are handled by the application's existing `GlobalExceptionHandler`.

---

# 🧠 Why Specifications?

Imagine we need the following searches:

```text
firstName
lastName
email
status
firstName + status
lastName + status
email + status
firstName + lastName
firstName + email
firstName + lastName + status
...
```

Creating a repository method for every combination would quickly become difficult to maintain.

With Specifications:

```text
CustomerSearchRequest
        ↓
Build Specification
        ↓
Add only supplied filters
        ↓
Pagination
        ↓
Sorting
        ↓
Database
```

This provides a much more flexible approach for dynamic search requirements.

---

# 🔄 Derived Queries vs Specifications

### Derived Query Methods

```java
findByFirstNameAndLastName(...)
```

Good for:

* Simple queries
* Fixed search requirements
* Small applications

### JPA Specifications

```java
Specification<Customer>
```

Good for:

* Dynamic search
* Multiple optional filters
* Complex combinations
* Pagination + sorting
* Applications where search requirements may grow

---

# 📁 Project Structure

```text
customer-service
│
├── controller
│   └── CustomerController.java
│
├── service
│   └── CustomerService.java
│
├── repository
│   └── CustomerRepository.java
│
├── specification
│   └── CustomerSpecification.java
│
├── dto
│   ├── CustomerSearchRequest.java
│   ├── CustomerResponseDto.java
│   └── CustomerPageResponse.java
│
├── entity
│   └── Customer.java
│
├── exception
│   ├── CustomerNotFoundException.java
│   ├── ErrorResponse.java
│   └── GlobalExceptionHandler.java
│
└── mapper
    └── CustomerMapper.java
```

---

# 🛠️ Tech Stack

* Java
* Spring Boot
* Spring Data JPA
* JPA Specifications
* Hibernate
* PostgreSQL
* MapStruct
* Lombok
* Maven
* Postman
* Jakarta Validation

---

# 🎯 Key Takeaways

After completing Day 41, the important concepts are:

1. **Derived Query Methods are useful for simple, fixed queries.**

2. **JPA Specifications are useful when search requirements become dynamic.**

3. **Multiple Specifications can be combined using `.and()`.**

4. **Specifications can be combined with `Pageable`.**

5. **Pagination and sorting can be handled together with dynamic filtering.**

6. **Input validation should happen before executing the database query.**

7. **Global exception handling keeps API error responses consistent.**

8. **A single dynamic-search endpoint can replace many combinations of repository methods.**

---

# 🚀 Day 41 Result

Implemented and tested:

```text
Dynamic Filtering       ✅
JPA Specifications      ✅
Pagination              ✅
Dynamic Sorting         ✅
Input Validation        ✅
Global Exception        ✅
Custom Page Response    ✅
Postman Testing         ✅
```

---

## 📌 What's Next?

The next step is to build on top of the Specification-based search instead of going back to more Derived Query Method keywords.

The goal is to gradually move the Customer Service toward a more realistic backend implementation.

---

⭐ **Learning in Public**

Building → Testing → Understanding → Improving

#SpringBoot #SpringDataJPA #Java #JPA #JPASpecifications #DynamicSearch #Pagination #Sorting #PostgreSQL #BackendDevelopment #100DaysOfCode
