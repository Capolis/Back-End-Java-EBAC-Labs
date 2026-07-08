package br.com.print_service.service;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import br.com.print_service.model.OrderMessage;

@Service
public class OrderPrintService {

    // DESAFIO: Criar contador de quantos pedidos foram gerados no sistema
    private final AtomicInteger totalOrdersCounter = new AtomicInteger(0);

    @KafkaListener(topics = "pedidos", groupId = "print-group")
    public void printOrder(OrderMessage orderMessage) {
        int currentCount = totalOrdersCounter.incrementAndGet();
        
        System.out.println("========================================");
        System.out.println("PRINTING SERVICE - NEW MESSAGE DETECTED");
        System.out.println("Order ID: " + orderMessage.getId());
        System.out.println("Customer: " + orderMessage.getCustomerName());
        System.out.println("Product: " + orderMessage.getProduct());
        System.out.println("Quantity: " + orderMessage.getQuantity());
        
        // DESAFIO: Implementar e simular atualização de status direto no log
        simulateStatusUpdate(orderMessage, "PRINTING");
        simulateStatusUpdate(orderMessage, "COMPLETED");
        
        System.out.println("Total generated orders tracked by consumer: " + currentCount);
        System.out.println("========================================");
    }

    private void simulateStatusUpdate(OrderMessage orderMessage, String updatedStatus) {
        orderMessage.setStatus(updatedStatus);
        System.out.println("[LOG STATUS UPDATE] -> Order: " + orderMessage.getId() + " is now " + updatedStatus);
    }

    public int getTotalOrdersCount() {
        return totalOrdersCounter.get();
    }
}