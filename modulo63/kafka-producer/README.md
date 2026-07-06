# Kafka Producer Service

This microservice acts as the entry point for data in our event-driven architecture. It provides a RESTful endpoint to receive payloads and publishes them directly to an Apache Kafka topic.

## Tech Stack
* **Java**
* **Spring Boot Web**
* **Spring Kafka**

## Configuration

The application is configured in `src/main/resources/application.yml` with the following key properties:
* **Server Port**: `8081` (Isolated to prevent conflicts)
* **Kafka Broker**: `localhost:9092`
* **Topic Name**: `practice-topic`
* **Serializers**: Uses standard `StringSerializer` for both keys and values.

## API Reference

### Publish a new message

#Responses: **200 OK**
