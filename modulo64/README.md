# Processing Service

## Overview
The `processing-service` acts as the persistence layer consumer. Operating under the `processing-group`, it listens to the `pedidos` topic independently from the Print Service. 

It consumes the JSON message, maps it directly to the `OrderDocument` entity, updates the order status to `PROCESSED`, and saves the record permanently into a MongoDB database.

## Technologies
* Java 21
* Spring Boot 3.2.5
* Spring Kafka
* Spring Data MongoDB
* Micrometer Tracing (Brave)
* Eureka Client

## Database Details
* **Engine:** MongoDB
* **Database Name:** `orders_db`
* **Target Collection:** `OrderDocument`

## Execution
Ensure Kafka, MongoDB, Zipkin, and the Discovery Server are running before initialization.

```bash
mvn spring-boot:run
```