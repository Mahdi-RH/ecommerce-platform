# E-Commerce Platform

## Project Vision

The goal of this project is to design and implement a **production-oriented e-commerce backend platform** using modern Java/Kotlin and Spring technologies, following **real-world backend, DevOps, and cloud-native practices**.

This project is intentionally built as an **incremental system**:
- Start with core e-commerce functionality
- Apply clean architecture and best practices
- Gradually evolve into a microservices-ready, cloud-deployed platform on Azure

The project also serves as a **hands-on learning and portfolio project**, demonstrating backend engineering skills commonly expected in professional Java/Kotlin backend roles.

---

## Tech Stack

### Backend
- **Java / Kotlin**
- **Spring Boot**
- Spring Web (REST APIs)
- Spring Data JPA (Hibernate)
- Spring Security (Authentication & Authorization)

### Database
- **PostgreSQL**
- JPA / Hibernate ORM
- Flyway (Database migrations)

### Asynchronous & Messaging (later phases)
- Kafka
- Kotlin Coroutines / Async Processing

### DevOps & Cloud
- Docker
- Kubernetes
- GitHub Actions (CI/CD)
- **Microsoft Azure**
  - Azure Container Registry (ACR)
  - Azure Kubernetes Service (AKS)

### Testing
- JUnit 5
- Mockito
- Integration testing with Testcontainers

---

## Scope

### Phase 1 – Core Functionality
- User registration and authentication
- Product catalog
- Shopping cart
- Order creation
- Basic role-based access control (Admin / User)

### Phase 2 – Data & Architecture Improvements
- Database normalization and indexing
- Transaction management
- Pagination and filtering
- DTO mapping and validation
- Lazy vs eager loading optimizations

### Phase 3 – Microservices & Messaging
- Service decomposition (e.g. Order, Payment, Inventory)
- Asynchronous communication using Kafka
- Resilience patterns (timeouts, retries, circuit breakers)

### Phase 4 – Cloud & Deployment
- Containerization with Docker
- Kubernetes deployment
- CI/CD pipelines with GitHub Actions
- Deployment to Azure (AKS)

---

## High-Level Architecture
The system follows a **layered architecture** with clear separation of concerns:

![High-Level Architecture](./docs/high-level-architecture)

This diagram shows the conceptual architecture of the e-commerce platform and how clients, backend services, and the database interact.
The architecture evolves over phases; the diagram above represents Phase 1.


### Key Architectural Principles
- Stateless REST APIs
- Clear service boundaries
- Transactional consistency using Spring @Transactional
- Database access abstracted via repositories
- Designed to evolve into microservices without major rewrites

In later phases, the architecture will expand to include:
- Kafka for async communication
- Multiple deployable services
- Kubernetes-based orchestration on Azure

---

## Status

**Work in progress**  
This project is actively being developed and expanded in phases.

---

## Author

Mahdi Roohandeh
Backend Software Engineer  
Spring Boot | Kotlin | Java | Cloud & DevOps
