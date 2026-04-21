# Job Portal Application 🚀

This is a Spring Boot-based Job Portal backend application developed using Java. It provides APIs for user registration with validation and proper exception handling.

---

## 📌 Features

* User Registration API
* Duplicate Email Validation
* Global Exception Handling
* Standard API Response Structure
* Input Validation using annotations

---

## 🛠️ Tech Stack

* Java 17+
* Spring Boot
* Spring Data JPA
* Hibernate
* MySQL
* Maven

---

## 📂 Project Structure

src/main/java/com/pavan/jobportal
│
├── controller        # REST Controllers
├── service           # Business Logic
├── repository        # JPA Repositories
├── entity            # Entity Classes
├── exception         # Custom Exceptions & Global Handler
├── response          # API Response Wrapper
└── dto               # Request/Response DTOs

---

## 🚀 API Endpoints

### Register User

* **URL:** `/api/users/register`
* **Method:** POST
* **Content-Type:** application/json

---

## 📥 Sample Request

```json
{
  "name": "Pavan",
  "email": "pavan@gmail.com",
  "password": "123456",
  "role": "USER"
}
```

---

## 📘 Fields Description

* **name** → User full name
* **email** → Unique email (must be unique)
* **password** → User password
* **role** → USER / ADMIN

---

## ✅ Success Response

```json
{
  "message": "User Created Successfully..",
  "data": "User created",
  "status": 201,
  "localDateTime": "2026-04-20T15:57:15.417929"
}
```

---

## ❌ Error Response (User Already Exists)

```json
{
  "message": "User already exist with mailId:- pavan@gmail.com",
  "data": null,
  "status": 400,
  "localDateTime": "2026-04-20T15:57:57.9479516"
}
```

---

## ⚙️ How to Run

1. Clone the repository:

```
git clone https://github.com/pava2706/jobportal.git
```

2. Navigate to project folder:

```
cd jobportal
```

3. Run the application:

```
mvn spring-boot:run
```

4. Server will start on:

```
http://localhost:8087
```

---

## ⚠️ Notes

* Ensure MySQL is running
* Update database credentials in `application.properties`
* Use Postman for API testing

---

## 📌 Future Enhancements

* Login & Authentication (JWT)
* Job Posting APIs
* Role-based access (Admin/User)
* Resume Upload
* Pagination & Filtering

---

⭐ If you like this project, give it a star on GitHub!
