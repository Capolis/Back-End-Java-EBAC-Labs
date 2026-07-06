package br.com.modulo63.kafka_consumer.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class MessageConsumer {

    // Listener acionado automaticamente assim que uma nova mensagem chega ao tópico
    @KafkaListener(topics = "practice-topic", groupId = "practice-group")
    public void consume(String message) {
        System.out.println("Message received from Kafka: " + message);
    }
}