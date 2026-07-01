package br.com.ecommerce.customer_service.dto;

public class CustomerRequestDTO {

    private String name;
    private Boolean active;

    public CustomerRequestDTO() {
    }

    public CustomerRequestDTO(String name, Boolean active) {
        this.name = name;
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}