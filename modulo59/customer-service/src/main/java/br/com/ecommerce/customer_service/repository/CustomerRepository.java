package br.com.ecommerce.customer_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ecommerce.customer_service.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}