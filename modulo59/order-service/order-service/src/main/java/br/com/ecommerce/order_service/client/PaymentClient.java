package br.com.ecommerce.order_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.ecommerce.order_service.dto.PaymentRequestDTO;

@FeignClient(name = "payment-service", url = "${payment.service.url:http://localhost:8083}")
public interface PaymentClient {
    
    // Envia o objeto DTO que sera convertido em JSON
    @PostMapping("/api/payments/process")
    void processPayment(@RequestBody PaymentRequestDTO request);
}