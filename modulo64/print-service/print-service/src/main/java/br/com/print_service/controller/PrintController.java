package br.com.print_service.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.print_service.service.OrderPrintService;

@RestController
@RequestMapping("/metrics/orders")
public class PrintController {

    private final OrderPrintService orderPrintService;

    public PrintController(OrderPrintService orderPrintService) {
        this.orderPrintService = orderPrintService;
    }

    // Endpoint para consultar o total de pedidos impressos/processados por este serviço
    @GetMapping("/total")
    public ResponseEntity<Map<String, Integer>> getTotalPrintedOrders() {
        int total = orderPrintService.getTotalOrdersCount();
        return ResponseEntity.ok(Map.of("totalOrdersProcessed", total));
    }
}