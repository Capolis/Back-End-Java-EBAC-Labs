package br.com.ecommerce.inventory_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ecommerce.inventory_service.model.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}