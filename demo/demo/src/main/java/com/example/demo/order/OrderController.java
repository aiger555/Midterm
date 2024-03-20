package com.example.demo.order;

import com.example.demo.order.OrderService;
import com.example.demo.DTO.OrderDTO;
import com.example.demo.Mappers.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<OrderDTO> orderDtos = OrderMapper.INSTANCE.toDtoList(orderService.getAllOrders());
        return new ResponseEntity<>(orderDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable Long id) {
        OrderDTO orderDto = OrderMapper.INSTANCE.toDto(orderService.getOrderById(id));
        return new ResponseEntity<>(orderDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderDTO> createOrder(@RequestBody OrderDTO orderDto) {
        Order createdOrder = orderService.createOrder(OrderMapper.INSTANCE.toEntity(orderDto));
        OrderDTO createdOrderDto = OrderMapper.INSTANCE.toDto(createdOrder);
        return new ResponseEntity<>(createdOrderDto, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable Long id, @RequestBody OrderDTO orderDto) {
        Order updatedOrder = orderService.updateOrder(id, OrderMapper.INSTANCE.toEntity(orderDto));
        OrderDTO updatedOrderDto = OrderMapper.INSTANCE.toDto(updatedOrder);
        return new ResponseEntity<>(updatedOrderDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
