# tickets
SpringBoot application following *Spring Boot 3 Fundamentals* course.

## modules
* **events-service** uses SQL to load tables related to events, organizers, venues and products
* **registration-service** no noSQL to store user-driven registrations to events
  * makes calls to events-service
  
## stack
* Spring Boot 3
  * Java 17
  * maven
* docker
  * `docker compose up -d`
  * to populate databases with test data, run **events-service** with test-data profile
    * `cd events-service`
    * `../mvnw spring-boot:run -Dspring-boot.run.profiles=testdata`
* mongoDB
  * confirm setup with Compass
* postgreSQL
  * check startup tables: `docker exec -it tickets-postgres-1 psql -U tickets -d tickets`
    * `\dt`
    * `\select * from events` and so on
* web
  * WebClient
  * Http Service Proxy

