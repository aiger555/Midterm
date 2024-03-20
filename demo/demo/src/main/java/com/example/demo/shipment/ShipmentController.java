package com.example.demo.shipment;

import com.example.demo.DTO.ShipmentDTO;
import com.example.demo.Mappers.ShipmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/shipments")
public class ShipmentController {

    @Autowired
    private ShipmentService shipmentService;

    private ShipmentMapper shipmentMapper;

    // Get all shipments
    @GetMapping
    public ResponseEntity<List<ShipmentDTO>> getAllShipments() {
        List<Shipment> shipments = shipmentService.getAllShipments();
        List<ShipmentDTO> shipmentDtos = shipmentMapper.toDtoList(shipments);
        return new ResponseEntity<>(shipmentDtos, HttpStatus.OK);
    }

    // Get shipment by ID
    @GetMapping("/{id}")
    public ResponseEntity<ShipmentDTO> getShipmentById(@PathVariable Long id) {
        Shipment shipment = shipmentService.getShipmentById(id);
        ShipmentDTO shipmentDto = shipmentMapper.toDto(shipment);
        return new ResponseEntity<>(shipmentDto, HttpStatus.OK);
    }

    // Create a new shipment
    @PostMapping
    public ResponseEntity<ShipmentDTO> createShipment(@RequestBody ShipmentDTO shipmentDto) {
        Shipment shipment = shipmentMapper.toEntity(shipmentDto);
        Shipment newShipment = shipmentService.createShipment(shipment);
        ShipmentDTO newShipmentDto = shipmentMapper.toDto(newShipment);
        return new ResponseEntity<>(newShipmentDto, HttpStatus.CREATED);
    }

    // Update an existing shipment
    @PutMapping("/{id}")
    public ResponseEntity<ShipmentDTO> updateShipment(@PathVariable Long id, @RequestBody ShipmentDTO shipmentDto) {
        Shipment shipment = shipmentMapper.toEntity(shipmentDto);
        Shipment updatedShipment = shipmentService.updateShipment(id, shipment);
        ShipmentDTO updatedShipmentDto = shipmentMapper.toDto(updatedShipment);
        return new ResponseEntity<>(updatedShipmentDto, HttpStatus.OK);
    }

    // Delete a shipment by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShipment(@PathVariable Long id) {
        shipmentService.deleteShipment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
