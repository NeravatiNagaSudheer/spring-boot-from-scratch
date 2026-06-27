Dependency Injection and @Autowired in Spring

Prerequisites
Before learning Dependency Injection, you should understand:
•	Spring IoC Container
•	Spring Beans
•	ApplicationContext
•	@Component, @Service, @Repository, and @Controller
•	@Configuration and @Bean
________________________________________
Learning Objectives
By the end of this guide, you will understand:
•	What Dependency Injection is
•	Why Dependency Injection is important
•	How Spring performs Dependency Injection
•	What @Autowired does
•	Types of Dependency Injection
•	Why Constructor Injection is recommended
•	Best practices for Dependency Injection
________________________________________
Introduction
One of the core principles of the Spring Framework is Dependency Injection (DI).
Instead of classes creating their own dependencies, Spring provides the required objects automatically through the IoC (Inversion of Control) Container.
Dependency Injection promotes loose coupling, improves testability, and makes applications easier to maintain.
________________________________________
What is Dependency Injection?
Dependency Injection is a design pattern in which the Spring Framework provides the required dependencies (Beans) to a class instead of the class creating them manually.
Without Dependency Injection
•	Classes become tightly coupled.
•	Code becomes difficult to test.
•	Object creation logic is scattered throughout the application.
With Dependency Injection
•	Spring manages object creation.
•	Dependencies are injected automatically.
•	Classes remain loosely coupled.
________________________________________
Why Do We Need Dependency Injection?
Consider an Order Management System.
OrderService depends on PaymentService.
Without Dependency Injection, OrderService creates the dependency itself.
public class OrderService {

    private PaymentService paymentService = new PaymentService();

}
Problems
•	Tight Coupling
•	Difficult Unit Testing
•	Hard to Replace Implementations
•	Poor Maintainability
________________________________________
Dependency Injection with Spring
Spring automatically injects the required Bean.
@Service
public class OrderService {

    private final PaymentService paymentService;

    public OrderService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

}
Spring creates both Beans and injects PaymentService into OrderService.
________________________________________
How Dependency Injection Works
Application Starts
        │
        ▼
Spring IoC Container
        │
        ▼
Scans Components
        │
        ▼
Creates PaymentService Bean
        │
        ▼
Creates OrderService Bean
        │
        ▼
Injects PaymentService
        │
        ▼
Application Ready
________________________________________
Behind the Scenes
When Spring Boot starts:
1.	It scans the application packages.
2.	It discovers classes annotated with @Component, @Service, @Repository, and @Controller.
3.	It creates Bean instances.
4.	It stores them in the Spring IoC Container.
5.	It injects the required Beans wherever dependencies are needed.
This entire process happens automatically.
________________________________________
What is @Autowired?
@Autowired is an annotation used by Spring to automatically inject Beans.
Example:
@Service
public class OrderService {

    @Autowired
    private PaymentService paymentService;

}
Spring searches for a matching Bean and injects it automatically.
________________________________________
Types of Dependency Injection
Spring supports three types of Dependency Injection.
________________________________________
1. Constructor Injection ⭐ (Recommended)
@Service
public class OrderService {

    private final PaymentService paymentService;

    public OrderService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

}
Advantages
•	Immutable Dependencies
•	Easier Unit Testing
•	Required Dependencies are Explicit
•	Better Readability
•	Recommended by the Spring Team
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
Setter Injection is suitable when dependencies are optional or may change after object creation.
________________________________________
3. Field Injection
@Service
public class OrderService {

    @Autowired
    private PaymentService paymentService;

}
Although this works, it is generally not recommended for modern Spring Boot applications because it:
•	Makes testing harder
•	Hides required dependencies
•	Prevents immutable fields
•	Makes dependencies less explicit
________________________________________
Constructor Injection vs Field Injection
Constructor Injection	Field Injection
✅ Recommended	❌ Not Recommended for New Projects
Better Testability	Difficult to Test
Supports Immutability	Mutable Fields
Explicit Dependencies	Hidden Dependencies
Preferred in Modern Spring Boot	Mostly Used in Legacy Projects
________________________________________
Why is Constructor Injection Recommended?
Constructor Injection offers several advantages:
•	Better Unit Testing
•	Immutability
•	Clear Dependencies
•	Easier Refactoring
•	Improved Code Quality
If a class has only one constructor, Spring automatically injects the dependencies.
No @Autowired annotation is required.
@Service
public class PaymentService {

}
@Service
public class OrderService {

