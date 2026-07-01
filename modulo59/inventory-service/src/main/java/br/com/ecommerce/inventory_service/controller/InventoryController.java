package br.com.ecommerce.inventory_service.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ecommerce.inventory_service.dto.InventoryRequestDTO;
import br.com.ecommerce.inventory_service.model.Inventory;
import br.com.ecommerce.inventory_service.repository.InventoryRepository;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {

    private final InventoryRepository inventoryRepository;

    public InventoryController(InventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }
    
    // Endpoint para listar todo o estoque
    @GetMapping
    public ResponseEntity<List<Inventory>> getAllInventory() {
        List<Inventory> inventories = inventoryRepository.findAll();
        return ResponseEntity.ok(inventories);
    }
    @PostMapping
    public ResponseEntity<Inventory> addInventory(@RequestBody InventoryRequestDTO request) {
        Inventory inventory = new Inventory(request.getProductId(), request.getAvailableQuantity());
        Inventory savedInventory = inventoryRepository.save(inventory);
        return ResponseEntity.status(201).body(savedInventory);
    }


    // Endpoint consumido pelo Order Service para reservar o estoque
    @PostMapping("/reserve")
    public ResponseEntity<Void> reserveProduct(
            @RequestParam("productId") Long productId,
            @RequestParam("quantity") Integer quantity) {

        Optional<Inventory> inventoryOpt = inventoryRepository.findById(productId);

        if (inventoryOpt.isPresent()) {
            Inventory inventory = inventoryOpt.get();
            
            // Verifica se tem estoque suficiente
            if (inventory.getAvailableQuantity() >= quantity) {
                // Subtrai a quantidade e salva no banco
                inventory.setAvailableQuantity(inventory.getAvailableQuantity() - quantity);
                inventoryRepository.save(inventory);
                return ResponseEntity.ok().build();
            }
        }

        // Retorna erro 400 se nao achar o produto ou nao tiver estoque
        return ResponseEntity.badRequest().build(); 
    }
}