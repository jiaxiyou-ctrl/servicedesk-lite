# ServiceDesk Lite

ServiceDesk Lite is a lightweight ticket/task management system.
Goal: build a small system that is actually usable in a real company (not a course demo).

## Tech Stack
- Backend: Java 17 + Spring Boot
- Auth: Spring Security + JWT (starting Day 2)
- Database: PostgreSQL + Flyway
- Frontend: React + TypeScript + Vite + MUI

## Local Quick Start

### 1) Start PostgreSQL (Docker)
docker compose up -d

### 2) Run Backend (Spring Boot)
cd backend
./mvnw spring-boot:run
