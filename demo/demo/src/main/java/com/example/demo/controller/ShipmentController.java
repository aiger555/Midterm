package com.example.demo.controller;

import com.example.demo.DTO.ShipmentDTO;
import com.example.demo.Mappers.ShipmentMapper;
import com.example.demo.shipment.Shipment;
import com.example.demo.shipment.ShipmentService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/shipments")
@Validated
public class ShipmentController {

    @Autowired
    private ShipmentService shipmentService;

    private ShipmentMapper shipmentMapper;

    @GetMapping("/{id}")
    public ResponseEntity<ShipmentDTO> getShipmentById(@PathVariable Long id) {
        try {
            Shipment shipment = shipmentService.getShipmentById(id);
            if (shipment == null) {
                throw new EntityNotFoundException("Shipment not found with id: " + id);
            }
            ShipmentDTO shipmentDto = shipmentMapper.toDto(shipment);
            return new ResponseEntity<>(shipmentDto, HttpStatus.OK);
        } catch (EntityNotFoundException ex) {
            return new ResponseEntity<>(new ShipmentDTO(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(new ShipmentDTO(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<ShipmentDTO> createShipment(@Valid @RequestBody ShipmentDTO shipmentDto) {
        try {
            Shipment shipment = shipmentMapper.toEntity(shipmentDto);
            Shipment newShipment = shipmentService.createShipment(shipment);
            ShipmentDTO newShipmentDto = shipmentMapper.toDto(newShipment);
            return new ResponseEntity<>(newShipmentDto, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(shipmentDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShipmentDTO> updateShipment(@PathVariable Long id, @RequestBody ShipmentDTO shipmentDto) {
        try {
            Shipment shipment = shipmentMapper.toEntity(shipmentDto);
            Shipment updatedShipment = shipmentService.updateShipment(id, shipment);
            ShipmentDTO updatedShipmentDto = shipmentMapper.toDto(updatedShipment);
            return new ResponseEntity<>(updatedShipmentDto, HttpStatus.OK);
        } catch (EntityNotFoundException ex) {
            return new ResponseEntity<>(shipmentDto, HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(shipmentDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShipment(@PathVariable Long id) {
        try {
            shipmentService.deleteShipment(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
