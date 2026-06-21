ApplicationContext in Spring
Introduction
ApplicationContext is one of the most important components of the Spring Framework.
It is an advanced implementation of the IoC (Inversion of Control) Container responsible for creating, configuring, managing, and providing Spring Beans throughout the application lifecycle.
Every Spring Boot application uses an ApplicationContext behind the scenes.
Understanding ApplicationContext helps developers understand how Spring manages Beans, performs Dependency Injection, and handles application configuration.
________________________________________
What is ApplicationContext?
ApplicationContext is a central interface in the Spring Framework that manages application components.
It is responsible for:
•	Creating Beans
•	Managing Bean Lifecycle
•	Performing Dependency Injection
•	Managing Configuration
•	Publishing Events
•	Loading Resources
In simple terms:
ApplicationContext
        │
        ▼
 Creates Beans
 Injects Dependencies
 Manages Lifecycle
 Provides Configuration
________________________________________
Why Do We Need ApplicationContext?
Without ApplicationContext:
PaymentService paymentService = new PaymentService();
OrderService orderService = new OrderService();
Developers must manually create and manage objects.
Problems:
•	Tight Coupling
•	Difficult Testing
•	Complex Dependency Management
•	Harder Maintenance
With ApplicationContext:
PaymentService paymentService =
        context.getBean(PaymentService.class);
Spring handles object creation and dependency management automatically.
________________________________________
ApplicationContext and IoC
ApplicationContext is an implementation of the IoC Container.
Relationship:
IoC Container
      │
      ▼
ApplicationContext
      │
      ▼
Manages Beans
The IoC principle defines object management.
ApplicationContext provides the implementation.
________________________________________
How ApplicationContext Works
Step 1: Application Starts
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
________________________________________
Step 2: Component Scanning
Spring scans the application for:
•	@Component
•	@Service
•	@Repository
•	@Controller
Example:
@Service
public class PaymentService {

}
________________________________________
Step 3: Bean Creation
Spring creates Bean instances.
ApplicationContext
        │
        ├── PaymentService
        ├── OrderService
        ├── ProductService
        └── UserService
________________________________________
Step 4: Dependency Injection
Spring injects required dependencies.
Example:
@Service
public class OrderService {

    private final PaymentService paymentService;

    public OrderService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
}
________________________________________
Step 5: Application Ready
Beans become available for use throughout the application.
________________________________________
Creating ApplicationContext Manually
Example:
ApplicationContext context =
        new AnnotationConfigApplicationContext(AppConfig.class);
Retrieve a Bean:
PaymentService paymentService =
        context.getBean(PaymentService.class);
Spring returns the managed Bean instance.
________________________________________
Common Methods of ApplicationContext
Get Bean by Type
PaymentService paymentService =
        context.getBean(PaymentService.class);
________________________________________
Get Bean by Name
PaymentService paymentService =
        (PaymentService) context.getBean("paymentService");
________________________________________
Check Bean Existence
context.containsBean("paymentService");
________________________________________
Features of ApplicationContext
1. Bean Management
Creates and manages Beans automatically.
________________________________________
2. Dependency Injection
Injects dependencies between Beans.
________________________________________
3. Event Handling
Supports application events.
Example:
ApplicationStartedEvent
ApplicationReadyEvent
ContextClosedEvent
________________________________________
4. Internationalization (i18n)
Supports multiple languages.
________________________________________
5. Resource Loading
Loads files and resources.
Example:
Resource resource =
        context.getResource("classpath:data.txt");
________________________________________
BeanFactory vs ApplicationContext
BeanFactory	ApplicationContext
Basic IoC Container	Advanced IoC Container
Lazy Loading	Eager Loading
Limited Features	Additional Features
Less Common	Most Common
Most Spring Boot applications use ApplicationContext.
________________________________________
Real-World Example
Consider an E-Commerce Application.
Components:
ProductService
OrderService
PaymentService
NotificationService
ApplicationContext manages all these Beans.
ApplicationContext
        │
        ├── ProductService
        ├── OrderService
        ├── PaymentService
        └── NotificationService
Whenever one service needs another, Spring provides the dependency automatically.
________________________________________
Benefits of ApplicationContext
Loose Coupling
Classes do not create dependencies manually.
________________________________________
Better Testability
Dependencies can be mocked easily.
________________________________________
Easier Maintenance
Object creation is centralized.
________________________________________
Improved Productivity
Developers focus on business logic.
________________________________________
Consistent Configuration
Spring manages application configuration uniformly.
________________________________________
Interview Questions
Q1. What is ApplicationContext?
ApplicationContext is an advanced IoC Container responsible for creating, configuring, and managing Spring Beans.
________________________________________
Q2. What is the difference between BeanFactory and ApplicationContext?
ApplicationContext provides additional features such as event handling, internationalization, and eager bean loading.
________________________________________
Q3. How do you retrieve a Bean from ApplicationContext?
context.getBean(PaymentService.class);
________________________________________
Q4. Does Spring Boot use ApplicationContext?
Yes.
Every Spring Boot application starts an ApplicationContext automatically.
________________________________________
Q5. What are some features provided by ApplicationContext?
•	Bean Management
•	Dependency Injection
•	Event Handling
•	Resource Loading
•	Internationalization
________________________________________
Q6. Is ApplicationContext an IoC Container?
Yes.
ApplicationContext is one of Spring's primary IoC Container implementations.
________________________________________
Summary
ApplicationContext is the heart of the Spring Framework. It acts as an advanced IoC Container responsible for creating Beans, managing dependencies, handling Bean lifecycles, and providing application-wide services.
Understanding ApplicationContext is essential because it connects all core Spring concepts such as IoC, Beans, Dependency Injection, and Bean Lifecycle.
📌 Next Topic: Bean Scopes in Spring (Singleton vs Prototype)

