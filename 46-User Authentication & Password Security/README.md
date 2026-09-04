# 🚀 Spring Boot From Scratch – Day 46
## 🔐 User Authentication & Password Security

Day 46 of my **Spring Boot From Scratch** learning journey.

Today I continued my Spring Security implementation in the **NeoBank Customer Service** project.

On Day 45, I learned how to configure Spring Security and protect application endpoints using `SecurityFilterChain`.

Today, I focused on understanding the **authentication foundation of Spring Security** — how an application identifies a user, loads their information, and handles passwords securely.

---

## 🎯 Today's Objective

The main objective of Day 46 was to understand the basic building blocks required for **user authentication and password security** in Spring Security.

The authentication flow can be represented as:

```text
User Login Request
        ↓
Spring Security
        ↓
Find User
        ↓
Load User Details
        ↓
Verify Password
        ↓
Authentication
        ↓
Authenticated User
```

---

## 🔐 What is Authentication?

Authentication is the process of verifying who the user is.

For example, when a user provides:

```text
Username: sudheer
Password: myPassword
```

the application needs to answer:

> "Does this username exist, and does the provided password match the stored password?"

If the credentials are valid, Spring Security considers the user authenticated.

### Authentication vs Authorization

These two concepts are different:

- **Authentication** → Who are you?
- **Authorization** → What are you allowed to do?

For example:

```text
Authentication:
Is Sudheer a valid user?

Authorization:
Is Sudheer allowed to access the Admin APIs?
```

Day 46 mainly focused on the authentication foundation.

---

## 👤 Why Do We Need an AppUser?

One important architectural decision in this project was keeping `Customer` information separate from authentication information.

A `Customer` represents business information such as:

```text
Customer
 ├── customerId
 ├── firstName
 ├── lastName
 ├── email
 ├── phoneNumber
 ├── dateOfBirth
 └── status
```

Authentication information belongs to a separate `AppUser` entity:

```text
AppUser
 ├── id
 ├── username
 ├── password
 └── role
```

### Why separate them?

A customer and a login user are not necessarily the same concept.

```text
Customer → Banking customer/business data
AppUser  → Authentication and security data
```

Keeping them separate provides a cleaner design and avoids putting security credentials directly into the customer/business entity.

---

## 🔎 UserDetails

Spring Security uses the `UserDetails` interface to represent information about an authenticated user.

Instead of Spring Security directly depending on our `AppUser` entity, we created:

```text
AppUser
   ↓
AppUserDetails
   ↓
Spring Security
```

`AppUserDetails` acts as the bridge between our application's user entity and Spring Security.

It provides Spring Security with information such as:

- Username
- Password
- Roles/Authorities
- Account status

This allows Spring Security to work with our application's user information without tightly coupling the security framework to our database entity.

---

## 🔍 UserDetailsService

Spring Security needs a way to find a user. This is the responsibility of `UserDetailsService`.

Our implementation follows this flow:

```text
Username
   ↓
AppUserDetailsService
   ↓
AppUserRepository
   ↓
Database
   ↓
AppUser
   ↓
AppUserDetails
```

The `AppUserDetailsService` uses the username to find the corresponding user from the database.

If the user exists:

```text
Database User
      ↓
AppUserDetails
      ↓
Spring Security
```

If the user does not exist, a `UsernameNotFoundException` is thrown.

---

## 🔐 Password Security

Passwords should never be stored as plain text in a database.

For example, storing:

```text
password = "Welcome123"
```

would be insecure.

Instead, the password should be converted into a secure hash before being stored. For this project, I used Spring Security's `PasswordEncoder` with **BCrypt**.

### 🔒 Why BCrypt?

BCrypt is a password-hashing algorithm designed specifically for securely storing passwords.

Instead of storing the original password:

```text
Welcome123
```

the database stores a BCrypt hash similar to:

```text
$2a$10$......................
```

When the user logs in, the application does not simply compare two plain-text passwords. Instead:

```text
User enters password
        ↓
PasswordEncoder
        ↓
Compare with stored BCrypt hash
        ↓
Match?
   ↙        ↘
 Yes         No
  ↓           ↓
Authenticate  Reject
```

This provides a much safer approach to password storage.

---

## 🧠 Key Learnings

- Authentication and authorization are distinct concerns.
- Separating `Customer` and `AppUser` keeps business and security concerns decoupled.
- `AppUserDetails` acts as an adapter between the app's domain model and Spring Security's contract.
- BCrypt hashes are one-way — verification works by re-hashing and comparing, never by decrypting.

---

## 🔜 Next Steps

- Implement JWT-based authentication
- Generate and validate JWT tokens
- Secure endpoints using a custom JWT filter

---

🚀 **Day 46 Completed!**
