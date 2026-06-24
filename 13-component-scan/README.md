@ComponentScan in Spring Boot

Introduction

One of the most powerful features of Spring Framework is its ability to automatically discover and manage Beans.
But how does Spring know which classes should be created as Beans?
The answer is @ComponentScan.
@ComponentScan is responsible for scanning packages, identifying Spring components, and registering them as Beans in the Spring IoC Container.
Without Component Scanning, Spring would not know which classes should be managed.
________________________________________
What is @ComponentScan?
@ComponentScan is an annotation that tells Spring where to search for classes annotated with:
•	@Component
•	@Service
•	@Repository
•	@Controller
•	@RestController
When Spring finds these classes, it automatically creates and manages them as Beans.
Example:
@ComponentScan
public class AppConfig {

}
________________________________________
Why Do We Need @ComponentScan?
Imagine the following application:
com.example.demo
│
├── controller
│     └── ProductController
│
├── service
│     └── ProductService
│
├── repository
│     └── ProductRepository
│
└── Application.java
Spring needs a mechanism to discover these classes automatically.
@ComponentScan performs this task.
________________________________________
How @ComponentScan Works
Application Startup Flow:
Application Starts
        │
        ▼
@ComponentScan
        │
        ▼
Scan Packages
        │
        ▼
Find Components
        │
        ▼
Create Beans
        │
        ▼
Register in IoC Container
        │
        ▼
Application Ready
________________________________________
Component Scanning Example
Service Layer
@Service
public class ProductService {

}
Repository Layer
@Repository
public class ProductRepository {

}
Controller Layer
@RestController
public class ProductController {

}
When Spring starts, @ComponentScan detects these annotations and creates Bean instances automatically.
________________________________________
@SpringBootApplication and @ComponentScan
In Spring Boot applications, we usually don't write @ComponentScan manually.
Example:
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
Behind the scenes:
@SpringBootApplication
contains:
@Configuration
@EnableAutoConfiguration
@ComponentScan
This means Component Scanning is enabled automatically.
________________________________________
Package Scanning Rules
Spring scans:
Current Package
+
All Sub-Packages
Example:
com.example.demo
│
├── controller
├── service
├── repository
└── Application
Since Application.java is in the root package, Spring can discover all sub-packages automatically.
________________________________________
Custom Component Scanning
You can specify packages manually.
Example:
@ComponentScan("com.example.service")
public class AppConfig {

}
Spring scans only:
com.example.service
________________________________________
Multiple Package Scanning
@ComponentScan({
    "com.example.service",
    "com.example.repository"
})
Spring scans both packages.
________________________________________
Common Mistake
Suppose:
com.company.application
│
└── Application.java
and
com.company.service
│
└── ProductService.java
If ProductService is outside the scanned package hierarchy, Spring cannot find it.
You may get:
NoSuchBeanDefinitionException
or
Bean Not Found
errors.
________________________________________
Real-World Example
E-Commerce Application:
ProductController
        │
        ▼
ProductService
        │
        ▼
ProductRepository
        │
        ▼
Database
Spring automatically discovers all components using @ComponentScan and registers them in the ApplicationContext.
________________________________________
Benefits of @ComponentScan
1. Automatic Bean Discovery
No need to create Beans manually.
________________________________________
2. Less Configuration
Reduces XML and Java configuration.
________________________________________
3. Faster Development
Developers focus on business logic.
________________________________________
4. Better Maintainability
New components are discovered automatically.
________________________________________
5. Improved Readability
Clear application structure.
________________________________________
Interview Questions
Q1. What is @ComponentScan?
@ComponentScan tells Spring where to scan for Spring-managed components and Beans.
________________________________________
Q2. Which annotations are discovered by @ComponentScan?
•	@Component
•	@Service
•	@Repository
•	@Controller
•	@RestController
________________________________________
Q3. Does @SpringBootApplication include @ComponentScan?
Yes.
@SpringBootApplication internally contains:
•	@Configuration
•	@EnableAutoConfiguration
•	@ComponentScan
________________________________________
Q4. What packages does Spring scan by default?
The package containing the main application class and all its sub-packages.
________________________________________
Q5. What happens if a Bean is outside the scanned package?
Spring cannot find the Bean and may throw a NoSuchBeanDefinitionException.
________________________________________
Q6. Can we specify custom packages for scanning?
Yes.
Using:
@ComponentScan("com.example.service")
________________________________________
Summary
@ComponentScan is one of the core features of Spring Framework. It automatically discovers classes annotated with stereotype annotations and registers them as Beans in the Spring IoC Container.
It eliminates manual Bean registration, reduces configuration, and makes Spring applications easier to develop and maintain.
Understanding @ComponentScan is essential because it forms the foundation of Spring's automatic Bean management system.

📌 Next Topic: @Configuration and @Bean – Creating Beans Manually in Spring

