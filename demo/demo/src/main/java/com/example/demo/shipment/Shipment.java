package com.example.demo.shipment;

import com.example.demo.order.Order;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "shipments")
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private ShipmentStatus status;
    private String shippingAddress;
    private LocalDate estimatedDeliveryDate;
    private LocalDate actualDeliveryDate;

    @Column(name = "created_at")
    private LocalDateTime createdAt;


    public Shipment(Long id, Order order, ShipmentStatus status, String shippingAddress, LocalDate estimatedDeliveryDate, LocalDate actualDeliveryDate, LocalDateTime createdAt) {
        this.id = id;
        this.order = order;
        this.status = status;
        this.shippingAddress = shippingAddress;
        this.estimatedDeliveryDate = estimatedDeliveryDate;
        this.actualDeliveryDate = actualDeliveryDate;
        this.createdAt = createdAt;
    }

    public Shipment(Order order, ShipmentStatus status, String shippingAddress, LocalDate estimatedDeliveryDate, LocalDate actualDeliveryDate, LocalDateTime createdAt) {
        this.order = order;
        this.status = status;
        this.shippingAddress = shippingAddress;
        this.estimatedDeliveryDate = estimatedDeliveryDate;
        this.actualDeliveryDate = actualDeliveryDate;
        this.createdAt = createdAt;
    }

    public Shipment() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public ShipmentStatus getStatus() {
        return status;
    }

    public void setStatus(ShipmentStatus status) {
        this.status = status;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public LocalDate getEstimatedDeliveryDate() {
        return estimatedDeliveryDate;
    }

    public void setEstimatedDeliveryDate(LocalDate estimatedDeliveryDate) {
        this.estimatedDeliveryDate = estimatedDeliveryDate;
    }

    public LocalDate getActualDeliveryDate() {
        return actualDeliveryDate;
    }

    public void setActualDeliveryDate(LocalDate actualDeliveryDate) {
        this.actualDeliveryDate = actualDeliveryDate;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Shipment{" +
                "id=" + id +
                ", order=" + order +
                ", status=" + status +
                ", shippingAddress='" + shippingAddress + '\'' +
                ", estimatedDeliveryDate=" + estimatedDeliveryDate +
                ", actualDeliveryDate=" + actualDeliveryDate +
                ", createdAt=" + createdAt +
                '}';
    }
}
