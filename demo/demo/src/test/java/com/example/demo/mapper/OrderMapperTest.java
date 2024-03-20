package com.example.demo.mapper;

import com.example.demo.DTO.OrderDTO;
import com.example.demo.Mappers.OrderMapper;
import com.example.demo.order.Order;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OrderMapperTest {

    private final OrderMapper orderMapper = Mappers.getMapper(OrderMapper.class);

    @Test
    public void testToDto() {
        // Given
        Order order = new Order();
        order.setId(1L);

        // When
        OrderDTO orderDTO = orderMapper.toDto(order);

        // Then
        assertEquals(order.getId(), orderDTO.getId());
    }

    @Test
    public void testToEntity() {
        // Given
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setId(1L);

        // When
        Order order = orderMapper.toEntity(orderDTO);

        // Then
        assertEquals(orderDTO.getId(), order.getId());
    }

    @Test
    public void testToDtoList() {
        // Given
        Order order1 = new Order();
        order1.setId(1L);

        Order order2 = new Order();
        order2.setId(2L);

        List<Order> orders = Arrays.asList(order1, order2);

        // When
        List<OrderDTO> orderDTOs = orderMapper.toDtoList(orders);

        // Then
        assertEquals(orders.size(), orderDTOs.size());
        assertEquals(orders.get(0).getId(), orderDTOs.get(0).getId());
        assertEquals(orders.get(1).getId(), orderDTOs.get(1).getId());

    }
}

