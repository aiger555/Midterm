package com.example.demo.repository;

import com.example.demo.order.Order;
import com.example.demo.shipment.Shipment;
import com.example.demo.shipment.ShipmentRepository;
import com.example.demo.shipment.ShipmentService;
import com.example.demo.shipment.ShipmentStatus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
public class ShipmentRepositoryTest {

    @MockBean
    private ShipmentRepository shipmentRepository;

    @InjectMocks
    private ShipmentService shipmentService;

    @Test
    public void testFindAllShipments() {
        // Given
        Order order1 = new Order();
        Order order2 = new Order();
        Shipment shipment1 = new Shipment(order1, ShipmentStatus.DELIVERED, "Shipping Address A", LocalDate.now(), null, null);
        Shipment shipment2 = new Shipment(order2, ShipmentStatus.PENDING, "Shipping Address B", LocalDate.now(), LocalDate.now().plusDays(2), null);
        List<Shipment> shipments = List.of(shipment1, shipment2);

        // When
        when(shipmentRepository.findAll()).thenReturn(shipments);
        List<Shipment> foundShipments = shipmentService.getAllShipments();

        // Then
        assertThat(foundShipments).isNotNull();
        assertThat(foundShipments.size()).isEqualTo(2);
        assertThat(foundShipments).contains(shipment1, shipment2);
    }

    @Test
    public void testFindShipmentById() {
        // Given
        Long shipmentId = 1L;
        Order order = new Order();
        Shipment shipment = new Shipment(order, ShipmentStatus.SHIPPED, "Shipping Address", LocalDate.now(), null, LocalDateTime.now());

        // When
        when(shipmentRepository.findById(shipmentId)).thenReturn(Optional.of(shipment));
        Optional<Shipment> foundShipment = Optional.ofNullable(shipmentService.getShipmentById(shipmentId));

        // Then
        assertThat(foundShipment).isPresent();
        assertThat(foundShipment.get()).isEqualTo(shipment);
    }

    @Test
    public void testSaveShipment() {
        // Given
        Order order = new Order();
        Shipment shipment = new Shipment(order, ShipmentStatus.DELIVERED,  "Mederova", LocalDate.now(), LocalDate.now(), LocalDateTime.now());

        // When
        when(shipmentRepository.save(shipment)).thenReturn(shipment);
        Shipment savedShipment = shipmentService.save(shipment);

        // Then
        assertThat(savedShipment).isNotNull();
        assertThat(savedShipment).isEqualTo(shipment);
    }

    @Test
    public void testDeleteShipment() {
        // Given
        Long shipmentId = 1L;
        shipmentService.deleteShipment(shipmentId);
        // Then
        assertThat(shipmentRepository.findById(shipmentId)).isEmpty();
    }
}
