Markdown
# Microservices Event-Driven Architecture

## Overview
This repository contains a robust, event-driven microservices architecture built with Spring Boot. It demonstrates a complete flow of order processing, utilizing Apache Kafka for asynchronous message distribution, MongoDB for NoSQL data persistence, and Discovery for service discovery. The system is fully observable, with distributed tracing implemented via Micrometer and Zipkin.

## System Architecture

| Service | Role | Port | Description |
|---|---|---|---|
| Discovery Server | Service Discovery | 8761 | Registry for all microservices |
| Order Service | Producer API | 8081 | REST API that receives orders and publishes to Kafka |
| Print Service | Kafka Consumer | 8082 | Consumes orders and outputs them to the console |
| Processing Service | Kafka Consumer | 8083 | Consumes orders and persists them to MongoDB |

## Prerequisites
* Java 21
* Maven 3.8+
* Docker

## Infrastructure Setup
The environment relies on containerized infrastructure. Run the following commands to start the necessary services:

```bash
docker run -d -p 27017:27017 --name mongodb mongo:latest
docker run -d -p 9411:9411 --name zipkin openzipkin/zipkin:latest
docker run -d -p 9092:9092 --name kafka bitnamilegacy/kafka:3.7.0
```

## 1. Execution Order

To prevent connection timeouts, start the applications in the following sequence:

* 1. Discovery Server
* 2. Order Service
* 3. Print Service
* 4. Processing Service