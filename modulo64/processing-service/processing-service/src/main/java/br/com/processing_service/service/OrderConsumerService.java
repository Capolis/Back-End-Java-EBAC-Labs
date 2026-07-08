package br.com.processing_service.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import br.com.processing_service.model.OrderDocument;
import br.com.processing_service.repository.OrderRepository;

@Service
public class OrderConsumerService {

    private final OrderRepository orderRepository;

    public OrderConsumerService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    // Listener o tópico "pedidos" sob o grupo "processing-group"
    @KafkaListener(topics = "pedidos", groupId = "processing-group")
    public void consumeOrder(OrderDocument orderDocument) {
        orderDocument.setStatus("PROCESSED"); // Altera o status antes de persistir
        orderRepository.save(orderDocument);  // Grava o documento de forma flexível no NoSQL
        System.out.println("Order status updated and saved to MongoDB: " + orderDocument.getId());
    }
}