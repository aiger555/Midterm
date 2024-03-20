package com.example.demo.controller;

import com.example.demo.shipment.Shipment;
import com.example.demo.shipment.ShipmentController;
import com.example.demo.shipment.ShipmentService;
import com.example.demo.Mappers.ShipmentMapper;
import com.example.demo.DTO.ShipmentDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@WebMvcTest(ShipmentController.class)
@AutoConfigureMockMvc
public class ShipmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ShipmentService shipmentService;

    @Mock
    private ShipmentMapper shipmentMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllShipments() throws Exception {
        // Mocking service response
        List<Shipment> shipments = Arrays.asList(new Shipment(), new Shipment());
        when(shipmentService.getAllShipments()).thenReturn(shipments);

        // Mocking mapper response
        List<ShipmentDTO> shipmentDTOs = Arrays.asList(new ShipmentDTO(), new ShipmentDTO());
        when(shipmentMapper.toDtoList(shipments)).thenReturn(shipmentDTOs);

        // Sending GET request
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/shipments")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").exists());
    }

    // Add more test methods for other endpoints...
}
