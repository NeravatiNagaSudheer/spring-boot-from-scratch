# Advanced MapStruct in Spring Boot

## 📌 Overview

In this module, I explored **Advanced MapStruct** features to eliminate manual object mapping and build cleaner, maintainable, and production-ready Spring Boot applications.

Instead of writing repetitive mapping logic inside the Service layer, MapStruct generates efficient mapping code during compilation, allowing developers to focus on business logic.

---

# 📚 Topics Covered

- ✅ @Mapper
- ✅ @Mapping
- ✅ @MappingTarget
- ✅ @BeanMapping
- ✅ NullValuePropertyMappingStrategy
- ✅ Constant Mapping
- ✅ Expression Mapping
- ✅ @Named
- ✅ qualifiedByName
- ✅ Enum Mapping
- ✅ DTO to Entity Mapping
- ✅ Entity to DTO Mapping
- ✅ List Mapping
- ✅ Partial Update Mapping

---

# 🏗 Project Structure

```
src
│
├── controller
│
├── dto
│   ├── CustomerRequestDto
│   └── CustomerResponseDto
│
├── entity
│   └── Customer
│
├── enums
│   └── CustomerStatus
│
├── mapper
│   ├── CustomerMapper
│   └── util
│       └── StringMapper
│
├── repository
│
├── service
│
└── exception
```

---

# 📖 Features Implemented

## 1️⃣ DTO → Entity Mapping

Converted incoming Request DTOs into JPA Entity objects.

```java
@Mapping(target = "customerId", ignore = true)
@Mapping(target = "status", constant = "ACTIVE")
Customer toEntity(CustomerRequestDto dto);
```

### Why?

- Prevents exposing Entity objects directly
- Automatically assigns default status
- Keeps business logic clean

---

## 2️⃣ Entity → Response DTO

Converted Entity objects into Response DTOs.

```java
CustomerResponseDto toResponseDto(Customer customer);
```

### Benefits

- Hide internal Entity implementation
- Return only required fields
- Improve API security

---

## 3️⃣ List Mapping

Instead of writing loops manually,

```java
List<CustomerResponseDto> toResponseDtoList(List<Customer> customers);
```

MapStruct automatically generates:

```java
for(Customer customer : customers){
    ...
}
```

---

## 4️⃣ @MappingTarget

Instead of creating a new object during updates,

MapStruct updates the existing JPA entity.

```java
void updateCustomerFromDto(
        CustomerRequestDto dto,
        @MappingTarget Customer customer
);
```

### Benefits

- Keeps Hibernate managed entity
- Cleaner update logic
- Less boilerplate code

---

## 5️⃣ @BeanMapping

```java
@BeanMapping(
nullValuePropertyMappingStrategy =
NullValuePropertyMappingStrategy.IGNORE
)
```

### Benefits

When a field is null inside Request DTO,

MapStruct ignores it instead of overwriting the existing value.

Example

Before Update

```
First Name : Sudheer
Last Name  : Neravati
Email      : sudheer@gmail.com
```

Request

```json
{
  "firstName":"Sudheer",
  "lastName":null
}
```

After Update

```
First Name : Sudheer
Last Name  : Neravati
```

---

## 6️⃣ Constant Mapping

Assigned a default customer status.

```java
@Mapping(target="status",
constant="ACTIVE")
```

Every new customer starts with

```
ACTIVE
```

without writing additional Service layer code.

---

## 7️⃣ Expression Mapping

Generated full name dynamically.

```java
@Mapping(
target="fullName",
expression="java(customer.getFirstName() + \" \" + customer.getLastName())"
)
```

Instead of storing duplicate data in the database,

```
First Name
Last Name
```

are combined while mapping.

---

## 8️⃣ @Named

Created reusable custom mapping methods.

```java
@Named("capitalize")
public String capitalize(String value){
...
}
```

---

## 9️⃣ qualifiedByName

Applied custom mapping logic.

```java
@Mapping(
target="firstName",
qualifiedByName="capitalize"
)
```

This automatically capitalizes customer names.

Example

Database

```
sudheer
```

Response

```
Sudheer
```

---

## 🔟 Enum Mapping

Instead of using String values,

Implemented

```java
public enum CustomerStatus {

    ACTIVE,
    INACTIVE,
    BLOCKED

}
```

### Benefits

- Better type safety
- Prevent invalid values
- Easier maintenance

---

# 🛠 Service Layer

The Service layer now contains almost no manual mapping.

Before

```java
Customer customer = new Customer();

customer.setFirstName(...);

customer.setLastName(...);

customer.setEmail(...);

...
```

Now

```java
Customer customer =
customerMapper.toEntity(dto);
```

Updating

```java
customerMapper.updateCustomerFromDto(
dto,
existingCustomer
);
```

Returning Response

```java
return customerMapper
.toResponseDto(savedCustomer);
```

Much cleaner and easier to maintain.

---

# 🔄 Request Flow

```
             Client
                │
                ▼
      CustomerRequestDto
                │
                ▼
        CustomerController
                │
                ▼
         CustomerService
                │
                ▼
       CustomerMapper
                │
                ▼
        Customer Entity
                │
                ▼
      CustomerRepository
                │
                ▼
          PostgreSQL
                │
                ▼
        Customer Entity
                │
                ▼
       CustomerMapper
                │
                ▼
     CustomerResponseDto
                │
                ▼
             Client
```

---

# 💻 Technologies Used

- Java 17
- Spring Boot
- Spring Data JPA
- PostgreSQL
- MapStruct
- Maven
- Lombok

---

# 📸 Output

### Create Customer

```
POST /api/v1/customers
```

Returns

```json
{
  "customerId": 1,
  "firstName": "Chandra",
  "lastName": "Shekar",
  "fullName": "chandra shekar",
  "email": "chandrashekar@gmail.com",
  "phoneNumber": "7799677017",
  "dateOfBirth": "1995-08-15",
  "status": "ACTIVE"
}
```

---

# 📖 What I Learned

- How MapStruct generates mapping code at compile time.
- Difference between Entity and DTO.
- Advanced mapping using annotations.
- Updating existing entities with @MappingTarget.
- Partial updates using @BeanMapping.
- Reusable custom mapping methods using @Named.
- Enum mapping for better domain modelling.
- Cleaner Service layer with less boilerplate code.

---

# 🎯 Key Takeaways

✔ Reduced manual mapping code.

✔ Improved code readability.

✔ Cleaner Service layer.

✔ Better maintainability.

✔ Production-ready mapping.

✔ Followed Spring Boot best practices.

---



---

⭐ If you found this project helpful, consider giving the repository a star!
