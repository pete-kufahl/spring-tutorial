# spring-retry
🎣 Bass Tracker & Lake Profile Microservices

A pair of Spring Boot microservices built to demonstrate inter-service communication without Spring Cloud.  
The **BassTracker** service creates and fetches lake data by interacting with the **LakeProfileService** over HTTP.

---

## 🧱 Architecture Overview
This project consists of two independent Spring Boot microservices that communicate via HTTP REST APIs.

### 1. LakeProfileService
Port: 8080

Responsibilities:
* Exposes REST endpoints to create and retrieve LakeProfile entities.
* Stores data in an in-memory H2 database.

### 2. BassTracker
Port: 8081

Responsibilities:
* Acts as a REST client to LakeProfileService.
* Orchestrates the following operation:
  * Sends a POST request to create a new LakeProfile.
  * Waits a few seconds.
  * Sends a GET request to retrieve the newly created LakeProfile by its ID.

---

## 🛠️ Technologies Used

- Java 17
- Spring Boot 3.2.x
- Spring Web
- Spring Data JPA
- H2 In-Memory Database
- RestTemplate
- Gradle

---

## 🔧 Running the Microservices

Make sure both microservices are running simultaneously on their respective ports:

| Service            | Port | Description                              |
|--------------------|------|------------------------------------------|
| LakeProfileService | 8080 | Manages LakeProfile persistence          |
| BassTracker        | 8081 | Client service that calls LakeProfileService |

### 1. Clone the project

```bash
git clone https://github.com/your-username/lake-bass-microservices.git
cd lake-bass-microservices
```

### 2. Run LakeProfileService
```bash
cd lake-profile-service
./gradlew bootRun
```
You should see the application start on port 8080.

### 3. Run BassTracker
open a new terminal:
```bash
cd bass-tracker
./gradlew bootRun
```
You should see the application start on port 8081.



