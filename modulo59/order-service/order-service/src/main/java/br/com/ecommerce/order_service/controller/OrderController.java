package br.com.ecommerce.order_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ecommerce.order_service.dto.OrderRequest;
import br.com.ecommerce.order_service.dto.OrderResponse;
import br.com.ecommerce.order_service.service.OrderCheckoutService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderCheckoutService orderCheckoutService;

    public OrderController(OrderCheckoutService orderCheckoutService) {
        this.orderCheckoutService = orderCheckoutService;
    }

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest request) {
        OrderResponse response = orderCheckoutService.processCheckout(request);
        return ResponseEntity.ok(response);
    }

    public OrderCheckoutService getOrderCheckoutService() {
        return orderCheckoutService;
    }

    
}