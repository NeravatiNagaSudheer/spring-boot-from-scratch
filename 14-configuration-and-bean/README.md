@Configuration and @Bean in Spring

Introduction

In the previous topic, we learned how Spring automatically discovers Beans using @ComponentScan and stereotype annotations like @Component, @Service, @Repository, and @Controller.
But what if we need to create a Bean manually?
Spring provides two powerful annotations for this purpose:
•	@Configuration
•	@Bean
These annotations allow developers to manually configure and register Beans inside the Spring IoC Container.
________________________________________
What is @Configuration?
@Configuration is an annotation that tells Spring a class contains one or more Bean definitions.
Spring processes this class during application startup and registers all Beans defined within it.
Example:
@Configuration
public class AppConfig {

}
This class is called a Configuration Class.
________________________________________
Why Do We Need @Configuration?
Normally, Spring creates Beans automatically using:
•	@Component
•	@Service
•	@Repository
•	@Controller
However, sometimes we need more control.
Examples:
•	Third-party library classes
•	Custom object creation
•	Custom initialization logic
•	External configuration
This is where @Configuration becomes useful.
________________________________________
What is @Bean?
@Bean is used inside a @Configuration class.
It tells Spring that the object returned by the method should be managed as a Spring Bean.
Example:
@Configuration
public class AppConfig {

    @Bean
    public PaymentService paymentService() {
        return new PaymentService();
    }
}
When Spring starts:
•	It executes the method.
•	Creates the object.
•	Registers it as a Spring Bean.
•	Makes it available for Dependency Injection.
________________________________________
How @Bean Works
Application Starts
        │
        ▼
@Configuration Class Loaded
        │
        ▼
@Bean Method Executes
        │
        ▼
Object Created
        │
        ▼
Spring IoC Container
        │
        ▼
Bean Ready for Use
________________________________________
Example
PaymentService
public class PaymentService {

    public void pay() {
        System.out.println("Payment Successful");
    }

}
________________________________________
Configuration Class
@Configuration
public class AppConfig {

    @Bean
    public PaymentService paymentService() {
        return new PaymentService();
    }

}
________________________________________
Using the Bean
@RestController
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

}
Spring automatically injects the Bean.
________________________________________
Why Not Simply Use @Component?
If you own the class, you can simply use:
@Service
public class PaymentService {

}
But imagine a third-party class:
public class EmailClient {

}
You cannot modify its source code.
Instead:
@Configuration
public class EmailConfig {

    @Bean
    public EmailClient emailClient() {
        return new EmailClient();
    }

}
Now Spring manages it as a Bean.
________________________________________
@Component vs @Bean
@Component	@Bean
Automatic Bean Creation	Manual Bean Creation
Used on Classes	Used on Methods
Discovered using @ComponentScan	Declared inside @Configuration
Best for application classes	Best for third-party or custom objects
________________________________________
Real-World Example
Suppose you're integrating a third-party payment gateway.
Library provides:
public class StripeClient {

}
Since you cannot add Spring annotations to this class:
@Configuration
public class PaymentConfig {

    @Bean
    public StripeClient stripeClient() {
        return new StripeClient();
    }

}
Now Spring can inject it anywhere in your application.
________________________________________
Bean Registration Process
@Configuration
        │
        ▼
@Bean Method
        │
        ▼
Creates Object
        │
        ▼
Registers Bean
        │
        ▼
ApplicationContext
        │
        ▼
Dependency Injection
________________________________________
Benefits of @Configuration and @Bean
1. Manual Bean Registration
Provides complete control over Bean creation.
________________________________________
2. Third-Party Library Support
Allows Spring to manage objects from external libraries.
________________________________________
3. Custom Initialization
Configure objects before registering them.
________________________________________
4. Better Flexibility
Ideal for complex object creation logic.
________________________________________
5. Cleaner Configuration
Keeps configuration centralized and organized.
________________________________________
Interview Questions
Q1. What is @Configuration?
@Configuration indicates that a class contains Spring Bean definitions.
________________________________________
Q2. What is @Bean?
@Bean registers the object returned by a method as a Spring Bean.
________________________________________
Q3. Where can @Bean be used?
Inside a class annotated with @Configuration.
________________________________________
Q4. What is the difference between @Component and @Bean?
•	@Component automatically creates Beans by scanning classes.
•	@Bean manually creates Beans using methods.
________________________________________
Q5. When should you use @Bean instead of @Component?
Use @Bean when:
•	Working with third-party libraries
•	You cannot modify the source code
•	You need custom initialization logic
________________________________________
Q6. Does Spring manage Beans created using @Bean?
Yes.
Beans created using @Bean are fully managed by the Spring IoC Container, just like Beans created with @Component.
________________________________________
Summary
@Configuration and @Bean provide a powerful way to manually register Spring Beans.
While Spring Boot automatically creates most Beans using Component Scanning, there are situations where manual Bean creation is necessary—especially when integrating third-party libraries or implementing custom object creation logic.
Understanding these annotations is essential because they provide greater flexibility and complete control over how objects are created and managed within the Spring Framework.
📌 Next Topic: @Autowired – Automatic Dependency Injection in Spring

