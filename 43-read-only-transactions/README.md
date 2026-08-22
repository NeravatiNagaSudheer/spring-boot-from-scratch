# 🚀 Spring Boot From Scratch – Day 43
## Read-Only Transactions & Rollback Rules

Day 43 of my **Spring Boot From Scratch** learning series.

Today I continued learning **Spring Transaction Management** and focused on:

- `@Transactional(readOnly = true)`
- Read vs Write Transactions
- Rollback behavior
- Runtime vs Checked Exceptions
- Transaction boundaries at the Service layer

These concepts were implemented and tested in my **NeoBank Customer Service** project.

---

# 📚 What is `@Transactional(readOnly = true)`?

`@Transactional(readOnly = true)` tells Spring that a transaction is intended for **reading data rather than modifying it**.

It can be used for service methods that perform operations such as:

- Fetching data
- Searching data
- Reading records

Example:

```java
@Transactional(readOnly = true)
public CustomerResponseDto getCustomerByID(Long id) {

    Customer customer = customerRepository.findById(id)
            .orElseThrow(() -> new CustomerNotFoundException(
                    "Customer Not Found with CustomerId :" + id));

    return customerMapper.toResponseDto(customer);
}
