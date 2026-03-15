# 🇨🇭 SwissPocket API - Enterprise Finance Backend

[![Java Version](https://img.shields.io/badge/Java-25-orange?style=for-the-badge&logo=openjdk)](https://openjdk.org/projects/jdk/25/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.4-brightgreen?style=for-the-badge&logo=springboot)](https://spring.io/projects/spring-boot)
[![Deployment](https://img.shields.io/badge/Live-Render_Frankfurt-blue?style=for-the-badge&logo=render)](https://swiss-project-api.onrender.com/swagger-ui/index.html)

A high-performance RESTful API for financial transaction management. This project demonstrates a production-ready ecosystem, moving beyond local development into a fully automated cloud-native architecture.

## 🚀 Live Environment (Frankfurt, EU-Central)
The application is live and integrated with a managed PostgreSQL cluster. 
👉 **[Access Live Swagger Documentation](https://swiss-project-api.onrender.com/swagger-ui/index.html)**

---

## 🏗️ System Architecture
The project follows a strict **3-Tier Architecture** combined with modern DevOps practices to ensure scalability and reliability.

### Key Components:
* **Separation of Concerns:** Controller, Service, and Repository layers.
* **Data Integrity:** Validated DTOs for all API boundaries.
* **Infrastructure as Code:** Multi-stage Docker builds for optimized container footprints.
* **CI/CD:** Automated test suites and deployment via GitHub Actions (CI) and Render (CD).

---

## 🛠️ Tech Stack & Infrastructure
| Layer | Technologies |
| :--- | :--- |
| **Language** | Java 25 (Modern Records & Pattern Matching) |
| **Framework** | Spring Boot 3.4 (Web, Data JPA, Validation) |
| **Database** | PostgreSQL (Managed by Neon.tech) |
| **Container** | Docker (Multi-stage Build) |
| **Cloud** | Render (Frankfurt Region - EU Central 1) |
| **API Docs** | OpenAPI 3 / Swagger UI |

---

## ⚙️ Development & Deployment

### Local Development (Docker)
To run the environment locally using the production-ready Docker setup:
```bash
# Build the multi-stage image
docker build -t swisspocket-api .

# Run with environment variables
docker run -p 8080:8080 \
  -e DATABASE_URL=jdbc:postgresql://your-db-url \
  -e DATABASE_USER=your-user \
  -e DATABASE_PASSWORD=your-password \
  swisspocket-api