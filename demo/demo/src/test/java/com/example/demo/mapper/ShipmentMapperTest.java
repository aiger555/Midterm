package com.example.demo.mapper;

import com.example.demo.DTO.ShipmentDTO;
import com.example.demo.Mappers.ShipmentMapper;
import com.example.demo.shipment.Shipment;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class ShipmentMapperTest {

    private final ShipmentMapper shipmentMapper = Mappers.getMapper(ShipmentMapper.class);

    @Test
    public void testToDto() {
        // Given
        Shipment shipment = new Shipment();
        shipment.setId(1L);

        // When
        ShipmentDTO shipmentDTO = shipmentMapper.toDto(shipment);

        // Then
        assertEquals(shipment.getId(), shipmentDTO.getId());
    }

    @Test
    public void testToEntity() {
        // Given
        ShipmentDTO shipmentDTO = new ShipmentDTO();
        shipmentDTO.setId(1L);

        // When
        Shipment shipment = shipmentMapper.toEntity(shipmentDTO);

        // Then
        assertEquals(shipmentDTO.getId(), shipment.getId());

    }

    @Test
    public void testToDtoList() {
        // Given
        Shipment shipment1 = new Shipment();
        shipment1.setId(1L);

        Shipment shipment2 = new Shipment();
        shipment2.setId(2L);

        List<Shipment> shipments = Arrays.asList(shipment1, shipment2);

        // When
        List<ShipmentDTO> shipmentDTOs = shipmentMapper.toDtoList(shipments);

        // Then
        assertEquals(shipments.size(), shipmentDTOs.size());
        assertEquals(shipments.get(0).getId(), shipmentDTOs.get(0).getId());
        assertEquals(shipments.get(1).getId(), shipmentDTOs.get(1).getId());

    }
}

