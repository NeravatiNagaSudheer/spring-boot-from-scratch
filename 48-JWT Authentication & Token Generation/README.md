# 🚀 Spring Boot From Scratch – Day 48
## 🔐 JWT Authentication & Token Generation

Day 48 of my **Spring Boot From Scratch** journey focuses on implementing **JWT (JSON Web Token) authentication and token generation** using Spring Security.

In Day 47, I implemented username/password authentication using Spring Security's `AuthenticationManager`.

Today, I moved to the next step:

> **Generate a JWT after successful authentication and return it to the client.**

---

## 🎯 Objective

The main objective of Day 48 was to understand how JWT can be used to maintain authentication across multiple requests in a **stateless REST API**.

The flow implemented today is:

```text
Client
   ↓
Username + Password
   ↓
AuthenticationManager
   ↓
Authentication Success
   ↓
Generate JWT
   ↓
Return JWT to Client
   ↓
Client sends JWT with future requests
   ↓
Protected APIs
```

---

## 🔐 What is JWT?

JWT stands for **JSON Web Token**.

It is a compact token that allows an authenticated client to prove its identity when accessing protected APIs.

Instead of relying on a server-side session, the client receives a token after successful login and sends that token with subsequent requests.

This makes JWT particularly useful for REST APIs and distributed applications.

---

## 🧩 JWT Structure

A JWT consists of three parts separated by dots:

```text
Header.Payload.Signature
```

For example:

```text
xxxxx.yyyyy.zzzzz
```

### 1. Header

The header contains metadata about the token — typically the token type and signing algorithm.

```json
{
    "alg": "HS256",
    "typ": "JWT"
}
```

### 2. Payload

The payload contains the token's claims — information such as username, role, issued time, and expiration time.

```json
{
    "sub": "sudheer",
    "role": "USER",
    "exp": "expiration-time"
}
```

The payload should **not** contain sensitive information such as passwords.

### 3. Signature

The signature is created by signing the token data using a secret key.

Conceptually:

```text
Signature =
Sign(
    Header + "." + Payload,
    Secret Key
)
```

The signature allows the server to verify that the token has not been modified.

---

## 🔐 Stateless Authentication

One of the important concepts learned today is **stateless authentication**.

With traditional session-based authentication:

```text
Client
   ↓
Login
   ↓
Server creates session
   ↓
Server stores session information
```

With JWT:

```text
Client
   ↓
Login
   ↓
Server generates JWT
   ↓
Client receives JWT
   ↓
Client sends JWT with requests
```

The server does not need to maintain authentication session data for every user. Each request carries the client's authentication token.

---

## 🔑 JWT Secret Key & Signing

The server uses a secret key to sign the JWT. The signing process provides integrity protection — if someone modifies the token payload, the signature will no longer match.

Conceptually:

```text
Header + Payload
       ↓
   Secret Key
       ↓
    Signature
       ↓
      JWT
```

The secret key must be protected and should never be hard-coded or committed to GitHub in a real application.

---

## ⏳ Token Expiration

JWTs should have an expiration time.

For example:

```text
Issued At: 10:00 AM
Expires At: 11:00 AM
```

After the expiration time, the token should no longer be accepted. Token expiration helps reduce the amount of time a stolen token could potentially be misused.

---

## 📦 Returning JWT to the Client

After successful authentication, the server generates the JWT and returns it in the login response.

Conceptually:

```text
POST /auth/login

Username + Password
        ↓
AuthenticationManager
        ↓
Authentication Success
        ↓
Generate JWT
        ↓
Return JWT
```

The client can then use the token for future protected API requests.

---

## 🌐 Using JWT in Subsequent Requests

The JWT is normally sent through the HTTP `Authorization` header.

```text
Authorization: Bearer <JWT>
```

A future protected request can look like:

```text
GET /api/v1/customers
Authorization: Bearer eyJhbGciOiJIUzI1Ni...
```

The JWT validation/filter mechanism will be implemented in the next stage.

---

## 🛠️ What I Implemented

During Day 48, I implemented:

- JWT generation
- JWT claims
- Token expiration
- JWT signing
- JWT generation after successful authentication
- Returning JWT in the login response
- Integration of JWT generation with the existing authentication flow

---

## 🏗️ Application Architecture

The authentication architecture now looks like:

```text
                Client
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
       AuthenticationProvider
                  │
                  ▼
        UserDetailsService
                  │
                  ▼
          AppUserRepository
                  │
                  ▼
              Database
                  │
                  ▼
       Authentication Success
                  │
                  ▼
             JWT Service
                  │
                  ▼
            Generate JWT
                  │
                  ▼
           Return JWT
                  │
                  ▼
               Client
```

---

## 🔗 JWT and Spring Security

JWT does not replace Spring Security. Instead, JWT becomes the mechanism used to maintain the authenticated state across subsequent stateless requests.

The overall architecture is:

```text
Spring Security
      │
      ├── AuthenticationManager
      │
      ├── UserDetailsService
      │
      ├── PasswordEncoder
      │
      └── JWT
             │
             ├── Identity
             ├── Claims
             ├── Expiration
             └── Signature
```

---

## 🧠 Key Learnings

- A JWT's three parts (header, payload, signature) let the server verify authenticity without a database lookup or stored session.
- Stateless authentication shifts the burden of proving identity from the server (sessions) to the client (token).
- The secret key used for signing must be kept out of source control in a real deployment.
- Expiration limits the blast radius of a leaked token.

---

## 🔜 Next Steps

- Implement a JWT Authentication Filter to validate the token on every protected request

---

