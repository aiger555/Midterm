package com.example.demo.order;
import com.example.demo.client.Client;
import com.example.demo.client.ClientRepository;
import com.example.demo.product.Product;
import com.example.demo.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;


@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProductRepository productRepository;

    // Get all orders
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    // Get order by ID
    public Order getOrderById(Long id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        return optionalOrder.orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + id));
    }

    // Create a new order
    public Order createOrder(Order order) {
        // Validate client and product exist
        Client client = clientRepository.findById(order.getClient().getId())
                .orElseThrow(() -> new EntityNotFoundException("Client not found with id: " + order.getClient().getId()));
        Product product = productRepository.findById(order.getProduct().getId())
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + order.getProduct().getId()));

        // Update product quantity if needed
        int newQuantity = product.getQuantity() - order.getQuantity();
        if (newQuantity < 0) {
            throw new IllegalArgumentException("Insufficient quantity for product: " + product.getId());
        }
        product.setQuantity(newQuantity);
        productRepository.save(product);

        order.setStatus(OrderStatus.PLACED);
        return orderRepository.save(order);
    }

    // Update an existing order
    public Order updateOrder(Long id, Order newOrder) {
        Order existingOrder = getOrderById(id);
        existingOrder.setClient(newOrder.getClient());
        existingOrder.setProduct(newOrder.getProduct());
        existingOrder.setQuantity(newOrder.getQuantity());
        existingOrder.setStatus(newOrder.getStatus());
        return orderRepository.save(existingOrder);
    }

    // Delete an order by ID
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }
}
