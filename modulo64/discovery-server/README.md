# Discovery Server

## Overview
This is the Service Discovery component of the architecture, built with Spring Cloud Netflix Eureka. It acts as a central registry where all other microservices register their network locations (IP and port) upon startup, allowing them to find and communicate with each other dynamically.

## Technologies
* Java 21
* Spring Boot 3.2.5
* Spring Cloud Netflix Eureka Server

## Execution
Ensure the default port `8761` is available on your machine.

```bash
mvn spring-boot:run
Access the Discovery Dashboard via your browser at http://localhost:8761.
```
