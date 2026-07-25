MapStruct Integration

## 📖 Overview

In this module, I integrated **MapStruct** into the Customer Service of my NeoBank Backend project to eliminate repetitive object mapping code and make the application cleaner, more maintainable, and production-ready.

Instead of manually converting between **Entity** and **DTO** objects, MapStruct automatically generates the mapping implementation during compile time.

---

## 🚀 What is MapStruct?

**MapStruct** is a Java annotation processor that generates type-safe mapping code at compile time.

Unlike reflection-based mapping libraries, MapStruct generates plain Java code, making it fast, efficient, and easy to debug.

---

## ✨ Why MapStruct?

- Eliminates boilerplate mapping code
- Generates mapping code at compile time
- Improves readability
- Better performance than reflection-based mappers
- Keeps the Service layer clean
- Easier to maintain and extend

---

## 🛠️ Features Implemented

### ✅ Request DTO → Entity Mapping

```java
Customer customer = customerMapper.toEntity(customerRequestDto);
```

---

### ✅ Entity → Response DTO Mapping

```java
return customerMapper.toResponseDto(savedCustomer);
```

---

### ✅ List Mapping

```java
return customerMapper.toResponseDtoList(customerRepository.findAll());
```

---

### ✅ Update Existing Entity using @MappingTarget

```java
customerMapper.updateCustomerFromDto(customerRequestDto, existingCustomer);
```

This updates only the existing entity without creating a new object.

---

## 📂 Customer Mapper

```java
@Mapper(componentModel = "spring")
public interface CustomerMapper {

    Customer toEntity(CustomerRequestDto dto);

    CustomerResponseDto toResponseDto(Customer customer);

    List<CustomerResponseDto> toResponseDtoList(List<Customer> customers);

    void updateCustomerFromDto(
            CustomerRequestDto dto,
            @MappingTarget Customer customer);
}
```

---

## 📌 Service Layer Before MapStruct

Manual mapping required multiple setter methods.

```java
Customer customer = new Customer();

customer.setFirstName(dto.getFirstName());
customer.setLastName(dto.getLastName());
customer.setEmail(dto.getEmail());
customer.setPhoneNumber(dto.getPhoneNumber());
customer.setDateOfBirth(dto.getDateOfBirth());
```

---

## 📌 Service Layer After MapStruct

```java
Customer customer = customerMapper.toEntity(customerRequestDto);

Customer savedCustomer = customerRepository.save(customer);

return customerMapper.toResponseDto(savedCustomer);
```

---

## 📌 Update Operation

Instead of manually updating every field,

```java
customerMapper.updateCustomerFromDto(
        customerRequestDto,
        existingCustomer);
```

MapStruct updates the existing entity automatically.

---

## 🔄 Application Flow

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
Database
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

## 📚 Concepts Learned

- DTO Pattern
- Entity ↔ DTO Mapping
- Compile-time code generation
- MapStruct Configuration
- Spring Bean Integration
- List Mapping
- @MappingTarget
- Cleaner Service Layer
- Production-ready object mapping

---

## 🎯 Key Takeaways

- Reduced hundreds of lines of repetitive mapping code.
- Improved readability and maintainability.
- Learned how MapStruct generates mapping code during compilation.
- Built a cleaner Spring Boot application following enterprise development practices.

---

## 🚀 Next Module

**Day 37 – Advanced MapStruct Features**

- Custom Field Mapping
- Nested Object Mapping
- Expressions
- Constants
- Ignore Fields
- QualifiedByName
- Null Value Strategies
- Custom Methods
