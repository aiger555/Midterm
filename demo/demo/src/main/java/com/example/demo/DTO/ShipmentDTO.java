package com.example.demo.DTO;


import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;

public class ShipmentDTO {

    private Long id;
    @NotBlank(message = "recipientName cannot be blank")
    private String recipientName;
    private String address;
    private BigDecimal weight;
    @NotBlank(message = "trackingNumber cannot be blank")
    private String trackingNumber;

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }
}

