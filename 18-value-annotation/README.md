
 @Value Annotation in Spring Boot

Prerequisites

Before learning  @Value, you should understand:

- Spring IoC Container
- Spring Beans
- Dependency Injection
- @Autowired
- @Primary
- application.properties
- Spring Boot Configuration

---

Learning Objectives

By the end of this guide, you will understand:

- What @Value is
- Why @Value is used
- How Spring injects configuration values
- How to use @Value with different data types
- Default values in @Value
- Best practices and interview questions

---

# Introduction

One of the biggest advantages of Spring Boot is its ability to separate configuration from application code.

Instead of hardcoding values like database URLs, application names, API keys, or server ports inside Java classes, Spring Boot stores them in configuration files such as:

- application.properties
- application.yml

The @Value annotation allows Spring to read these values and inject them directly into Spring Beans.

This makes applications more flexible, maintainable, and environment-independent.

---

Why Do We Need @Value?

Imagine writing code like this:
@Component
public class AppInfo {

    private String appName = "Spring Boot From Scratch";

}



If the application name changes, you must modify the source code and rebuild the application.
A better approach is to store the value in application.properties.
app.name=Spring Boot From Scratch
Then inject it using @Value.
________________________________________
Using @Value
application.properties
app.name=Spring Boot From Scratch
app.version=1.0.0
app.author=Sudheer
Java Class
@Component
public class AppInfo {

    @Value("${app.name}")
    private String appName;

    @Value("${app.version}")
    private String version;

    @Value("${app.author}")
    private String author;

}
Spring automatically injects the values from the configuration file.
________________________________________
How @Value Works
Application Starts
        │
        ▼
Reads application.properties
        │
        ▼
Finds Matching Key
        │
        ▼
@Value("${app.name}")
        │
        ▼
Injects Value
        │
        ▼
Application Ready
________________________________________
Architecture
application.properties
        │
        ▼
Spring Boot
        │
        ▼
Reads Configuration
        │
        ▼
@Value Annotation
        │
        ▼
Spring Bean
________________________________________
Supported Data Types
@Value supports multiple data types.
server.port=8080

app.name=Spring Boot

app.enabled=true

app.price=199.99
@Value("${server.port}")
private int port;

@Value("${app.name}")
private String name;

@Value("${app.enabled}")
private boolean enabled;

@Value("${app.price}")
private double price;
________________________________________
Default Values
If the property does not exist, provide a default value.
@Value("${app.version:1.0.0}")
private String version;
If app.version is missing, Spring injects:
1.0.0
________________________________________
Using @Value with Expressions
Spring also supports expressions.
@Value("#{5 * 10}")
private int result;
Output
50
You can also combine values.
@Value("${app.name} Application")
private String applicationName;
________________________________________
Real-World Example
Consider an E-Commerce application.
spring.datasource.url=jdbc:postgresql://localhost:5432/ecommerce

spring.datasource.username=postgres

spring.datasource.password=password

server.port=8080
Inject values.
@Component
public class DatabaseConfig {

    @Value("${spring.datasource.url}")
    private String url;

    @Value("${server.port}")
    private int port;

}
No values are hardcoded.
________________________________________
Advantages of @Value
•	Eliminates hardcoded values 
•	Improves maintainability 
•	Supports different environments 
•	Easy configuration management 
•	Flexible application deployment 
________________________________________
Limitations of @Value
Although @Value is useful, it becomes difficult to manage when there are many related properties.
Example
app.name

app.version

app.author

app.description

app.company

app.website
Using six separate @Value annotations is not ideal.
For grouped configuration, Spring provides:
@ConfigurationProperties
which we'll learn in the next chapter.
________________________________________
@Value vs @ConfigurationProperties
@Value	@ConfigurationProperties
Individual properties	Group of properties
Best for small configuration	Best for large configuration
Easy to use	Cleaner for enterprise projects
Less scalable	Highly scalable
________________________________________
Common Mistakes
1. Wrong Property Name
@Value("${app.nam}")
Spring cannot find the property.
________________________________________
2. Missing Property
If the property does not exist,
Spring throws an exception unless a default value is provided.
________________________________________
3. Hardcoding Configuration
Avoid this.
private String url =
"jdbc:mysql://localhost";
Use
@Value("${spring.datasource.url}")
instead.
________________________________________
Interview Questions
Q1. What is @Value?
@Value is an annotation used to inject configuration values from application.properties, application.yml, or expressions into Spring Beans.
________________________________________
Q2. Where can @Value read values from?
•	application.properties 
•	application.yml 
•	System Properties 
•	Environment Variables 
•	Spring Expression Language (SpEL) 
________________________________________
Q3. Can @Value inject primitive data types?
Yes.
It supports:
•	String 
•	int 
•	long 
•	double 
•	boolean 
•	float 
•	List 
•	Arrays 
________________________________________
Q4. How do you provide a default value?
@Value("${app.version:1.0.0}")
________________________________________
Q5. What is the difference between @Value and @ConfigurationProperties?
@Value injects individual values.
@ConfigurationProperties binds a group of related properties to a Java class.
________________________________________
Q6. Which one is preferred in enterprise applications?
@ConfigurationProperties is preferred when managing many related configuration properties.
________________________________________
Best Practices
✅ Keep configuration outside Java code.
✅ Use application.properties or application.yml.
✅ Use default values whenever possible.
✅ Prefer @ConfigurationProperties for grouped configuration.
✅ Never hardcode sensitive information such as passwords or API keys.
________________________________________
Key Takeaways
•	@Value injects configuration values into Spring Beans. 
•	It helps eliminate hardcoded values. 
•	It supports multiple data types. 
•	Default values can be provided. 
•	It works with properties, YAML, and expressions. 
•	For larger configurations, prefer @ConfigurationProperties. 
________________________________________
Summary
The @Value annotation is one of the simplest and most widely used features in Spring Boot. It allows developers to inject configuration values from external sources into Spring Beans, making applications flexible, configurable, and easy to maintain.
While @Value is perfect for injecting individual properties, @ConfigurationProperties is a better choice for managing larger groups of related configuration values.
Understanding @Value is essential because configuration management is a fundamental part of every Spring Boot application.
________________________________________
What's Next?
📌 Next Topic:
@ConfigurationProperties – Managing Configuration the Right Way



