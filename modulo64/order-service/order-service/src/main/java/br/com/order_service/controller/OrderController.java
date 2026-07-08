package br.com.order_service.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.order_service.model.Order;
import br.com.order_service.service.OrderProducerService;
import io.micrometer.observation.ObservationRegistry;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderProducerService orderProducerService;
    private final ObservationRegistry observationRegistry;
    
    // Lista em memória para resolver o GET/orders no order-service, o banco de dados oficial fica no processing-service
    private final List<Order> localOrderCache = new ArrayList<>();

    public OrderController(OrderProducerService orderProducerService, ObservationRegistry observationRegistry) {
        this.orderProducerService = orderProducerService;
        this.observationRegistry = observationRegistry;
    }

    // Cria um novo pedido e publica no Kafka
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        return io.micrometer.observation.Observation.createNotStarted("criacao-de-pedido", observationRegistry)
                .observe(() -> {
                    
                    // Tudo que rodar aqui dentro será rastreado à força
                    orderProducerService.sendOrder(order);
                    localOrderCache.add(order);
                    
                    return ResponseEntity.ok(order);
                    
                });
    }

    // DESAFIO: Consulta GET para listar os pedidos cadastrados
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        return ResponseEntity.ok(localOrderCache);
    }
}