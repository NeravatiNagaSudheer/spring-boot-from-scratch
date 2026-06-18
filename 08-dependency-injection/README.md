Dependency Injection (DI) in Spring

Introduction

Dependency Injection (DI) is one of the core concepts of the Spring Framework.
It is a design pattern that allows Spring to provide the required dependencies to a class instead of the class creating those dependencies manually.
Dependency Injection helps applications become loosely coupled, easier to test, and easier to maintain.
Spring implements Dependency Injection through its IoC (Inversion of Control) Container.
________________________________________
What is Dependency Injection?
A dependency is an object that another object requires to perform its work.
For example:
public class OrderService {

    private PaymentService paymentService = new PaymentService();

}
In this example:
•	OrderService depends on PaymentService.
•	OrderService creates PaymentService manually.
•	The classes become tightly coupled.
This approach creates maintenance and testing challenges.
Dependency Injection solves this problem.
________________________________________
Dependency Injection in Spring
Instead of creating dependencies manually:
private PaymentService paymentService = new PaymentService();
Spring creates the dependency and injects it automatically.
Example:
@Service
public class OrderService {

    private final PaymentService paymentService;

    public OrderService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
}
Spring automatically provides the PaymentService Bean.
________________________________________
How Dependency Injection Works
OrderService
      │
      ▼
Needs PaymentService
      │
      ▼
Spring IoC Container
      │
      ▼
Creates PaymentService Bean
      │
      ▼
Injects into OrderService
      │
      ▼
Application Ready
The developer focuses on business logic while Spring handles dependency management.
________________________________________
Why Do We Need Dependency Injection?
Without Dependency Injection:
•	Tight Coupling
•	Difficult Testing
•	Harder Maintenance
•	Less Flexibility
With Dependency Injection:
•	Loose Coupling
•	Better Testability
•	Easier Maintenance
•	Improved Reusability
•	Cleaner Code
________________________________________
Types of Dependency Injection
Spring supports three types of Dependency Injection.
1. Constructor Injection (Recommended)
@Service
public class OrderService {

    private final PaymentService paymentService;

    public OrderService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
}
Advantages:
•	Immutable Dependencies
•	Easier Testing
•	Recommended by Spring Team
________________________________________
2. Setter Injection
@Service
public class OrderService {

    private PaymentService paymentService;

    @Autowired
    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
}
Advantages:
•	Optional Dependencies
Disadvantages:
•	Object can be created without required dependencies.
________________________________________
3. Field Injection
@Service
public class OrderService {

    @Autowired
    private PaymentService paymentService;
}
Advantages:
•	Less Code
Disadvantages:
•	Difficult Testing
•	Not Recommended for Production Applications
________________________________________
Constructor Injection vs Field Injection
Constructor Injection	Field Injection
Recommended	Not Recommended
Easy Testing	Difficult Testing
Immutable Dependencies	Mutable Dependencies
Better Design	Less Flexible
Most modern Spring Boot applications use Constructor Injection.
________________________________________
Real-World Example
Consider an E-Commerce Application.
Services
OrderService
PaymentService
InventoryService
NotificationService
Without Dependency Injection:
OrderService creates PaymentService
OrderService creates InventoryService
OrderService creates NotificationService
This creates tight coupling.
With Dependency Injection:
Spring Container
      │
      ├── PaymentService
      ├── InventoryService
      ├── NotificationService
      │
      ▼
Injects into OrderService
Spring manages all dependencies automatically.
________________________________________
Benefits of Dependency Injection
1. Loose Coupling
Classes depend on abstractions rather than concrete implementations.
________________________________________
2. Better Testability
Dependencies can be mocked easily during testing.
________________________________________
3. Easier Maintenance
Changes can be made without modifying dependent classes.
________________________________________
4. Improved Reusability
Components can be reused across the application.
________________________________________
5. Cleaner Architecture
Business logic remains separate from object creation logic.
________________________________________
Interview Questions
Q1. What is Dependency Injection?
Dependency Injection is a design pattern where dependencies are provided by the Spring Container rather than created manually.
________________________________________
Q2. What problem does Dependency Injection solve?
It reduces tight coupling between classes.
________________________________________
Q3. What are the types of Dependency Injection?
•	Constructor Injection
•	Setter Injection
•	Field Injection
________________________________________
Q4. Which type of Dependency Injection is recommended?
Constructor Injection.
________________________________________
Q5. Why is Constructor Injection preferred?
•	Easier Testing
•	Immutable Dependencies
•	Better Design
________________________________________
Q6. What is the relationship between IoC and DI?
•	IoC is a design principle.
•	Dependency Injection is a technique used to implement IoC.
________________________________________
Summary
Dependency Injection is one of the most important features of Spring Framework. It allows Spring to manage and provide dependencies automatically, reducing coupling and improving application maintainability.
By using Dependency Injection, developers can build applications that are easier to test, easier to maintain, and more scalable.
Understanding Dependency Injection is essential before learning Bean Lifecycle and ApplicationContext.
📌 Next Topic: Bean Lifecycle in Spring


