# tickets
SpringBoot application following *Spring Boot 3 Fundamentals* course.
* Spring Boot 3
  * Java 17
  * maven
* docker
  * `docker compose up -d`
  * to populate databases with test data, run app with test-data profile
    * `./mvnw spring-boot:run -Dspring-boot.run.profiles=testdata`
* mongoDB
  * confirm setup with Compass
* postgreSQL
  * check startup tables: `docker exec -it tickets-postgres-1 psql -U tickets -d tickets`
    * `\dt`
    * `\select * from events` and so on