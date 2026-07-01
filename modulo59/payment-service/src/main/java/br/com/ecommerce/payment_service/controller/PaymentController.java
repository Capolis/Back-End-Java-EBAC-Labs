package br.com.ecommerce.payment_service.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ecommerce.payment_service.dto.PaymentRequestDTO;
import br.com.ecommerce.payment_service.model.PaymentTransaction;
import br.com.ecommerce.payment_service.repository.PaymentRepository;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentRepository paymentRepository;

    public PaymentController(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    // Endpoint para processar os pedidos
    @PostMapping("/process")
    public ResponseEntity<Void> processPayment(@RequestBody PaymentRequestDTO request) {
        
        // Simula a aprovacao criando o registro com status SUCCESS
        PaymentTransaction transaction = new PaymentTransaction(
            request.getCustomerId(), 
            request.getAmount(), 
            "SUCCESS"
        );
        
        paymentRepository.save(transaction);
        return ResponseEntity.ok().build();
    }

    // Novo endpoint para listar as transacoes e validar se o dinheiro entrou
    @GetMapping
    public ResponseEntity<List<PaymentTransaction>> getAllTransactions() {
        List<PaymentTransaction> transactions = paymentRepository.findAll();
        return ResponseEntity.ok(transactions);
    }
}