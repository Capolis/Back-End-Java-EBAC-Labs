# Spring Boot & Kafka Microservices Practice

A practical project demonstrating asynchronous communication between microservices using **Spring Boot** and **Apache Kafka**. This repository contains a multi-instance setup to simulate a real-world event-driven architecture.

## 🏗️ Architecture overview

The project is divided into two independent microservices:
1. **Kafka Producer (`kafka_producer`)**: Exposes a REST API to receive text messages and publishes them to a Kafka topic. Runs on port `8081`.
2. **Kafka Consumer (`kafka_consumer`)**: Continuously listens to the Kafka topic and processes incoming messages. Runs on port `8082`.

## 🚀 Prerequisites

Before you begin, ensure you have the following installed on your machine:
* **Java 21+**
* **Maven**
* **Apache Kafka** (Configured locally in KRaft standalone mode on `localhost:9092`)

## ⚙️ How to Run and Test

**Step 1: Start the Kafka Server**
Ensure your local Kafka broker is up and running in standalone mode (KRaft) and listening on port `9092`.

**Step 2: Start the Producer Service**
Navigate to the `kafka_producer` directory and run the application

**Step 3: Start the Consumer Service**
Open a new terminal, navigate to the kafka_consumer directory, and run

**Step 4: Send a Message**
With both applications running, use a tool like Postman, Insomnia, or cURL to send a POST request to the Producer (everything sent will be a String)

**Step 5: Verify the Output**
Check the console logs of your Consumer application. You should see the message being received and printed in real-time!