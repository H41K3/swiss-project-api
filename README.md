# 🇨🇭 SwissPocket API - Enterprise Finance Backend

[![Java Version](https://img.shields.io/badge/Java-25-orange?style=for-the-badge&logo=openjdk)](https://openjdk.org/projects/jdk/25/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.4-brightgreen?style=for-the-badge&logo=springboot)](https://spring.io/projects/spring-boot)
[![Security](https://img.shields.io/badge/Security-JWT_Auth-red?style=for-the-badge&logo=jsonwebtokens)](https://jwt.io/)
[![Deployment](https://img.shields.io/badge/Live-Render_Frankfurt-blue?style=for-the-badge&logo=render)](https://swiss-project-api.onrender.com/swagger-ui/index.html)

A high-performance RESTful API for financial transaction management. This project demonstrates a production-ready ecosystem with a focus on security, privacy, and cloud-native architecture.

## 🚀 Live Environment (Frankfurt, EU-Central)

The application is live and integrated with a managed PostgreSQL cluster.
👉 **[Access Live Swagger Documentation](https://swiss-project-api.onrender.com/swagger-ui/index.html)**

---

## 🏗️ Core Architecture & Security Features

This project moves beyond a simple CRUD, implementing advanced backend patterns required for financial applications in Europe:

### 🛡️ Security & Multi-Tenancy (GDPR Ready)

* **Data Isolation:** Implemented a strict **Multi-Tenant** logic where transactions are bound to the authenticated User Principal. A user can never access or modify data belonging to another user.
* **JWT Authentication:** Stateless authentication using JSON Web Tokens (RSA encryption ready).
* **Data Privacy (Jackson Integration):** Sensitive user data (password hashes, internal roles) are strictly ignored during JSON serialization using `@JsonIgnore` to prevent credential leakage.
* **Encrypted Credentials:** Password hashing using BCrypt with custom salting.

### 🏛️ System Design

* **3-Tier Architecture:** Clear separation between Controller (REST), Service (Business Logic), and Repository (Data Access).
* **Automated Testing:** Unit and Integration test suites utilizing **Mockito** and **WebMvcTest** with secure context mocking.
* **Infrastructure as Code:** Multi-stage Docker builds for optimized container footprints.

---

## 🛠️ Tech Stack & Infrastructure

| Layer | Technologies |
| :--- | :--- |
| **Language** | Java 25 (Latest JDK Features) |
| **Framework** | Spring Boot 3.4 (Web, Security, Data JPA, Validation) |
| **Database** | PostgreSQL (Managed by Neon.tech) |
| **Security** | Spring Security + Auth0 Java JWT |
| **Cloud/CD** | Render (Frankfurt Region) + GitHub Auto-Deploy |
| **Documentation** | OpenAPI 3 / Swagger UI |

---

## ⚙️ Development & Quick Start

### Authentication Workflow

1. **Register:** Create a new user at `/auth/register`.
2. **Login:** Authenticate at `/auth/login` to receive your Bearer Token.
3. **Authorize:** Use the "Authorize" button in Swagger to inject the token.
4. **Manage:** All subsequent transaction requests will be automatically scoped to your profile.

### Local Development (Docker)

```bash
# Build the multi-stage image
docker build -t swisspocket-api .

# Run with environment variables
docker run -p 8080:8080 \
  -e DATABASE_URL=jdbc:postgresql://your-db-url \
  -e TOKEN_SECRET=your-secure-secret \
  swisspocket-api
