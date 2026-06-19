Bean Lifecycle in Spring

Introduction

Spring Beans are managed by the Spring IoC Container.
From the moment a Bean is created until it is destroyed, it goes through several lifecycle phases.
This entire journey is known as the Bean Lifecycle.
Understanding the Bean Lifecycle helps developers manage resources efficiently, initialize components properly, and perform cleanup operations when the application shuts down.
________________________________________
What is Bean Lifecycle?
Bean Lifecycle describes the sequence of steps a Spring Bean follows inside the IoC Container.
A Bean typically goes through:
Bean Creation
      │
      ▼
Dependency Injection
      │
      ▼
Initialization
      │
      ▼
Ready for Use
      │
      ▼
Bean Destruction
Spring manages all these phases automatically.
________________________________________
Bean Lifecycle Flow
Application Starts
        │
        ▼
Create Bean
        │
        ▼
Inject Dependencies
        │
        ▼
@PostConstruct
        │
        ▼
Bean Ready
        │
        ▼
Application Running
        │
        ▼
@PreDestroy
        │
        ▼
Bean Destroyed
________________________________________
Step 1: Bean Creation
Spring creates the Bean object.
Example:
@Service
public class PaymentService {

}
When Spring detects this class during component scanning, it creates a Bean instance.
________________________________________
Step 2: Dependency Injection
Spring injects all required dependencies.
Example:
@Service
public class OrderService {

    private final PaymentService paymentService;

    public OrderService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
}
Before the Bean becomes usable, Spring ensures all dependencies are available.
________________________________________
Step 3: Initialization
After dependency injection is complete, Spring performs initialization.
This is the ideal place to:
•	Load configuration
•	Initialize caches
•	Open resources
•	Validate startup data
Using @PostConstruct
@Component
public class PaymentService {

    @PostConstruct
    public void init() {
        System.out.println("Bean Initialized");
    }
}
The method executes automatically after dependency injection.
________________________________________
Step 4: Bean Ready for Use
After initialization:
Bean Created
      │
      ▼
Dependencies Injected
      │
      ▼
Initialization Complete
      │
      ▼
Ready to Serve Requests
The Bean is now fully managed by Spring and can be used throughout the application.
________________________________________
Step 5: Bean Destruction
When the application shuts down, Spring destroys managed Beans.
This phase is useful for:
•	Closing database connections
•	Releasing resources
•	Stopping background processes
•	Cleaning up memory
Using @PreDestroy
@Component
public class PaymentService {

    @PreDestroy
    public void cleanup() {
        System.out.println("Bean Destroyed");
    }
}
This method executes before Spring removes the Bean.
________________________________________
Complete Lifecycle Example
@Component
public class PaymentService {

    @PostConstruct
    public void init() {
        System.out.println("PaymentService Initialized");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("PaymentService Destroyed");
    }
}
Output:
PaymentService Initialized
Application Running...
PaymentService Destroyed
________________________________________
Bean Lifecycle in Real Projects
Example 1: Database Connections
Application Starts
      │
      ▼
Initialize Database Resources
      │
      ▼
Application Running
      │
      ▼
Close Resources
________________________________________
Example 2: Cache Loading
Application Starts
      │
      ▼
Load Cache Data
      │
      ▼
Serve Requests Faster
      │
      ▼
Clear Cache
________________________________________
Bean Lifecycle Interfaces
Spring also provides lifecycle interfaces.
InitializingBean
public class PaymentService implements InitializingBean {

    @Override
    public void afterPropertiesSet() {
        System.out.println("Initialized");
    }
}
________________________________________
DisposableBean
public class PaymentService implements DisposableBean {

    @Override
    public void destroy() {
        System.out.println("Destroyed");
    }
}
However, modern Spring applications generally prefer:
•	@PostConstruct
•	@PreDestroy
because they are simpler and more readable.
________________________________________
Bean Lifecycle and Scope
Singleton Bean
Default Scope
Application Starts
      │
      ▼
One Bean Created
      │
      ▼
Shared Across Application
________________________________________
Prototype Bean
Every Request
      │
      ▼
New Bean Instance
Important:
Spring does not automatically manage destruction of Prototype Beans.
________________________________________
Benefits of Understanding Bean Lifecycle
Better Resource Management
Proper initialization and cleanup.
________________________________________
Improved Performance
Load resources once during startup.
________________________________________
Easier Maintenance
Predictable application behavior.
________________________________________
Reduced Memory Leaks
Resources are released properly.
________________________________________
Interview Questions
Q1. What is Bean Lifecycle in Spring?
Bean Lifecycle refers to the sequence of stages a Bean goes through from creation to destruction.
________________________________________
Q2. What annotations are commonly used during Bean Lifecycle?
•	@PostConstruct
•	@PreDestroy
________________________________________
Q3. When is @PostConstruct executed?
After dependency injection and before the Bean becomes available.
________________________________________
Q4. When is @PreDestroy executed?
Just before the Bean is removed from the container.
________________________________________
Q5. What is the default Bean Scope?
Singleton.
________________________________________
Q6. Does Spring manage destruction of Prototype Beans?
No.
Spring creates Prototype Beans but does not automatically destroy them.
________________________________________
Summary
Bean Lifecycle describes how Spring creates, initializes, manages, and destroys Beans inside the IoC Container.
Understanding Bean Lifecycle is important for resource management, application stability, and efficient Spring application design.
By using @PostConstruct and @PreDestroy, developers can execute initialization and cleanup logic at the appropriate stages of a Bean's lifecycle.
📌 Next Topic: ApplicationContext in Spring