    private final PaymentService paymentService;

    public OrderService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

}
________________________________________
Advantages of Dependency Injection
•	Loose Coupling
•	Better Testability
•	Easier Maintenance
•	Improved Code Reusability
•	Cleaner Architecture
•	Simplified Object Management
________________________________________
Disadvantages
Although Dependency Injection offers many benefits, there are a few challenges:
•	Slight learning curve for beginners
•	Incorrect Bean configuration can cause runtime errors
•	Understanding the Bean lifecycle requires practice
________________________________________
Real-World Example
Consider an E-Commerce application.
ProductController
        │
        ▼
ProductService
        │
        ▼
ProductRepository
        │
        ▼
PostgreSQL Database
Spring automatically injects:
•	ProductService into ProductController
•	ProductRepository into ProductService
Developers never need to create these objects manually.
________________________________________
Common Mistakes
1. Creating Objects Using new
❌
private PaymentService paymentService = new PaymentService();
Always let Spring manage Spring Beans.
________________________________________
2. Using Field Injection Everywhere
Although valid, Constructor Injection is the preferred approach for new Spring Boot applications.
________________________________________
3. Forgetting Spring Annotations
Classes must be registered as Spring Beans using:
•	@Component
•	@Service
•	@Repository
•	@Controller
•	@Bean
Otherwise, Spring cannot inject them.
________________________________________
Interview Questions
Q1. What is Dependency Injection?
Dependency Injection is a design pattern where Spring provides the required objects (Beans) to a class instead of the class creating them manually.
________________________________________
Q2. What is @Autowired?
@Autowired is an annotation that tells Spring to automatically inject a matching Bean into a class.
________________________________________
Q3. What are the types of Dependency Injection?
•	Constructor Injection
•	Setter Injection
•	Field Injection
________________________________________
Q4. Which Dependency Injection method is recommended?
Constructor Injection.
________________________________________
Q5. Why is Constructor Injection preferred?
•	Better Testability
•	Immutability
•	Explicit Dependencies
•	Cleaner Code
________________________________________
Q6. Is @Autowired mandatory for Constructor Injection?
No.
If a class has only one constructor, Spring automatically performs Constructor Injection.
________________________________________
Q7. Why should we avoid Field Injection?
Because it hides dependencies, makes testing difficult, and prevents immutable design.
________________________________________
Real Interview Scenario
Question:
Why do most companies prefer Constructor Injection instead of Field Injection?
Answer:
Constructor Injection:
•	Makes dependencies mandatory
•	Supports immutable fields using final
•	Improves unit testing
•	Makes code easier to understand
•	Is the recommended approach in modern Spring Boot applications
For these reasons, most enterprise projects use Constructor Injection.
________________________________________
Best Practices
✅ Prefer Constructor Injection.
✅ Keep dependencies immutable using final.
✅ Let Spring manage object creation.
✅ Avoid using the new keyword for Spring Beans.
✅ Use Field Injection only when maintaining legacy applications.
________________________________________
Key Takeaways
•	Dependency Injection is one of the core features of Spring Framework.
•	Spring manages object creation through the IoC Container.
•	@Autowired enables automatic Bean injection.
•	Constructor Injection is the preferred approach.
•	Avoid creating Spring-managed objects using the new keyword.
•	Prefer loose coupling over tight coupling.
________________________________________
Summary
Dependency Injection is one of the foundational concepts of the Spring Framework. It enables Spring to manage object creation and automatically inject dependencies between Beans, resulting in loosely coupled, testable, and maintainable applications.
While Spring supports Constructor, Setter, and Field Injection, Constructor Injection is the recommended approach because it improves readability, testability, and overall code quality.
________________________________________
What's Next?
In the next chapter, we'll learn about @Qualifier and understand how Spring resolves dependency conflicts when multiple Beans of the same type exist.
📌 Next Topic: @Qualifier – Resolving Multiple Bean Conflicts


