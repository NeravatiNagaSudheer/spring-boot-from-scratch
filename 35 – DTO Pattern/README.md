 DTO Pattern (Request DTO & Response DTO)
📖 Overview

Today, I refactored the Customer Service module by introducing the DTO (Data Transfer Object) Pattern.

Previously, the REST APIs were directly exposing Entity objects. Although functional, this approach tightly couples the API with the database model.

To follow enterprise best practices, I introduced:

CustomerRequestDto – Receives data from the client.
CustomerResponseDto – Returns only the required data to the client.

This creates a clear separation between the API layer and the persistence layer.

📌 What is a DTO?

A Data Transfer Object (DTO) is an object used to transfer data between different layers of an application.

Instead of exposing Entity objects directly, the application exchanges DTOs through REST APIs.

🚀 Why use DTOs?
Hide internal database structure.
Prevent exposing unnecessary or sensitive fields.
Decouple the API from the persistence layer.
Support request validation.
Improve maintainability and scalability.
Allow independent evolution of API and database models.
🏗️ Architecture
                  CREATE / UPDATE

Client
   │
   ▼
CustomerRequestDto
   │
   ▼
CustomerController
   │
   ▼
CustomerService
   │
   ▼
Customer Entity
   │
   ▼
CustomerRepository
   │
   ▼
Database
                    READ

Database
   │
   ▼
Customer Entity
   │
   ▼
CustomerService
   │
   ▼
CustomerResponseDto
   │
   ▼
CustomerController
   │
   ▼
Client
✅ Implemented Features
Added CustomerRequestDto
Added CustomerResponseDto
Refactored Create Customer API
Refactored Get All Customers API
Refactored Get Customer By ID API
Refactored Update Customer API
Added Bean Validation on Request DTO
Improved project architecture using DTOs
