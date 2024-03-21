package com.example.demo.controller;

import com.example.demo.DTO.OrderDTO;
import com.example.demo.Mappers.OrderMapper;
import com.example.demo.order.Order;
import com.example.demo.order.OrderService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/orders")
@Validated
public class OrderController {

    @Autowired
    private OrderService orderService;

    private OrderMapper orderMapper;

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id) {
        try {
            Order order = orderService.getOrderById(id);
            if (order == null) {
                throw new EntityNotFoundException("Order not found with id: " + id);
            }
            OrderDTO orderDto = orderMapper.toDto(order);
            return new ResponseEntity<>(orderDto, HttpStatus.OK);
        } catch (EntityNotFoundException ex) {
            return new ResponseEntity<>(new OrderDTO(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(new OrderDTO(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@Valid @RequestBody OrderDTO orderDto) {
        try {
            Order order = orderMapper.toEntity(orderDto);
            Order newOrder = orderService.createOrder(order);
            OrderDTO newOrderDto = orderMapper.toDto(newOrder);
            return new ResponseEntity<>(newOrderDto, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(new OrderDTO(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable Long id, @RequestBody OrderDTO orderDto) {
        try {
            Order order = orderMapper.toEntity(orderDto);
            Order updatedOrder = orderService.updateOrder(id, order);
            OrderDTO updatedOrderDto = orderMapper.toDto(updatedOrder);
            return new ResponseEntity<>(updatedOrderDto, HttpStatus.OK);
        } catch (EntityNotFoundException ex) {
            return new ResponseEntity<>(orderDto, HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(orderDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        try {
            orderService.deleteOrder(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
