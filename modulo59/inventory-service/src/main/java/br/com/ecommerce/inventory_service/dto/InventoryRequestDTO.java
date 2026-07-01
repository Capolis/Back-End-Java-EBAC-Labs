package br.com.ecommerce.inventory_service.dto;

public class InventoryRequestDTO {

    private Long productId;
    private Integer availableQuantity;

    public InventoryRequestDTO() {
    }

    public InventoryRequestDTO(Long productId, Integer availableQuantity) {
        this.productId = productId;
        this.availableQuantity = availableQuantity;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Integer getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(Integer availableQuantity) {
        this.availableQuantity = availableQuantity;
    }
}