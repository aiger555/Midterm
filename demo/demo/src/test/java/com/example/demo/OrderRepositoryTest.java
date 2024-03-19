package com.example.demo;

import com.example.demo.client.Client;
import com.example.demo.order.Order;
import com.example.demo.order.OrderRepository;
import com.example.demo.order.OrderService;
import com.example.demo.order.OrderStatus;
import com.example.demo.product.Product;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@DataJpaTest
public class OrderRepositoryTest {

    @MockBean
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @Test
    public void testFindAllOrders() {
        LocalDateTime createdAt1 = LocalDateTime.of(2024, 3, 25, 9, 22); // LocalDateTime for Product1
        LocalDate createdD = LocalDate.of(2024, 3, 25); // LocalDateTime for Product1

        // Given
        Client client = new Client(1L, "John Doe", "5248215214", createdD);
        Product product = new Product("Product A", "Description A", BigDecimal.valueOf(100), 10, createdAt1);

        Order order1 = new Order(client, product, 5, OrderStatus.PLACED, createdAt1);
        Order order2 = new Order(client, product, 10, OrderStatus.SHIPPED, createdAt1);
        List<Order> orders = List.of(order1, order2);

        // When
        when(orderRepository.findAll()).thenReturn(orders);
        List<Order> foundOrders = orderRepository.findAll();

        // Then
        assertThat(foundOrders).isNotNull();
        assertThat(foundOrders.size()).isEqualTo(2);
        assertThat(foundOrders).contains(order1, order2);
    }

    @Test
    public void testFindOrderById() {
        // Given
        LocalDate createdD = LocalDate.of(2024, 3, 25); // LocalDateTime for Product1
        LocalDateTime createdAt1 = LocalDateTime.of(2024, 3, 25, 9, 22); // LocalDateTime for Product1

        Long orderId = 1L;
        Client client = new Client(1L, "Dashsa Doe", "4154551551", createdD);
        Product product = new Product("Product B", "Description B", BigDecimal.valueOf(100), 10, createdAt1);

        Order order = new Order(client, product, 5, OrderStatus.PLACED, createdAt1);

        // When
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));
        Optional<Order> foundOrder = orderRepository.findById(orderId);

        // Then
        assertThat(foundOrder).isPresent();
        assertThat(foundOrder.get()).isEqualTo(order);
    }
    @Test
    public void testSaveOrder() {
        LocalDate createdD = LocalDate.of(2024, 3, 25); // LocalDateTime for Product1
        LocalDateTime createdAt1 = LocalDateTime.of(2024, 3, 25, 9, 22); // LocalDateTime for Product1

        // Given
        Client client = new Client(1L, "dcsfe Peffs", "4562145477", createdD);
        Product product = new Product("Product B", "Description B", BigDecimal.valueOf(100), 10, createdAt1);
        Order order = new Order(client, product, 5, OrderStatus.PLACED, createdAt1);

        // When
        when(orderRepository.save(order)).thenReturn(order);
        Order savedOrder = orderService.save(order);

        // Then
        assertThat(savedOrder).isNotNull();
        assertThat(savedOrder).isEqualTo(order);
    }

    @Test
    public void testDeleteOrder() {
        LocalDate createdD = LocalDate.of(2024, 3, 25); // LocalDateTime for Product1
        LocalDateTime createdAt1 = LocalDateTime.of(2024, 3, 25, 9, 22); // LocalDateTime for Product1

        // Given
        Long orderId = 1L;
        Client client = new Client(1L, "Marya Kim", "965452455", createdD);
        Product product = new Product("Product C", "Description C", BigDecimal.valueOf(100), 40, createdAt1);
        Order order = new Order(client, product, 2, OrderStatus.PLACED, createdAt1);

        // When
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));
        orderService.deleteOrder(orderId);

        // Then
        assertThat(orderRepository.findById(orderId)).isEmpty();
    }

}

