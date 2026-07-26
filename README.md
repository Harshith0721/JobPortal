# Job Portal

## About the Project

Job Portal is a backend application built using **Spring Boot** that provides a platform for job seekers and recruiters.

Users can create an account, log in securely, view available job openings, and apply for jobs. Users with the **HR role** can create job postings and manage applications received for their posted jobs.

The project was developed to gain practical experience in building secure REST APIs, implementing authentication, managing database relationships, and following a layered backend architecture.

---

## Features

### For Candidates (USER)

* Register and log in securely
* View available job openings
* Apply for active jobs
* View submitted applications

### For HR

* Create job postings
* Manage job details
* View applications received for posted jobs

### Security

* Spring Security authentication
* JWT-based authentication
* Role-based authorization
* BCrypt password encryption

---

## Technologies Used

**Backend**

* Java
* Spring Boot
* Spring Security
* Spring Data JPA
* Hibernate
* REST APIs

**Database**

* MySQL

**Tools**

* IntelliJ IDEA
* Maven
* Docker
* Postman

---

## Project Structure

The application follows a layered architecture:

```
Controller
    |
Service
    |
Repository
    |
Database
```

Additional layers:

* Entity layer for database mapping
* Security layer for authentication and authorization
* Exception handling for managing errors

---

## Database Entities

### User

Stores user details and authentication information.

A user can have different roles:

* USER - Job seeker
* HR - Recruiter

### Job

Stores job posting details such as:

* Job title
* Required skills
* Role
* Package
* Status

### Application

Stores details about job applications submitted by users.

---

## Authentication Flow

1. User registers using email and password.
2. Password is encrypted before storing in the database.
3. User logs in and receives a JWT token.
4. The token is used to access secured APIs.
5. Access is controlled based on the user's role.

---

## Key Implementations

* REST API development using Spring Boot
* Entity relationships using Hibernate and JPA
* JWT authentication with Spring Security
* Role-based access control
* Application-level uniqueness validation
* Exception handling
* Docker containerization

---

## Running the Project

### Build the project

```bash
mvn clean package
```

### Build Docker Image

```bash
docker build -t jobportal .
```

### Run Docker Container

```bash
docker run -d -p 8080:8080 --name jobportal-container jobportal
```

---

## Future Improvements

* Resume upload feature
* Email notifications
* Advanced job search
* Pagination and sorting
* Refresh token support
* Cloud deployment

---

## What I Learned

While building this project, I gained hands-on experience with:

* Developing backend applications using Spring Boot
* Designing REST APIs
* Implementing authentication and authorization
* Working with databases using JPA and Hibernate
* Understanding real-world entity relationships
* Containerizing applications using Docker

---

## Author

**Harshith Soma**

Java Backend Developer
