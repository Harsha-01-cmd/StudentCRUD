# Student CRUD REST API

A RESTful API for managing student records using Spring Boot, Spring Data JPA, and MySQL.

## Features

- Full CRUD operations: create, read, update, delete students
- Soft delete support: mark records as deleted without removing them
- Layered architecture: Controller → Service → Repository
- Constructor-based dependency injection
- Auto-generated SQL queries via Spring Data JPA method name derivation

## Tech Stack

- **Spring Boot 4.1** — auto-configuration and embedded Tomcat
- **Spring Data JPA** — repository abstraction with auto-generated queries
- **Hibernate ORM** — object-relational mapping
- **MySQL** — relational database
- **HikariCP** — connection pool (auto-configured)

## Prerequisites

- Java 21+
- MySQL server running on `localhost:3306`
- Maven

## Setup

1. Create the MySQL database:
   ```sql
   CREATE DATABASE student_crud_db;
   ```

2. Update `application.properties` with your MySQL credentials if needed.

3. Run the application:
   ```bash
   mvn spring-boot:run
   ```

The app starts on `http://localhost:8080`.

## API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/api/students/create` | Create a new student |
| `GET` | `/api/students/get?id=1` | Get student by ID |
| `GET` | `/api/students/getAll` | Get all active students |
| `PUT` | `/api/students/update?id=1` | Update an existing student |
| `DELETE` | `/api/students/delete?id=1` | Permanently delete a student |
| `PATCH` | `/api/students/delete-soft?id=1` | Soft delete a student |

## Example Request

```json
POST /api/students/create
{
  "name": "John Doe",
  "age": 20,
  "email": "john@example.com",
  "rollNo": 101,
  "subject": "Mathematics"
}
```

## Project Structure

```
src/main/java/in/strikes/crudSpringBootDemo/
├── CrudSpringBootDemoApplication.java  # Main entry point
├── controller/
│   └── StudentController.java          # REST endpoints
├── service/
│   └── StudentService.java             # Business logic
├── repository/
│   └── StudentRepository.java          # Data access layer
└── entity/
    └── Student.java                    # JPA entity (DB table mapping)
```
