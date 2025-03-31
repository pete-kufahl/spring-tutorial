**Asynchronous MongoDB Operations with Kafka Consumer in Spring Boot**

# **Optimizing Kafka Consumer Performance with MongoDB**

## **1. Concurrency Model in Kafka Consumer**

Your Spring Boot Kafka consumer is configured using `ConcurrentKafkaListenerContainerFactory`, and the `@KafkaListener` method processes a **batch of messages** as a `List<ConsumerRecord<String, AvroMessage>>`.

By default, the **entire batch is processed sequentially** within a single thread. However, you can introduce concurrency by:

1. Configuring multiple Kafka consumer threads.
2. Using the `CompletableFuture` API to process each message asynchronously.

## **2. Controlling Kafka Batch Size**

Kafka batch size can be configured in `application.properties`:

```properties
spring.kafka.consumer.max-poll-records=500
spring.kafka.consumer.fetch-max-wait-ms=500
```

- `max-poll-records`: Controls the maximum number of records returned per poll.
- `fetch-max-wait-ms`: Controls the maximum wait time before returning messages.

## **3. Asynchronous MongoDB Operations Using CompletableFuture**

Your consumer processes messages by:

1. Checking if the message's `correlationId` exists in `EventStore`.
2. Marking duplicates (`status = DUPLICATE`).
3. Storing an event (`status = SUCCESS`).
4. Processing XML and saving it to `ExternalDocument` (`status = REQUEST`).
5. Sending the document to a remote partner.
6. Updating the document status (`OUTBOUND` or `FAILURE`).

### **3.1. Define Asynchronous MongoDB Repository Methods**

Modify your MongoDB repositories to return `CompletableFuture`.

#### **Event Store Repository**

```java
@Repository
public interface EventStoreRepository extends MongoRepository<EventEntity, String> {
    @Async
    CompletableFuture<Optional<EventEntity>> findByCorrelationId(String correlationId);
    
    @Async
    @Modifying
    @Query("{ 'correlationId' : ?0 }")
    @Update("{ '$set' : { 'status' : 'DUPLICATE' } }")
    CompletableFuture<Integer> markAsDuplicate(String correlationId);
}
```

#### **External Document Repository**

```java
@Repository
public interface ExternalDocumentRepository extends MongoRepository<ExternalDocument, String> {
    @Async
    @Modifying
    @Query("{ '_id' : ?0 }")
    @Update("{ '$set' : { 'status' : ?1 } }")
    CompletableFuture<Integer> updateStatus(String docId, String status);
}
```

### **3.2. Implement Async Service Layer**

```java
@Service
public class EventProcessingService {
    private final EventStoreRepository eventStoreRepository;
    private final ExternalDocumentRepository externalDocumentRepository;

    @Async
    public CompletableFuture<Boolean> checkDuplicateAndMark(String correlationId) {
        return eventStoreRepository.findByCorrelationId(correlationId)
            .thenCompose(eventOpt -> {
                if (eventOpt.isPresent()) {
                    return eventStoreRepository.markAsDuplicate(correlationId)
                        .thenApply(count -> true);
                }
                return CompletableFuture.completedFuture(false);
            });
    }

    @Async
    public CompletableFuture<Void> updateExternalDocumentStatus(String docId, String status) {
        return externalDocumentRepository.updateStatus(docId, status).thenAccept(count -> {
            if (count == 0) {
                System.err.println("Warning: No document updated for ID: " + docId);
            }
        });
    }
}
```

### **3.3. Modify Kafka Consumer to Use CompletableFuture**

```java
@Service
public class KafkaMessageConsumer {
    private final EventProcessingService eventProcessingService;
    private final DocumentSenderService documentSenderService;

    @KafkaListener(topics = "your-topic", containerFactory = "kafkaListenerContainerFactory")
    public void listen(List<ConsumerRecord<String, AvroMessage>> messages) {
        messages.forEach(record -> {
            AvroMessage message = record.value();
            String correlationId = message.getCorrelationId();

            eventProcessingService.checkDuplicateAndMark(correlationId)
                .thenCompose(isDuplicate -> {
                    if (isDuplicate) return CompletableFuture.completedFuture(null);
                    String processedXml = processXml(message.getXmlData());
                    ExternalDocument externalDoc = new ExternalDocument(correlationId, processedXml, "REQUEST");
                    return eventProcessingService.storeExternalDocument(externalDoc).thenApply(v -> externalDoc);
                })
                .thenCompose(externalDoc -> documentSenderService.sendDocument(externalDoc)
                    .thenCompose(success -> {
                        String newStatus = success ? "OUTBOUND" : "FAILURE";
                        return eventProcessingService.updateExternalDocumentStatus(externalDoc.getId(), newStatus);
                    })
                )
                .exceptionally(ex -> {
                    System.err.println("Error processing message: " + ex.getMessage());
                    return null;
                });
        });
    }

    private String processXml(String xml) {
        return "<processed>" + xml + "</processed>";
    }
}
```

### **3.4. Configure Async Support in Spring**

```java
@Configuration
@EnableAsync
public class AsyncConfig {}
```

## **4. Performance Optimizations**

### **4.1. Use a Custom Thread Pool**

```java
@Bean
public Executor taskExecutor() {
    ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
    executor.setCorePoolSize(10);
    executor.setMaxPoolSize(50);
    executor.setQueueCapacity(100);
    executor.setThreadNamePrefix("MongoAsync-");
    executor.initialize();
    return executor;
}
```

### **4.2. Use Batch Updates Instead of Individual Updates**

```java
@Modifying
@Query("{ 'correlationId': { $in: ?0 } }")
@Update("{ '$set': { 'status': 'DUPLICATE' } }")
CompletableFuture<Integer> markMultipleAsDuplicate(List<String> correlationIds);
```

## **5. Conclusion**

- **Non-blocking MongoDB operations** with `CompletableFuture`.
- **Parallelized processing** of Kafka messages.
- **Optimized performance** using thread pools and batch updates.
- **Better exception handling** via `exceptionally()`.

This ensures high **throughput and efficiency** in processing Kafka messages with MongoDB. 🚀

