package com.example.demo.shipment;

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

    // Get all shipments
    @GetMapping
    public ResponseEntity<List<Shipment>> getAllShipments() {
        List<Shipment> shipments = shipmentService.getAllShipments();
        return new ResponseEntity<>(shipments, HttpStatus.OK);
    }

    // Get shipment by ID
    @GetMapping("/{id}")
    public ResponseEntity<Shipment> getShipmentById(@PathVariable Long id) {
        Shipment shipment = shipmentService.getShipmentById(id);
        return new ResponseEntity<>(shipment, HttpStatus.OK);
    }

    // Create a new shipment
    @PostMapping
    public ResponseEntity<Shipment> createShipment(@RequestBody Shipment shipment) {
        Shipment newShipment = shipmentService.createShipment(shipment);
        return new ResponseEntity<>(newShipment, HttpStatus.CREATED);
    }

    // Update an existing shipment
    @PutMapping("/{id}")
    public ResponseEntity<Shipment> updateShipment(@PathVariable Long id, @RequestBody Shipment shipment) {
        Shipment updatedShipment = shipmentService.updateShipment(id, shipment);
        return new ResponseEntity<>(updatedShipment, HttpStatus.OK);
    }

    // Delete a shipment by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShipment(@PathVariable Long id) {
        shipmentService.deleteShipment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

