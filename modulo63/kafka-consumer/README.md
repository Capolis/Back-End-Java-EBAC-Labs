# Kafka Consumer Service

This microservice operates asynchronously, subscribing to a specific Kafka topic to consume and process messages published by the Producer service.

## Tech Stack
* **Java**
* **Spring Boot**
* **Spring Kafka**

## Configuration

The application is configured in `src/main/resources/application.yml` with the following properties:
* **Server Port**: `8082` (Isolated from the Producer)
* **Kafka Broker**: `localhost:9092`
* **Topic Subscribed**: `practice-topic`
* **Consumer Group ID**: `practice-group`
* **Deserializers**: Uses standard `StringDeserializer` to read the incoming payloads.

## How it Works

The service uses the `@KafkaListener` annotation on the `MessageConsumer` class. It constantly polls the broker and, upon detecting a new event in `practice-topic`, immediately triggers the execution method, logging the payload to the console.

Unlike the Producer, this service does not expose any HTTP endpoints. It is purely event-driven.
