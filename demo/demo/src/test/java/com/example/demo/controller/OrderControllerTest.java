package com.example.demo.controller;

import com.example.demo.DTO.OrderDTO;
import com.example.demo.Mappers.OrderMapper;
import com.example.demo.client.ClientService;
import com.example.demo.Mappers.ClientMapper;
import com.example.demo.order.Order;
import com.example.demo.order.OrderController;
import com.example.demo.order.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private OrderService orderService;

    @Mock
    private OrderMapper orderMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllOrders() throws Exception {
        // Mocking service response
        List<Order> orders = Arrays.asList(new Order(), new Order());
        when(orderService.getAllOrders()).thenReturn(orders);

        // Mocking mapper response
        List<OrderDTO> orderDTOs = Arrays.asList(new OrderDTO(), new OrderDTO());
        when(orderMapper.toDtoList(orders)).thenReturn(orderDTOs);

        // Sending GET request
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/orders")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").exists());
    }
    @Test
    public void createOrder_WhenInvalidData_ReturnsBadRequest() throws Exception {
        // Arrange
        OrderDTO invalidOrderDto = new OrderDTO();
        invalidOrderDto.setProductId(Long.valueOf(""));

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(invalidOrderDto)))
                .andExpect(status().isBadRequest());
    }

    // Utility method to convert object to JSON string
    private String asJsonString(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
