# ATS Resume Analyzer

A backend application that analyzes resumes against job descriptions using Groq AI. Gives ATS scores, highlights gaps, and suggests improvements so users can tailor their resumes before applying.

---

## Tech Stack

- Java, Spring Boot
- Groq AI API
- Spring Security + JWT + OAuth 2.0
- MySQL, Spring Data JPA
- Maven

---

## Features

- Resume builder — store and manage resume data
- Job description input for targeted analysis
- ATS score based on resume vs JD match
- Groq AI powered improvement suggestions
- Live resume preview
- Google login via OAuth 2.0
- JWT secured endpoints

---

## How it works

1. User adds resume details
2. Pastes the job description
3. App runs analysis and generates ATS score
4. Groq AI gives specific improvement suggestions
5. User updates resume and previews before use

---

## Project Structure

```
src/
├── controller/     API request handling
├── service/        Business logic + Groq AI calls
├── repository/     Database layer (JPA)
├── entity/         DB models
├── dto/            Data transfer objects
└── config/         Security and OAuth config
```

---

## Setup

1. Clone the repo
```bash
git clone https://github.com/Lokeshsijapati/AtsResume.git
cd AtsResume
```

2. Add your credentials in `application.properties`
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ats_db
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD

groq.api.key=YOUR_GROQ_API_KEY

spring.security.oauth2.client.registration.google.client-id=YOUR_CLIENT_ID
spring.security.oauth2.client.registration.google.client-secret=YOUR_CLIENT_SECRET
```

3. Run
```bash
mvn spring-boot:run
```

---

## Contact

Lokesh Sijapati — [GitHub](https://github.com/Lokeshsijapati)
