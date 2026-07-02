# 🚀 Job Portal Backend Application

A production-oriented backend REST API built with Spring Boot that simulates a real-world Job Portal platform. The application supports secure authentication, role-based authorization, recruiter job management, and is being enhanced with AI-powered features.

---

## 📌 Project Overview

This project enables:

- Recruiters to register, log in, and post jobs.
- Job Seekers to register and log in securely.
- JWT-based authentication and authorization.
- Secure REST APIs using Spring Security.
- Layered architecture following backend development best practices.

This project is being developed to demonstrate practical backend development skills using Java and Spring Boot.

---

## ✨ Current Features

### 👤 Authentication & User Management

- Recruiter Registration
- Job Seeker Registration
- Login Authentication
- BCrypt Password Encryption
- JWT Token Generation
- JWT Validation
- Role-Based Authorization

---

### 💼 Recruiter Module

- Create Job
- Associate Job with Recruiter
- Automatic Created Timestamp
- DTO-based Request & Response
- Global Exception Handling
- Standard API Response Structure

---

### 🔒 Security

- Spring Security
- JWT Filter
- Stateless Authentication
- Protected REST APIs
- Public & Private Endpoint Configuration

---

## 🛠️ Tech Stack

| Technology | Version |
|------------|---------|
| Java | 17+ |
| Spring Boot | 3.x |
| Spring Security | Latest |
| Spring Data JPA | Latest |
| Hibernate | ORM |
| MySQL | Database |
| JWT | Authentication |
| Maven | Build Tool |
| Swagger OpenAPI | API Documentation |

---

## 📂 Project Structure

```
src/main/java
│
├── config
├── controller
├── dto
├── entity
├── exception
├── repository
├── service
│     └── implementation
├── response
└── util
```

---

## 📌 Implemented REST APIs

### Authentication

| Method | Endpoint | Access |
|---------|----------|--------|
| POST | /api/users/register/jobseeker | Public |
| POST | /api/users/register/recruiter | Public |
| POST | /api/users/login | Public |

---

### Recruiter APIs

| Method | Endpoint | Access |
|---------|----------|--------|
| POST | /api/recruiter/createjobs | Recruiter Only |

---

## 🔐 Authorization

| Role | Permissions |
|------|-------------|
| RECRUITER | Create Jobs |
| JOBSEEKER | Register & Login |

---

## 🚧 Upcoming Features

- View All Jobs
- Apply for Job
- View Applied Jobs
- Recruiter View Applicants
- Resume Upload
- Resume Download
- Search Jobs
- Filter Jobs
- Pagination
- AI Resume Analysis (Ollama)
- AI Interview Question Generator
- Email Notifications
- Docker Deployment

---

## 📖 API Documentation

Swagger UI

```
http://localhost:8087/swagger-ui/index.html
```

---

## ▶️ How to Run

Clone the repository

```bash
git clone https://github.com/pava2706/jobportal.git
```

Navigate to the project

```bash
cd jobportal
```

Configure MySQL credentials inside:

```
application.properties
```

Run the project

```bash
mvn spring-boot:run
```

---

## 🎯 Future Scope

- React Frontend
- Docker
- CI/CD Pipeline
- Cloud Deployment
- AI Job Recommendation
- Resume Parsing
- Admin Dashboard

---

## 👨‍💻 Developed By

**Pavankumar M K**

Java Backend Developer (Aspiring)

GitHub:
https://github.com/pava2706
