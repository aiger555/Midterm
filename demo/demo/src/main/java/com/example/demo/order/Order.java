package com.example.demo.order;

import com.example.demo.client.Client;
import com.example.demo.product.Product;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer quantity;
    private OrderStatus status;

//    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public Order(Long id, Client client, Product product, Integer quantity, OrderStatus status, LocalDateTime createdAt) {
        this.id = id;
        this.client = client;
        this.product = product;
        this.quantity = quantity;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Order(Client client, Product product, Integer quantity, OrderStatus status, LocalDateTime createdAt) {
        this.client = client;
        this.product = product;
        this.quantity = quantity;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", client=" + client +
                ", product=" + product +
                ", quantity=" + quantity +
                ", status=" + status +
                ", createdAt=" + createdAt +
                '}';
    }

}
