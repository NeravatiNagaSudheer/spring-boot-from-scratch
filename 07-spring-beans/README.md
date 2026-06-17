Spring Beans in Spring Framework
Introduction
Spring Beans are one of the most fundamental concepts in the Spring Framework.
Every Spring application is built around Beans. They are the objects that Spring creates, manages, and maintains inside the IoC (Inversion of Control) Container.
Understanding Beans is essential because Dependency Injection, Bean Lifecycle, and ApplicationContext all rely on Beans.
________________________________________
What is a Spring Bean?
A Spring Bean is an object that is created, configured, and managed by the Spring IoC Container.
Instead of creating objects manually:
PaymentService paymentService = new PaymentService();
Spring creates and manages the object automatically.
Example:
@Service
public class PaymentService {

}
When the application starts, Spring creates an instance of PaymentService and stores it in the IoC Container.
________________________________________
Why Do We Need Beans?
Without Spring Beans:
•	Developers create objects manually.
•	Applications become tightly coupled.
•	Testing becomes difficult.
•	Code maintenance becomes harder.
With Spring Beans:
•	Objects are managed centrally.
•	Dependencies are injected automatically.
•	Applications become loosely coupled.
•	Maintenance becomes easier.
________________________________________
How Spring Creates Beans
Step 1: Application Starts
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
________________________________________
Step 2: Component Scanning Begins
Spring scans the application packages.
Application Starts
        │
        ▼
Component Scan
________________________________________
Step 3: Spring Detects Components
Spring identifies classes annotated with:
•	@Component
•	@Service
•	@Repository
•	@Controller
Example:
@Service
public class PaymentService {

}
________________________________________
Step 4: Bean Creation
Spring creates an object and stores it inside the IoC Container.
Spring IoC Container
       │
       ├── PaymentService Bean
       ├── OrderService Bean
       ├── ProductService Bean
       └── UserService Bean
________________________________________
Common Bean Annotations
@Component
Generic Spring Bean.
@Component
public class NotificationService {

}
________________________________________
@Service
Used for business logic.
@Service
public class PaymentService {

}
________________________________________
@Repository
Used for database access.
@Repository
public class ProductRepository {

}
________________________________________
@Controller
Used for handling web requests.
@Controller
public class ProductController {

}
________________________________________
Bean Naming
By default, Spring generates bean names using the class name.
Example:
@Service
public class PaymentService {

}
Bean Name:
paymentService
________________________________________
Bean Scope
Singleton (Default)
Only one Bean instance exists in the application.
@Service
public class PaymentService {

}
Spring creates:
One Instance
and shares it throughout the application.
________________________________________
Prototype
A new Bean instance is created every time it is requested.
@Component
@Scope("prototype")
public class NotificationService {

}
________________________________________
Real-World Example
Consider an E-Commerce Application.
Services:
OrderService
PaymentService
InventoryService
NotificationService
Spring creates and manages all these Beans.
Spring Container
      │
      ├── OrderService
      ├── PaymentService
      ├── InventoryService
      └── NotificationService
Whenever one service needs another, Spring provides the required Bean automatically.
________________________________________
Benefits of Spring Beans
1. Loose Coupling
Objects do not create dependencies manually.
________________________________________
2. Better Testability
Beans can be mocked easily during testing.
________________________________________
3. Easier Maintenance
Spring manages object creation and configuration.
________________________________________
4. Reusability
Beans can be reused throughout the application.
________________________________________
5. Improved Productivity
Developers focus on business logic instead of object management.
________________________________________
Interview Questions
Q1. What is a Spring Bean?
A Spring Bean is an object that is created, configured, and managed by the Spring IoC Container.
________________________________________
Q2. Where are Beans stored?
Beans are stored inside the Spring IoC Container.
________________________________________
Q3. Which annotations create Beans?
•	@Component
•	@Service
•	@Repository
•	@Controller
________________________________________
Q4. What is the default Bean Scope?
Singleton.
________________________________________
Q5. What is the difference between @Component and @Service?
@Service is a specialization of @Component used for business logic classes.
________________________________________
Q6. How are Beans discovered?
Using Component Scanning.
________________________________________
Summary
Spring Beans are the core building blocks of Spring applications. They are objects managed by the Spring IoC Container and are responsible for enabling Dependency Injection and loose coupling.
Understanding Beans is crucial before learning Dependency Injection, Bean Lifecycle, and ApplicationContext because these concepts are built on top of Spring Beans.
📌 Next Topic: Dependency Injection in Spring

