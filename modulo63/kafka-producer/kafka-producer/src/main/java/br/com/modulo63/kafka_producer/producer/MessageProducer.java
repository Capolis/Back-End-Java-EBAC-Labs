package br.com.modulo63.kafka_producer.producer;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private static final String TOPIC_NAME = "practice-topic";

    public MessageProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    // Envia a string para o tópico configurado
    public void sendMessage(String message) {
        kafkaTemplate.send(TOPIC_NAME, message);
        System.out.println("Message sent to Kafka: " + message);
    }
}