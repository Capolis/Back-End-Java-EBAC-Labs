# Order Service

## Overview
The `order-service` is the main entry point of the system. It exposes a RESTful API to receive new order requests. Upon receiving a request, it generates a unique identifier, logs the execution context using SLF4J, and publishes the payload as an event to the Apache Kafka topic `pedidos`.

This service is equipped with Micrometer Tracing and Zipkin Reporter to initiate the distributed tracing chain.

## Technologies
* Java 21
* Spring Boot 3.2.5
* Spring Web & Spring Kafka
* Micrometer Tracing (Brave) & Zipkin
* Eureka Client

## API Reference
**Endpoint:** `POST /orders`
Accepts a JSON payload representing the order parameters.

## Execution
Ensure Kafka, Zipkin, and the Discovery Server are running before initialization.

```bash
mvn spring-boot:run
```