Spring Stereotype Annotations (@Component, @Service, @Repository, @Controller)

Introduction

Spring Framework uses Stereotype Annotations to automatically detect and manage application components as Spring Beans.
These annotations help organize applications into different layers and make the code more readable, maintainable, and scalable.
The most commonly used Stereotype Annotations are:
•	@Component
•	@Service
•	@Repository
•	@Controller
Although all of them create Spring Beans, each annotation has a specific purpose within the application architecture.

________________________________________
What are Stereotype Annotations?
Stereotype Annotations are special annotations used to indicate the role of a class within a Spring application.
When Spring performs Component Scanning, it detects these annotations and automatically creates Bean instances.
Application Startup
         │
         ▼
Component Scanning
         │
         ▼
Detect Stereotype Annotations
         │
         ▼
Create Spring Beans
________________________________________
1. @Component
What is @Component?
@Component is the most generic stereotype annotation.
It tells Spring that the class should be managed as a Spring Bean.
Example:
@Component
public class EmailUtility {

    public void sendEmail() {
        System.out.println("Email Sent");
    }
}
Spring automatically creates and manages this Bean.
________________________________________
When to Use @Component?
Use @Component when the class does not belong specifically to:
•	Service Layer
•	Repository Layer
•	Controller Layer
Examples:
•	Utility Classes
•	Helper Classes
•	Common Components
________________________________________
2. @Service
What is @Service?
@Service is a specialized form of @Component.
It is used for classes that contain business logic.
Example:
@Service
public class PaymentService {

    public void processPayment() {
        System.out.println("Payment Processed");
    }
}
________________________________________
When to Use @Service?
Examples:
•	Payment Processing
•	Order Management
•	User Management
•	Business Rules
Controller
      │
      ▼
Service
      │
      ▼
Repository
The Service Layer acts as the bridge between Controllers and Repositories.
________________________________________
3. @Repository
What is @Repository?
@Repository is used for database access classes.
It indicates that the class is responsible for interacting with the database.
Example:
@Repository
public interface ProductRepository
        extends JpaRepository<Product, Long> {

}
________________________________________
Responsibilities
•	CRUD Operations
•	Database Queries
•	Data Access Logic
Examples:
productRepository.save(product);

productRepository.findById(id);

productRepository.deleteById(id);
________________________________________
Additional Benefit
@Repository provides automatic exception translation.
Database exceptions are converted into Spring DataAccessException.
________________________________________
4. @Controller
What is @Controller?
@Controller is used in the presentation layer.
It handles incoming HTTP requests.
Example:
@Controller
public class ProductController {

}
________________________________________
Responsibilities
•	Receive Requests
•	Process User Input
•	Return Responses
Flow:
Client Request
        │
        ▼
@Controller
        │
        ▼
@Service
        │
        ▼
@Repository
        │
        ▼
Database
________________________________________
@RestController
Modern Spring Boot applications often use:
@RestController
public class ProductController {

}
@RestController is equivalent to:
@Controller
@ResponseBody
It automatically converts Java objects into JSON responses.
Example:
@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping
    public String getProducts() {
        return "Products Retrieved";
    }
}
Response:
{
  "message": "Products Retrieved"
}
________________________________________
Relationship Between All Annotations
                 @Component
                      │
      ┌───────────────┼───────────────┐
      ▼               ▼               ▼
   @Service      @Repository     @Controller
All are Spring Beans.
The difference lies in their purpose.
________________________________________
Layered Architecture Example
Consider an E-Commerce Application.
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
Code Example:
Controller
@RestController
@RequestMapping("/products")
public class ProductController {

}
Service
@Service
public class ProductService {

}
Repository
@Repository
public interface ProductRepository
        extends JpaRepository<Product, Long> {

}
________________________________________
Why Use Different Annotations?
Instead of using @Component everywhere:
@Component
public class ProductService {

}
Use:
@Service
public class ProductService {

}
Benefits:
•	Better Readability
•	Clear Separation of Concerns
•	Easier Maintenance
•	Better Team Collaboration
________________________________________
Interview Questions
Q1. What are Stereotype Annotations?
Stereotype Annotations are annotations used by Spring to identify and manage application components as Beans.
Examples:
•	@Component
•	@Service
•	@Repository
•	@Controller
________________________________________
Q2. What is the difference between @Component and @Service?
@Service is a specialized version of @Component used for business logic classes.
________________________________________
Q3. What is the purpose of @Repository?
@Repository is used for database access and provides automatic exception translation.
________________________________________
Q4. What is the difference between @Controller and @RestController?
@Controller is used for web applications and views.
@RestController returns JSON/XML responses directly.
________________________________________
Q5. Are all Stereotype Annotations Spring Beans?
Yes.
All Stereotype Annotations create Spring-managed Beans.
________________________________________
Q6. Which annotation is used for business logic?
@Service
________________________________________
Summary
Stereotype Annotations help Spring identify and manage application components automatically.
While all of them create Spring Beans, each annotation has a specific responsibility:
•	@Component → Generic Components
•	@Service → Business Logic
•	@Repository → Database Access
•	@Controller → Request Handling
Using the correct annotation improves code organization, readability, and maintainability.
📌 Next Topic: Component Scanning (@ComponentScan)


