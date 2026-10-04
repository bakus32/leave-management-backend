# Leave Management API

This project is a Spring Boot 4 leave management backend focused on the core employee and role/profile setup used by the application. In this version, the business domain is intentionally reduced to the base entities required for the workflow: `User` and `Profile`.

The goal is to support a leave management system where employees can submit leave requests and managers can validate or approve them, while keeping the foundational master data clean and scalable.

## Business context

The application models:
- `User`: an employee or staff member
- `Profile`: a classification or role such as `ADMIN`, `MANAGER`, `EMPLOYEE`, etc.

A user belongs to exactly one profile, and profile metadata is stored separately to support role-based access and validation logic in the leave workflow.

## Features
- Hexagonal architecture with clean separation between domain, application, infrastructure, and API layers
- CRUD for `Profile`
- CRUD for `User`
- One-to-one logical relationship from `User` to `Profile`
- Sequence-based ID generation for database performance
- Audit fields: `createdDate` and `modifiedDate`
- Flyway migration support for database versioning
- Batch-friendly Hibernate settings for efficient writes
- MapStruct for mapping between layers
- Lombok for boilerplate reduction

## Project structure

- `domain/` — core entities and ports
- `application/` — use cases, DTOs, and mapper interfaces
- `infrastructure/` — JPA entities, repositories, and adapters
- `common/` — exceptions and shared configuration
- `src/main/resources/db/migration/` — schema migration scripts

## Tech stack
- Java 25
- Spring Boot 4.1.1
- Spring Data JPA
- Spring Validation
- Hibernate
- Flyway
- MapStruct
- Lombok
- H2 in-memory database for local development

## Run locally

```bash
./mvnw clean install
./mvnw spring-boot:run
```

## OpenAPI / Swagger

The application exposes the generated OpenAPI specification at:

- `http://localhost:8080/v3/api-docs`
- `http://localhost:8080/swagger-ui.html`

Swagger UI provides a browser-based interface for testing the available endpoints and reviewing request/response schemas.

## API endpoints

### Profiles
- `POST /api/v1/profiles`
- `GET /api/v1/profiles`
- `GET /api/v1/profiles/{id}`
- `PUT /api/v1/profiles/{id}`
- `DELETE /api/v1/profiles/{id}`

### Users
- `POST /api/v1/users`
- `GET /api/v1/users`
- `GET /api/v1/users/{id}`
- `PUT /api/v1/users/{id}`
- `DELETE /api/v1/users/{id}`

## Example payloads

### Create profile

```json
{
  "code": "MANAGER",
  "name": "Manager"
}
```

### Create user

```json
{
  "name": "Alice Dupont",
  "email": "alice.dupont@company.com",
  "phone": "+33612345678",
  "profileId": 1
}
```

## Database schema

The application uses Flyway to create the base schema. The migration script is:

`src/main/resources/db/migration/V1__create_profiles_and_users.sql`

This script creates:
- `profiles` table
- `users` table
- `profile_seq` sequence
- `user_seq` sequence

## Notes
- `User.profileId` references the selected profile.
- The `createdDate` and `modifiedDate` fields are automatically handled by Spring Data JPA auditing.
- This project currently focuses on the core setup required by the leave management workflow and is ready to be extended with leave request, approval, and manager logic.
