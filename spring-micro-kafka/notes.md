# Spring Kafka Tutorial - 4 Microservices

## set up kafka (using Zookeeper)
2 terminals in kafka directory ...

1> `bin/zookeeper-server-start.sh config/zookeeper.properties`

2> `bin/kafka-server-start.sh config/server.properties`

### test in another terminal
* `kafka-topics.sh --create --topic topic-example --bootstrap-server localhost:9092`
* `kafka-console-producer.sh --topic topic-example --bootstrap-server localhost:9092`
can enter some text, ^C
* `kafka-console-consumer.sh --topic topic-example --bootstrap-server localhost:9092 --from-beginning`

## Spring Initializr
* maven project, java 17
* dependency: spring web
* dependency: spring for apache kafka

### generate three services (artifacts) with these settings
* order-service
* stock-service
* email-service

### generate another microservice
* base-domains
* remove the spring web and apache kafka dependencies, replace with lombok dependency

## setup microservices as four projects under one workspace
* move all generated project .zip files into a parent directory
* open under Intellij, wait for it to detect the pom.xml files, load them
* the three "service" uservices need to run on different ports
  * by default, tomcat runs on 8080 --> order-service
  * change `application.properties` - `server.port=8081` in stock-service
  * `application.properties` - `server.port=8082` in email-service

**IntelliJ lombok plugin does not appear to be working with the IDE compiler, even with the suggested workarounds. Adding your own setters, getters and toString() may be necessary.**

### test order-service
* with service on 8080, POST a request to http://localhost:8080/api/v1/orders
```json
{
    "name": "computer",
    "qty": 1,
    "price": 1200
}
```
