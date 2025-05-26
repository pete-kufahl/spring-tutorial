# spring-retry
A Spring project that simulates failrues in interservice communication, with mitigation using Spring Retry.

Here we use:
🎣 Bass Tracker (client) & Lake Profile (server) Microservices

These are a pair of Spring Boot microservices built to demonstrate interservice communication without Spring Cloud.  
The **BassTracker** service creates and fetches lake data by interacting with the **LakeProfileService** over HTTP.

To simulate various failures, the Lake Profile service is run under various Spring profiles.
---

## 🧱 Architecture Overview
This project consists of two independent Spring Boot microservices that communicate via HTTP REST APIs.

### 1. LakeProfileService
Port: 8080

Responsibilities:
* Exposes REST endpoints to create and retrieve LakeProfile entities.
* Stores data in an in-memory H2 database.
* see: http://localhost:8080/swagger-ui/index.html

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
git clone https://github.com/pete-kufahl/spring-tutorial/spring-retry.git
cd spring-tutorial/spring-retry
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

## Simulating Communication Failures

### Simulator Profiles
The `default` profile of the Lake Profile service engages an H2 database (which gets wiped out and repopulated every startup cycle). Otherwise, there are various simulation profiles:

| Profile       | Behavior Description                                                                 |
|---------------|----------------------------------------------------------------------------------------|
| `normal`      | Healthy service behavior. No actual DB usage — all data is stored in memory.         |
| `timeout`     | Simulates high latency by delaying responses (e.g., hangs or times out).             |
| `socket`      | Fails with HTTP 503 for the first few requests, then recovers.                       |
| `flaky`       | Randomly fails 50% of the time with HTTP 503 to simulate instability.                |
| `unavailable` | Always fails with HTTP 503 to simulate full service downtime.                        |

### Running LakeProfileService with Simulator Profiles

You can activate different behavior profiles using the `spring.profiles.active` setting at runtime. These profiles simulate various failure scenarios or a healthy environment.

#### Running via Command Line (preferred)
Use the `--spring.profiles.active=<profile>` argument with `bootRun` or `java -jar`.

Examples:
```bash
# Normal behavior (in-memory service)
./gradlew :lake-profile-service:bootRun --args='--spring.profiles.active=normal'

# Simulate timeout
./gradlew :lake-profile-service:bootRun --args='--spring.profiles.active=timeout'

# Simulate socket failures (recovers after 1–2 failures)
./gradlew :lake-profile-service:bootRun --args='--spring.profiles.active=socket'

# Simulate flaky service (random failures)
./gradlew :lake-profile-service:bootRun --args='--spring.profiles.active=flaky'

# Simulate fully unavailable service
./gradlew :lake-profile-service:bootRun --args='--spring.profiles.active=unavailable'



