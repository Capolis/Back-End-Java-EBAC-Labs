package br.com.ecommerce.order_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "inventory-service", url = "${inventory.service.url:http://localhost:8082}")
public interface InventoryClient {
    
    @PostMapping("/api/inventory/reserve")
    void reserveProduct(@RequestParam("productId") Long productId, @RequestParam("quantity") Integer quantity);
}