package com.example.demo.DTO;


import jakarta.validation.constraints.NotBlank;

public class OrderDTO {
    private Long id;
    @NotBlank(message = "clientID cannot be blank")
    private Long clientId;
    @NotBlank(message = "productID cannot be blank")
    private Long productId;

    private int quantity;
    private String status;

    // Constructors
    public OrderDTO() {
    }

    public OrderDTO(Long id, Long clientId, Long productId, int quantity, String status) {
        this.id = id;
        this.clientId = clientId;
        this.productId = productId;
        this.quantity = quantity;
        this.status = status;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
