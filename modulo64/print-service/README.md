# Print Service

## Overview
The `print-service` is a Kafka Consumer microservice. It is configured to listen to the `pedidos` topic under the specific consumer group `print-group`. Its primary responsibility is to fetch messages in real-time, safely deserialize the JSON payload, and output the data to the system console.

This service utilizes an `ErrorHandlingDeserializer` to prevent continuous crash loops in the event of malformed data headers.

## Technologies
* Java 21
* Spring Boot 3.2.5
* Spring Kafka
* Micrometer Tracing (Brave)
* Eureka Client

## Execution
Ensure Kafka and the Discovery Server are running before initialization.

```bash
mvn spring-boot:run
```