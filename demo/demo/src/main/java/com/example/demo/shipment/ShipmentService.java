package com.example.demo.shipment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;


@Service
public class ShipmentService {

    @Autowired
    private ShipmentRepository shipmentRepository;

    // Get all shipments
    public List<Shipment> getAllShipments() {
        return shipmentRepository.findAll();
    }

    // Get shipment by ID
    public Shipment getShipmentById(Long id) {
        Optional<Shipment> optionalShipment = shipmentRepository.findById(id);
        return optionalShipment.orElseThrow(() -> new EntityNotFoundException("Shipment not found with id: " + id));
    }

    // Create a new shipment
    public Shipment createShipment(Shipment shipment) {
        return shipmentRepository.save(shipment);
    }

    // Update an existing shipment
    public Shipment updateShipment(Long id, Shipment newShipment) {
        Shipment existingShipment = getShipmentById(id);
        existingShipment.setOrder(newShipment.getOrder());
        existingShipment.setStatus(newShipment.getStatus());
        existingShipment.setShippingAddress(newShipment.getShippingAddress());
        existingShipment.setEstimatedDeliveryDate(newShipment.getEstimatedDeliveryDate());
        existingShipment.setActualDeliveryDate(newShipment.getActualDeliveryDate());
        return shipmentRepository.save(existingShipment);
    }

    // Delete a shipment by ID
    public void deleteShipment(Long id) {
        shipmentRepository.deleteById(id);
    }
}
