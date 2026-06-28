
@Qualifier Annotation in Spring
Prerequisites
Before learning @Qualifier, you should understand:
•	Spring IoC Container 
•	Spring Beans 
•	Dependency Injection 
•	@Autowired 
•	Constructor Injection 
•	Spring Stereotype Annotations 
________________________________________
Learning Objectives
By the end of this guide, you will understand:
•	What @Qualifier is 
•	Why @Qualifier is needed 
•	How Spring resolves Bean conflicts 
•	How to use @Qualifier with Constructor Injection 
•	Difference between @Qualifier and @Primary 
•	Best practices and interview questions 
________________________________________
Introduction
Dependency Injection is one of the core features of the Spring Framework.
Normally, Spring automatically injects a Bean when only one implementation of a class or interface exists.
However, what happens if multiple Beans of the same type are available?
This is where @Qualifier comes into the picture.
@Qualifier helps Spring identify exactly which Bean should be injected.
________________________________________
The Problem
Suppose we have an interface.
public interface PaymentService {

    void pay();

}
Now we have two implementations.
@Service("creditCardPayment")
public class CreditCardPaymentService
        implements PaymentService {

    @Override
    public void pay() {

    }

}
@Service("upiPayment")
public class UpiPaymentService
        implements PaymentService {

    @Override
    public void pay() {

    }

}
Now Spring has two Beans of type PaymentService.
________________________________________
What Happens Without @Qualifier?
@Service
public class OrderService {

    private final PaymentService paymentService;

    public OrderService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

}
Spring doesn't know which implementation to inject.
Result:
NoUniqueBeanDefinitionException
Because both Beans match the required type.
________________________________________
Solution Using @Qualifier
@Service
public class OrderService {

    private final PaymentService paymentService;

    public OrderService(
            @Qualifier("upiPayment")
            PaymentService paymentService) {

        this.paymentService = paymentService;

    }

}
Now Spring injects the UpiPaymentService Bean.
________________________________________
How @Qualifier Works
Application Starts
        │
        ▼
Spring IoC Container
        │
        ▼
Creates CreditCardPayment Bean
        │
        ▼
Creates UpiPayment Bean
        │
        ▼
OrderService Requests PaymentService
        │
        ▼
@Qualifier("upiPayment")
        │
        ▼
Inject UpiPayment Bean
        │
        ▼
Application Ready
________________________________________
Architecture
              PaymentService
                     │
      ┌──────────────┴──────────────┐
      ▼                             ▼
CreditCardPayment           UpiPayment
      │                             │
      └──────────────┬──────────────┘
                     ▼
      @Qualifier("upiPayment")
                     │
                     ▼
              OrderService
________________________________________
Why Do We Need @Qualifier?
Imagine an E-Commerce application.
Payment methods:
•	Credit Card 
•	UPI 
•	Net Banking 
•	Wallet 
All implement:
PaymentService
Spring needs to know which implementation should be injected.
@Qualifier provides that information.
________________________________________
Constructor Injection with @Qualifier
@Service
public class OrderService {

    private final PaymentService paymentService;

    public OrderService(
            @Qualifier("creditCardPayment")
            PaymentService paymentService) {

        this.paymentService = paymentService;

    }

}
Constructor Injection is still the recommended approach.
________________________________________
Where Can We Use @Qualifier?
•	Constructor Injection ✅ 
•	Setter Injection ✅ 
•	Field Injection ✅ 
Although Constructor Injection is preferred.
________________________________________
Real-World Example
Consider a Notification System.
NotificationService
        │
        ├───────────────┐
        ▼               ▼
 EmailService     SmsService
        │               │
        └──────┬────────┘
               ▼
 @Qualifier("emailService")
               │
               ▼
 NotificationManager
Now Spring injects the EmailService implementation.
________________________________________
@Qualifier vs @Primary
@Qualifier	@Primary
Selects a specific Bean	Defines the default Bean
Explicit selection	Implicit selection
Used at injection point	Used on Bean definition
Best when multiple implementations are needed	Best when one implementation is usually preferred
________________________________________
Advantages of @Qualifier
•	Eliminates Bean ambiguity 
•	Supports multiple implementations 
•	Improves code readability 
•	Makes Dependency Injection more explicit 
•	Ideal for Strategy Pattern implementations 
________________________________________
Common Mistakes
1. Forgetting @Qualifier
Multiple Beans exist.
No @Qualifier.
Result:
NoUniqueBeanDefinitionException
________________________________________
2. Incorrect Bean Name
Wrong:
@Qualifier("payment")
Correct:
@Qualifier("upiPayment")
Bean names must match exactly.
________________________________________
3. Mixing Bean Names
Always ensure the Bean name matches the name defined in @Service.
________________________________________
Interview Questions
Q1. What is @Qualifier?
@Qualifier is an annotation that tells Spring exactly which Bean should be injected when multiple Beans of the same type exist.
________________________________________
Q2. Why do we use @Qualifier?
To resolve Bean ambiguity when multiple implementations of the same interface are available.
________________________________________
Q3. What exception occurs without @Qualifier?
NoUniqueBeanDefinitionException
________________________________________
Q4. Can @Qualifier be used with Constructor Injection?
Yes.
It is commonly used together with Constructor Injection.
________________________________________
Q5. What is the difference between @Qualifier and @Primary?
@Qualifier selects a specific Bean.
@Primary marks one Bean as the default choice.
________________________________________
Q6. Which is preferred in modern Spring Boot?
Constructor Injection together with @Qualifier when multiple Beans exist.
________________________________________
Best Practices
✅ Prefer Constructor Injection.
✅ Use @Qualifier only when multiple Beans exist.
✅ Use meaningful Bean names.
✅ Keep Bean naming consistent.
✅ Consider @Primary when one implementation should be the default.
________________________________________
Key Takeaways
•	@Qualifier resolves Bean conflicts. 
•	It tells Spring exactly which Bean should be injected. 
•	It works with Constructor, Setter, and Field Injection. 
•	Constructor Injection + @Qualifier is the recommended combination. 
•	It helps build flexible and maintainable Spring applications. 
________________________________________
Summary
When multiple Beans of the same type exist, Spring cannot decide which one to inject automatically. This results in a NoUniqueBeanDefinitionException.
The @Qualifier annotation solves this problem by explicitly identifying the required Bean. Combined with Constructor Injection, it provides a clean, maintainable, and scalable approach to Dependency Injection.
Understanding @Qualifier is essential because real-world Spring Boot applications often have multiple implementations of the same interface.
________________________________________
What's Next?
📌 Next Topic:
@Primary – Setting the Default Bean in Spring

