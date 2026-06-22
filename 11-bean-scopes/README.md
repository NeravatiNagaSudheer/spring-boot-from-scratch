
Bean Scopes in Spring (Singleton vs Prototype)

Introduction

Spring manages objects called Beans inside the IoC Container.
One important aspect of Bean management is Bean Scope.
Bean Scope determines how many instances of a Bean Spring should create and how those instances are shared throughout the application.
Understanding Bean Scopes is important for writing efficient and scalable Spring applications.
________________________________________
What is Bean Scope?
A Bean Scope defines the lifecycle and visibility of a Bean within the Spring Container.
In simple terms:
Bean Scope = How many Bean instances Spring creates
Spring provides multiple Bean Scopes, but the most commonly used are:
•	Singleton
•	Prototype
________________________________________
Why Do We Need Bean Scopes?
Different application components have different requirements.
For example:
•	A PaymentService can be shared by all users.
•	A ShoppingCart may need a separate instance for each user.
Bean Scopes help Spring manage these scenarios efficiently.
________________________________________
1. Singleton Scope
Singleton is the default Bean Scope in Spring.
Spring creates only one instance of the Bean and shares it throughout the application.
Example:
@Service
public class PaymentService {

}
Spring creates:
PaymentService Bean
Only once.
Every class that requires PaymentService receives the same instance.
________________________________________
Singleton Scope Flow
User 1
      │
User 2 ─────► Same Bean Instance
      │
User 3
________________________________________
Characteristics of Singleton Scope
•	Default Scope
•	One Instance Per Spring Container
•	Shared Across Application
•	Memory Efficient
•	Suitable for Stateless Services
________________________________________
Real-World Example
@Service
public class ProductService {

}
All requests use the same ProductService instance.
This reduces memory consumption and improves performance.
________________________________________
2. Prototype Scope
Prototype Scope creates a new Bean instance every time it is requested.
Example:
@Component
@Scope("prototype")
public class NotificationService {

}
Spring creates:
Request 1 → Bean Instance 1

Request 2 → Bean Instance 2

Request 3 → Bean Instance 3
Each request receives a new object.
________________________________________
Prototype Scope Flow
User 1 ─► NotificationService #1

User 2 ─► NotificationService #2

User 3 ─► NotificationService #3
________________________________________
Characteristics of Prototype Scope
•	New Instance Every Request
•	Not Shared
•	Higher Memory Usage
•	Suitable for Stateful Objects
________________________________________
Singleton vs Prototype
Feature	Singleton	Prototype
Instances	One	Multiple
Default Scope	Yes	No
Memory Usage	Lower	Higher
Sharing	Shared	Not Shared
Use Case	Stateless Services	Stateful Components
________________________________________
Additional Bean Scopes
Spring also supports web-specific scopes.
Request Scope
One Bean per HTTP Request.
@RequestScope
@Component
public class UserRequestData {

}
________________________________________
Session Scope
One Bean per User Session.
@SessionScope
@Component
public class ShoppingCart {

}
________________________________________
Application Scope
One Bean shared across the entire web application.
________________________________________
Bean Scope Example
@Component
@Scope("prototype")
public class ReportGenerator {

}
Whenever Spring is asked for this Bean:
context.getBean(ReportGenerator.class);
A new instance is created.
________________________________________
When Should You Use Singleton?
Use Singleton when:
•	Service Classes
•	Repository Classes
•	Utility Classes
•	Stateless Components
Example:
@Service
public class OrderService {

}
This is the most common approach.
________________________________________
When Should You Use Prototype?
Use Prototype when:
•	Object State Changes Frequently
•	Independent Instances Are Required
•	Temporary Processing Objects
Example:
@Component
@Scope("prototype")
public class FileProcessor {

}
________________________________________
Interview Questions
Q1. What is Bean Scope?
Bean Scope defines how many instances of a Bean Spring creates and manages.
________________________________________
Q2. What is the default Bean Scope in Spring?
Singleton.
________________________________________
Q3. What is Prototype Scope?
Prototype Scope creates a new Bean instance every time the Bean is requested.
________________________________________
Q4. Which scope is more memory efficient?
Singleton Scope.
________________________________________
Q5. When should Prototype Scope be used?
When independent Bean instances are required.
________________________________________
Q6. Name some Spring Bean Scopes.
•	Singleton
•	Prototype
•	Request
•	Session
•	Application
________________________________________
Summary
Bean Scope determines how Spring creates and manages Bean instances.
Singleton Scope creates one shared instance and is the default scope used in most Spring applications.
Prototype Scope creates a new instance whenever requested and is useful for stateful components.
Understanding Bean Scopes helps developers optimize memory usage, improve performance, and design scalable Spring applications.
📌 Next Topic: @Component vs @Service vs @Repository vs @Controller

