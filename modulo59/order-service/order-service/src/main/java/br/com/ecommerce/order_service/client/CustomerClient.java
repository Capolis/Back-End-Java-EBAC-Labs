package br.com.ecommerce.order_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "customer-service", url = "${customer.service.url:http://localhost:8081}")
public interface CustomerClient {
    
    @GetMapping("/api/customers/{id}/validate")
    void validateCustomer(@PathVariable("id") Long customerId);
}