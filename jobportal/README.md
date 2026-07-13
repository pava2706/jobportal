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

---

## 🔐 Security

- Spring Security Integration
- JWT Token Generation & Validation
- JWT Authentication Filter
- Recruiter and Job Seeker Authorization
- Protected REST APIs

---

## 💼 Job Module

### Recruiter APIs

- Create Job
- Get Job By ID
- Get All Jobs
- Update Job
- Recruiter Ownership Validation (Only the job owner can update)

---

## 📦 API Response

All APIs return a standardized response format:

- Message
- Data
- Status Code
- Timestamp

---

## 🛠 Tech Stack

- Java 19
- Spring Boot
- Spring Security
- Spring Data JPA
- Hibernate
- MySQL
- JWT Authentication
- Maven
- REST APIs

---

## 📌 API Endpoints

### Authentication APIs

| Method | Endpoint | Access |
|---------|----------|--------|
| POST | /api/users/register/jobseeker | Public |
| POST | /api/users/register/recruiter | Public |
| POST | /api/users/login | Public |

---

### Recruiter APIs

| Method | Endpoint | Access |
|---------|----------|--------|
| POST | /api/recruiter/createjobs | Recruiter |
| GET | /api/recruiter/job/{id} | Recruiter |
| GET | /api/recruiter/jobs | Recruiter |
| PUT | /api/recruiter/job/{id} | Recruiter (Owner Only) |

---

## 🔐 Authorization Rules

- ROLE_RECRUITER
  - Create Jobs
  - View Jobs
  - Update Own Jobs

- ROLE_JOBSEEKER
  - View/Apply Jobs (Upcoming)

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
response/
```

---

## 🚧 Upcoming Features

- Delete Job API
- Apply Job
- View Applied Jobs
- Search Jobs
- Filter Jobs
- Resume Upload
- Admin Module
- Pagination
- Basic Frontend (React)

---

## 👨‍💻 Developed By

**Pavankumar M K**

Backend Developer | Spring Boot | Java | REST APIs | JWT | MySQL