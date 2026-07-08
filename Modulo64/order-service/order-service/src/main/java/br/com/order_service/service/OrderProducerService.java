package br.com.order_service.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import br.com.order_service.model.Order;

@Service
public class OrderProducerService {

    private static final String TOPIC = "pedidos";
    private final KafkaTemplate<String, Object> kafkaTemplate;

    private static final Logger log = LoggerFactory.getLogger(OrderProducerService.class);

    public OrderProducerService(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    // Envia o pedido para o Kafka
    public void sendOrder(Order order) {
        kafkaTemplate.send(TOPIC, order.getId(), order);

        log.info("Order sent to Kafka via SLF4J: {}", order.getId());
    }
}