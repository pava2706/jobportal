# Job Portal Backend Application

A backend REST API project built using Spring Boot for managing a Job Portal system with JWT Authentication and Role-Based Authorization.

---

## 🚀 Features Implemented

### 👤 User Module
- User Registration
  - Job Seeker Registration
  - Recruiter Registration
- Login Authentication using JWT
- Password Encryption using BCrypt
- Role-Based Access Control

### 🔐 Security
- Spring Security Integration
- JWT Token Generation & Validation
- Protected APIs using JWT Filter
- Recruiter and Jobseeker Authorization

### 💼 Job Module
- Recruiter can create jobs
- DTO Mapping implemented
- Global API Response Structure
- Created Timestamp for Jobs

---

## 🛠 Tech Stack

- Java 19
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate
- MySQL
- JWT
- Maven

---

## 📌 API Endpoints

### Authentication APIs

| Method | Endpoint | Access |
|--------|-----------|--------|
| POST | /api/users/register/jobseeker | Public |
| POST | /api/users/register/recruiter | Public |
| POST | /api/users/login | Public |

### Recruiter APIs

| Method | Endpoint | Access |
|--------|-----------|--------|
| POST | /api/recruiter/createjobs | Recruiter Only |

---

## 🔐 Authorization

- ROLE_RECRUITER → Can create jobs
- ROLE_JOBSEEKER → Can view/apply jobs (upcoming)

---

## 📂 Project Structure

```text
controller/
service/
repository/
entity/
dto/
config/
exception/
```

---

## 🚧 Upcoming Features

- View All Jobs
- Apply Job Feature
- Resume Upload
- Search & Filter Jobs
- Admin Module

---

## 👨‍💻 Developed By

Pavankumar M K