# Simple Spring Boot App

A simple Spring Boot application for performing CRUD operations on **attractions**, **addresses**, **services**, and **ticket information**.

## 🛠️ Tech Stack

- Java 17
- Spring Boot 3.4
- Spring Data JPA
- PostgreSQL
- Liquibase
- Thymeleaf
- Lombok

### 🧪 Testing

- JUnit 5
- Mockito
- Spring Boot Test
- Testcontainers (PostgreSQL)

## 🚀 Running the Application

### Locally via Maven

Make sure you have Java 17 installed, then run the following command in the project root:

```bash
./mvnw spring-boot:run
```
## 🚀 Running the Application with Docker Compose
This application uses Docker Compose to run the necessary services (e.g., PostgreSQL) along with the application.

To build and start the containers in detached mode, run:

```bash
docker-compose up --build -d
```
To stop and remove the containers, run:
```bash
docker-compose down
```