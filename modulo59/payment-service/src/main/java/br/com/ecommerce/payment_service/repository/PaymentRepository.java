package br.com.ecommerce.payment_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ecommerce.payment_service.model.PaymentTransaction;

public interface PaymentRepository extends JpaRepository<PaymentTransaction, Long> {
}