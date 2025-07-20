# 🧠 MeetMind

MeetMind is a scalable, event-driven microservices system for real-time **audio transcription**, **AI summarization**, and **user notifications**. Built with a polyglot tech stack (Spring Boot, FastAPI, Gin), it demonstrates modern best practices in microservice architecture, including:

- ✅ **JWT-based authentication**
- ✅ **Kafka-powered communication**
- ✅ **Resilience with Resilience4j**
- ✅ **Service discovery via Eureka**
- ✅ **Observability with Prometheus & Grafana**

---

## 📦 Services Overview

| Service              | Language/Tech     | Role                                  |
|---------------------|-------------------|----------------------------------------|
| `api-gateway`        | Kotlin / Spring   | Routes requests, JWT validation, rate-limiting |
| `auth-service`       | Java / Spring     | Login, registration, token issuing     |
| `upload-service`     | Kotlin / Spring   | Handles file uploads, emits Kafka events |
| `ai-transcriber`     | Python / FastAPI  | Transcribes audio using Whisper        |
| `ai-summarizer`      | Python / FastAPI  | Summarizes transcribed text via NLP    |
| `notification-service`| Go / Gin         | Sends email notifications via Kafka    |
| `discovery-service`  | Java / Spring     | Eureka server for service discovery    |

---

## 🔧 Technologies

- **Spring Boot (Java & Kotlin)**
- **FastAPI (Python)**
- **Gin (Go)**
- **Kafka** for event-driven architecture
- **Redis** for caching
- **GraphQL + gRPC**
- **Resilience4j** for fault tolerance
- **Eureka** for service discovery
- **Prometheus + Grafana** for monitoring
- **JWT** for secure auth

---

## 📁 Folder Structure

