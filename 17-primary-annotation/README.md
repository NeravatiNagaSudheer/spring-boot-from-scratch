
@Primary Annotation in Spring
Prerequisites
Before learning @Primary, you should understand:
•	Spring IoC Container 
•	Spring Beans 
•	Dependency Injection 
•	@Autowired 
•	Constructor Injection 
•	@Qualifier 
________________________________________
Learning Objectives
By the end of this guide, you will understand:
•	What @Primary is 
•	Why @Primary is needed 
•	How Spring resolves Bean conflicts 
•	Difference between @Primary and @Qualifier 
•	Best practices and interview questions 
________________________________________
Introduction
In the previous chapter, we learned how @Qualifier helps Spring resolve conflicts when multiple Beans of the same type exist.
Another way to solve this problem is by using @Primary.
@Primary tells Spring which Bean should be considered the default Bean whenever multiple implementations of the same type are available.
Instead of specifying @Qualifier every time, you can mark one Bean as the primary choice.
________________________________________
Why Do We Need @Primary?
Suppose we have an interface.
public interface PaymentService {

    void pay();

}
Now we have two implementations.
@Service
@Primary
public class CreditCardPaymentService implements PaymentService {

    @Override
    public void pay() {
        System.out.println("Payment using Credit Card");
    }

}
@Service
public class UpiPaymentService implements PaymentService {

    @Override
    public void pay() {
        System.out.println("Payment using UPI");
    }

}
Spring now has two Beans.
Without @Primary or @Qualifier, Spring doesn't know which one should be injected.
________________________________________
Using @Primary
@Service
public class OrderService {

    private final PaymentService paymentService;

    public OrderService(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

}
Since CreditCardPaymentService is marked with @Primary, Spring automatically injects it.
No @Qualifier is required.
________________________________________
How @Primary Works
Application Starts
        │
        ▼
Spring IoC Container
        │
        ▼
Creates CreditCardPayment Bean (@Primary)
        │
        ▼
Creates UpiPayment Bean
        │
        ▼
OrderService Requests PaymentService
        │
        ▼
Inject Default Bean
        │
        ▼
Application Ready
________________________________________
Architecture
               PaymentService
                     │
      ┌──────────────┴──────────────┐
      ▼                             ▼
CreditCardPayment             UpiPayment
     @Primary
          │
          ▼
   Default Bean Selected
          │
          ▼
      OrderService
________________________________________
Real-World Example
Consider an E-Commerce application.
There are multiple payment methods:
•	Credit Card 
•	UPI 
•	Wallet 
•	Net Banking 
Most users pay using Credit Card.
Instead of writing:
@Qualifier("creditCardPayment")
everywhere,
simply make it the default.
@Service
@Primary
public class CreditCardPaymentService implements PaymentService {

}
Spring automatically injects it whenever a PaymentService is required.
________________________________________
@Primary vs @Qualifier
@Primary	@Qualifier
Sets the default Bean	Selects a specific Bean
Used on Bean class	Used at injection point
Automatic selection	Explicit selection
Best when one implementation is commonly used	Best when a specific implementation is required
________________________________________
When Should We Use @Primary?
Use @Primary when:
•	One implementation is used most of the time. 
•	You want a default Bean. 
•	Multiple implementations exist. 
Use @Qualifier when:
•	You need a specific implementation. 
•	Different classes require different Beans. 
________________________________________
What Happens if Both @Primary and @Qualifier are Used?
Suppose:
@Service
@Primary
public class CreditCardPaymentService implements PaymentService {

}
and
@Service
public class UpiPaymentService implements PaymentService {

}
Injection:
public OrderService(
        @Qualifier("upiPayment")
        PaymentService paymentService) {

}
Spring injects:
UpiPaymentService
because @Qualifier always overrides @Primary.
________________________________________
Advantages of @Primary
•	Eliminates Bean ambiguity 
•	Defines a default implementation 
•	Reduces the need for @Qualifier 
•	Cleaner Dependency Injection 
•	Improves readability 
________________________________________
Common Mistakes
1. Assuming @Primary Always Wins
If @Qualifier is present,
@Qualifier takes precedence.
________________________________________
2. Forgetting @Primary
Multiple Beans exist.
No @Primary.
No @Qualifier.
Result:
NoUniqueBeanDefinitionException
________________________________________
3. Using @Primary on Multiple Beans
Only one Bean should normally be marked as @Primary.
Otherwise, Spring cannot determine the default Bean.
________________________________________
Interview Questions
Q1. What is @Primary?
@Primary marks a Bean as the default choice when multiple Beans of the same type exist.
________________________________________
Q2. Why do we use @Primary?
To define the default implementation that Spring should inject.
________________________________________
Q3. What is the difference between @Primary and @Qualifier?
•	@Primary defines the default Bean. 
•	@Qualifier selects a specific Bean. 
________________________________________
Q4. Which annotation has higher priority?
@Qualifier
It overrides @Primary.
________________________________________
Q5. Can we use both together?
Yes.
If both are present, Spring uses the Bean specified by @Qualifier.
________________________________________
Q6. When should we prefer @Primary?
When one implementation is used in most parts of the application.
________________________________________
Best Practices
✅ Prefer Constructor Injection.
✅ Use @Primary for the default implementation.
✅ Use @Qualifier when selecting a specific Bean.
✅ Keep Bean names meaningful.
✅ Avoid marking multiple Beans as @Primary.
________________________________________
Key Takeaways
•	@Primary defines the default Bean. 
•	It helps resolve Bean ambiguity. 
•	It reduces the need for @Qualifier. 
•	@Qualifier overrides @Primary. 
•	Constructor Injection + @Primary is a clean and maintainable approach. 
________________________________________
Summary
When multiple Beans of the same type exist, Spring needs a way to determine which Bean should be injected.
The @Primary annotation allows developers to define a default Bean that Spring uses automatically during Dependency Injection.
While @Primary simplifies dependency management by providing a default implementation, @Qualifier offers explicit control when a specific Bean is required.
Understanding both annotations is essential because they are commonly used together in real-world Spring Boot applications.
________________________________________
What's Next?
📌 Next Topic:
@Value Annotation – Injecting Values from application.properties

