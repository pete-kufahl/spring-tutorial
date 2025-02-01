# loanz
Springboot-initiated backend for a full-stack underwriting application.
## Initializr setup
* Project: Maven
* Language: Java
* Spring Boot Version: 3.x (compatible with Java 17)
* Dependencies:
  * Spring Web (for REST API)
  * Spring Data JPA (for database interaction)
  * PostgreSQL Driver (for PostgreSQL database)
  * Lombok (to reduce boilerplate code)
  * Spring Boot Actuator (for monitoring)
  * Spring for Apache Kafka (messaging)
  
## `application.properties` for postgreSQL connectio
```commandline
spring.datasource.url=jdbc:postgresql://localhost:5432/loan_underwriting_db
spring.datasource.username=<USERNAME>
spring.datasource.password=<PASSWORD>
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```