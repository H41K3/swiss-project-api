# SwissPocket API 🇨🇭

A robust RESTful API for personal finance management, designed with a clean 3-tier architecture and containerized for seamless deployment. This project serves as a showcase of enterprise-level backend development practices using Java and Spring Boot.

## 🚀 Technologies Used
* **Java 25** (Record classes, modern language features)
* **Spring Boot 3** (Web, Data JPA, Validation)
* **MySQL** (Relational Database)
* **Docker** (Containerization)
* **Mockito & JUnit 5** (Unit Testing)
* **Swagger/OpenAPI** (API Documentation)

## 🏗️ Architecture
This application strictly follows the **3-Tier Architecture** pattern (Controller -> Service -> Repository) to ensure separation of concerns, maintainability, and testability. Data is protected at the API boundary using **Data Transfer Objects (DTOs)**.

## ⚙️ How to Run (Docker)

To run this application without installing Java locally, ensure you have Docker running.

1. **Build the image:**
docker build -t swisspocket-api .

2. **Run the container:**
docker run -p 8080:8080 -e "SPRING_DATASOURCE_URL=jdbc:mysql://host.docker.internal:3306/swissproject_db?serverTimezone=UTC" swisspocket-api

3. **Access the API Documentation:**
Open your browser and navigate to: http://localhost:8080/swagger-ui/index.html