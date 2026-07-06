package br.com.modulo63.kafka_producer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.modulo63.kafka_producer.producer.MessageProducer;

@RestController
@RequestMapping("/api/v1/messages")
public class MessageController {

    private final MessageProducer messageProducer;

    public MessageController(MessageProducer messageProducer) {
        this.messageProducer = messageProducer;
    }

    @PostMapping
    public ResponseEntity<String> publish(@RequestBody String message) {
        messageProducer.sendMessage(message);
        return ResponseEntity.ok("Message processed successfully");
    }
}