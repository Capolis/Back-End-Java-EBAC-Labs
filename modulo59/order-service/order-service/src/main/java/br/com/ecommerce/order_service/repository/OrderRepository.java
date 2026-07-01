package br.com.ecommerce.order_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ecommerce.order_service.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}