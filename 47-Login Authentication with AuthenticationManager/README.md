# 🚀 Spring Boot From Scratch – Day 47
## 🔐 Login Authentication with AuthenticationManager

Day 47 of my **Spring Boot From Scratch** learning journey.

Today I continued my Spring Security implementation in the **NeoBank Customer Service** project.

On Day 46, I focused on the authentication foundation:

- `AppUser`
- `AppUserRepository`
- `UserDetails`
- `UserDetailsService`
- `PasswordEncoder`
- BCrypt password hashing

On Day 47, I moved one step further and implemented the **actual username/password authentication flow** using Spring Security's `AuthenticationManager`.

The main goal was to understand how Spring Security receives a login request, finds the user from the database, verifies the password, and determines whether the authentication is successful.

---

## 🎯 Today's Objective

The main objective of Day 47 was to understand and implement the actual **login authentication process**.

The authentication flow is:

```text
Login Request
      ↓
AuthController
      ↓
AuthService
      ↓
AuthenticationManager
      ↓
AuthenticationProvider
      ↓
UserDetailsService
      ↓
AppUserRepository
      ↓
Database
      ↓
AppUserDetails
      ↓
PasswordEncoder
      ↓
Password Verification
      ↓
Authentication Result
```

---

## 🔑 Authentication vs Authorization

Authentication and authorization are two different concepts.

**Authentication** answers: *Who are you?*

```text
Is "sudheer" a valid user?
```

**Authorization** answers: *What are you allowed to access?*

```text
Is "sudheer" allowed to access an Admin API?
```

---

## 🧩 AuthenticationManager

`AuthenticationManager` is one of the core components of Spring Security. Its responsibility is to process an authentication request.

Instead of manually implementing:

```text
Find User
   ↓
Get Password
   ↓
Compare Password
   ↓
Decide Authentication
```

we delegate the authentication process to Spring Security:

```text
Authentication Request
        ↓
AuthenticationManager
        ↓
Authentication Result
```

In this project, `AuthenticationManager` is injected into `AuthService`. The service creates an authentication request using `UsernamePasswordAuthenticationToken` and passes it to the `AuthenticationManager`.

---

## 🔄 UsernamePasswordAuthenticationToken

When the client sends:

```json
{
    "username": "sudheer",
    "password": "password123"
}
```

the application creates a `UsernamePasswordAuthenticationToken` containing the username and password.

Conceptually:

```text
Username + Password
        ↓
UsernamePasswordAuthenticationToken
        ↓
AuthenticationManager
```

This represents the authentication request that needs to be processed by Spring Security.

---

## 🧩 AuthenticationProvider

`AuthenticationManager` delegates the actual authentication work to an `AuthenticationProvider`. For database-backed username/password authentication, `DaoAuthenticationProvider` is used.

```text
AuthenticationManager
        ↓
DaoAuthenticationProvider
        ↓
UserDetailsService
        ↓
PasswordEncoder
```

The provider uses the configured `UserDetailsService` to retrieve the user and `PasswordEncoder` to verify the password.

---

## 🗄️ Database-Backed Authentication

In this project, users are stored in the database rather than being hardcoded.

The authentication process starts with the username:

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
```

For example, the database may contain:

| id | username | password         | role |
|----|----------|------------------|------|
| 1  | sudheer  | $2a$10$........  | USER |

When the user attempts to log in, Spring Security retrieves the user from the database.

---

## 🔎 UserDetailsService

`UserDetailsService` is responsible for loading user information during authentication. The custom implementation in this project is `AppUserDetailsService`.

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

If the username exists, the user information is returned to Spring Security. If the username does not exist, authentication fails.

---

## 👤 AppUserDetails

The application has its own user entity, `AppUser`. Spring Security works with `UserDetails`. Therefore, `AppUserDetails` acts as the bridge between the application's user entity and Spring Security.

```text
AppUser
   ↓
AppUserDetails
   ↓
Spring Security
```

`AppUserDetails` provides Spring Security with information such as:

- Username
- Password
- Authorities / Roles
- Account status

This allows Spring Security to work with the application's user entity through the standard `UserDetails` interface.

---

## 🔐 Password Verification with BCrypt

Passwords should not be stored as plain text.

For example, a user may register with:

```text
password123
```

But the database stores a BCrypt hash:

```text
$2a$10$................................
```

During login:

```text
User enters password
        ↓
PasswordEncoder
        ↓
Compare with stored BCrypt hash
        ↓
Password Match?
      /       \
    YES        NO
     ↓          ↓
Authentication  Authentication
Successful      Failed
```

The original plain-text password is not stored in the database.

---

## 📝 User Registration

Before testing login, a user must exist in the database. Therefore, a registration API was implemented:


POST /auth/register


Example request:

```json
{
    "username": "sudheer",
    "password": "password123",
    "role": "USER"
}
```

The registration flow is:

```text
POST /auth/register
        ↓
AuthController
        ↓
RegisterRequestDto
        ↓
AuthService
        ↓
PasswordEncoder
        ↓
BCrypt
        ↓
AppUserRepository
        ↓
Database
```

The password is encoded before it is saved:

```text
password123
     ↓
BCrypt
     ↓
$2a$10$................
     ↓
Database
```

This ensures that the plain-text password is not stored directly.

---

## 🔑 Login API

The login API implemented on Day 47 is:


POST /auth/login


Example request:

```json
{
    "username": "sudheer",
    "password": "password123"
}
```

The request is handled by `AuthController` and passed to `AuthService`. The service delegates authentication to `AuthenticationManager`.

---

## 🔄 Complete Login Flow

The complete login process implemented on Day 47 is:

```text
                    POST /auth/login
                           │
                           ▼
                    AuthController
                           │
                           ▼
                      AuthService
                           │
                           ▼
                 AuthenticationManager
                           │
                           ▼
                DaoAuthenticationProvider
                           │
                           ▼
                AppUserDetailsService
                           │
                           ▼
                 AppUserRepository
                           │
                           ▼
                      Database
                           │
                           ▼
                   AppUserDetails
                           │
                           ▼
                    PasswordEncoder
                           │
                           ▼
                  BCrypt Verification
                           │
                    ┌──────┴──────┐
                    │             │
                  Match        No Match
                    │             │
                    ▼             ▼
             Authentication     Authentication
                Success            Failure
```

---

## 🏗️ Application Architecture

The authentication-related architecture now looks like:

```text
Client
  │
  │ HTTP Request
  ▼
AuthController
  │
  ▼
AuthService
  │
  ▼
AuthenticationManager
  │
  ▼
DaoAuthenticationProvider
  │
  ├───────────────────┐
  │                   │
  ▼                   ▼
UserDetailsService   PasswordEncoder
  │                   │
  ▼                   ▼
AppUserRepository    BCrypt
  │
  ▼
Database
  │
  ▼
AppUser
  │
  ▼
AppUserDetails
```

Each component has a specific responsibility.

---

## 🧠 Key Learnings

- Authentication requests are represented as `UsernamePasswordAuthenticationToken` objects, not raw strings.
- `AuthenticationManager` coordinates the process; `AuthenticationProvider` (specifically `DaoAuthenticationProvider`) does the actual work.
- `UserDetailsService` and `PasswordEncoder` are the two dependencies `DaoAuthenticationProvider` relies on.
- Registration and login are symmetric in one sense: registration encodes the password once; login re-verifies it on every attempt via `PasswordEncoder`, never by decrypting.

---

## 🔜 Next Steps

- JWT-based authentication and token generation
- Maintaining authentication for REST APIs without sessions

---

🚀 **Day 47 Completed!**
