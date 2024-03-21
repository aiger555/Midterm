package com.example.demo.DTO;

import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public class ProductDTO {

    private Long id;
    @NotBlank(message = "Name cannot be blank")
    private String name;
    private BigDecimal price;
    private int quantity;

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}

