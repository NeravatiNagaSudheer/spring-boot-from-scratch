Embedded Tomcat in Spring Boot
Introduction
One of the most powerful features of Spring Boot is Embedded Tomcat.
Before Spring Boot, Java web applications typically required an external Tomcat server to be installed and configured separately.
Spring Boot simplified this process by embedding Tomcat directly within the application.
This allows developers to run applications using a simple Java command without manually deploying WAR files.
________________________________________
What is Tomcat?
Apache Tomcat is an open-source web server and servlet container used to run Java web applications.
Tomcat is responsible for:
•	Handling HTTP Requests
•	Managing Servlet Lifecycle
•	Processing Web Applications
•	Serving Dynamic Content
Tomcat acts as a bridge between the client and the Java application.
________________________________________
Traditional Deployment Model
Before Spring Boot:
Developer
     │
     ▼
Build WAR File
     │
     ▼
Install Tomcat Server
     │
     ▼
Deploy WAR File
     │
     ▼
Start Tomcat
     │
     ▼
Access Application
Challenges:
•	Manual Server Installation
•	Complex Deployment Process
•	Environment Differences
•	Additional Maintenance
________________________________________
What is Embedded Tomcat?
Embedded Tomcat means the Tomcat server is packaged inside the Spring Boot application.
Instead of deploying an application to Tomcat:
Application → Tomcat
Spring Boot packages:
Application + Tomcat
into a single executable JAR file.
________________________________________
How Embedded Tomcat Works
When a Spring Boot application starts:
Spring Boot Application
          │
          ▼
 spring-boot-starter-web
          │
          ▼
  Embedded Tomcat
          │
          ▼
  Application Starts
          │
          ▼
 Access via Browser/API
Tomcat starts automatically as part of the application startup process.
________________________________________
Dependency Responsible for Tomcat
When we add:
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
Spring Boot automatically includes:
spring-boot-starter-web
         │
         ▼
spring-boot-starter-tomcat
         │
         ▼
Embedded Tomcat Server
No additional server installation is required.
________________________________________
Example Spring Boot Application
@SpringBootApplication
public class ProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductApplication.class, args);
    }
}
When this application starts:
•	Spring Container Starts
•	Embedded Tomcat Starts
•	Application Context Loads
•	Endpoints Become Available
________________________________________
Default Tomcat Port
By default:
8080
Application URL:
http://localhost:8080
________________________________________
Changing Tomcat Port
application.properties
server.port=9090
Application URL becomes:
http://localhost:9090
________________________________________
Advantages of Embedded Tomcat
1. Simplified Deployment
Applications can be executed directly.
________________________________________
2. Faster Development
No external server setup required.
________________________________________
3. Environment Consistency
Development, Testing, and Production environments behave similarly.
________________________________________
4. Easy Distribution
Applications can be distributed as executable JAR files.
________________________________________
5. Reduced Maintenance
No separate Tomcat installation or upgrades.
________________________________________
Real-World Example
Suppose you build a Product Management API.
Without Spring Boot:
•	Install Tomcat
•	Configure Tomcat
•	Package WAR File
•	Deploy WAR File
With Spring Boot:
java -jar product-service.jar
The application starts immediately with Embedded Tomcat.
________________________________________
Interview Questions
Q1. What is Embedded Tomcat?
Embedded Tomcat is a Tomcat server packaged within a Spring Boot application.
________________________________________
Q2. Why is Embedded Tomcat useful?
It eliminates the need for external server installation and simplifies deployment.
________________________________________
Q3. Which dependency provides Embedded Tomcat?
spring-boot-starter-web
through:
spring-boot-starter-tomcat
________________________________________
Q4. What is the default Tomcat port?
8080
________________________________________
Q5. How can you change the Tomcat port?
server.port=9090
________________________________________
Q6. What is the difference between JAR and WAR deployment?
JAR:
•	Contains Embedded Tomcat
•	Self-executable
WAR:
•	Requires External Server
•	Manual Deployment
________________________________________
Summary
Embedded Tomcat is one of the most important features of Spring Boot. It packages the web server directly inside the application, eliminating the need for external server installation and simplifying deployment.
This feature improves developer productivity, simplifies environment management, and enables applications to run with minimal setup.
📌 Next Topic: IoC (Inversion of Control) Container

