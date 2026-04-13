# 🌐 GlobalWallet API - Enterprise Finance Backend

[![Java Version](https://img.shields.io/badge/Java-25-orange?style=for-the-badge&logo=openjdk)](https://openjdk.org/projects/jdk/25/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.4-brightgreen?style=for-the-badge&logo=springboot)](https://spring.io/projects/spring-boot)
[![Security](https://img.shields.io/badge/Security-JWT_Auth-red?style=for-the-badge&logo=jsonwebtokens)](https://jwt.io/)
[![Deployment](https://img.shields.io/badge/Live-Render_Cloud-blue?style=for-the-badge&logo=render)](https://globalwallet-api-9ffu.onrender.com/swagger-ui/index.html)

A high-performance RESTful API for financial transaction management. This project demonstrates a production-ready ecosystem with a focus on security, privacy, and cloud-native architecture.

## 🌍 Live Environment

The application is live and integrated with a managed PostgreSQL cluster on Supabase.
👉 **[Access Live Swagger Documentation](https://globalwallet-api-9ffu.onrender.com/swagger-ui/index.html)**

---

## 🚀 Core Architecture & Technologies

* **Language:** Java 25 LTS
* **Framework:** Spring Boot 3.4
* **Database:** PostgreSQL (Hosted on Supabase)
* **Authentication:** Spring Security with Stateless JWT
* **Connection Pooling:** HikariCP (Optimized for cloud limits)

## 🛠️ Local Setup

To run this project locally, ensure you have JDK 25 installed and set up the following environment variables:

* `DATABASE_URL` -> Supabase JDBC URL
* `DATABASE_USER` -> Database username
* `DATABASE_PASSWORD` -> Database password
* `JWT_SECRET` -> Your secret key for token generation
* `PORT` -> 8080

Clone the repository and run:

```bash
./mvnw spring-boot:run
```
