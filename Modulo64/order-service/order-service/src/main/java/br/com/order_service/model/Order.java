package br.com.order_service.model;

import java.util.UUID;

// Modelo de domínio do Pedido
public class Order {
    private String id;
    private String customerName;
    private String product;
    private Integer quantity;
    private Double price;
    private String status;

    public Order() {
        this.id = UUID.randomUUID().toString(); // Gera um ID único automaticamente
        this.status = "CREATED";
    }

    // Getters e Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}