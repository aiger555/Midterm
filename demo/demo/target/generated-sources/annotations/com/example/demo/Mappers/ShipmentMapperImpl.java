package com.example.demo.Mappers;

import com.example.demo.DTO.ShipmentDTO;
import com.example.demo.shipment.Shipment;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-21T23:33:04+0600",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 18.0.1.1 (Oracle Corporation)"
)
public class ShipmentMapperImpl implements ShipmentMapper {

    @Override
    public Shipment toEntity(ShipmentDTO shipmentDto) {
        if ( shipmentDto == null ) {
            return null;
        }

        Shipment shipment = new Shipment();

        shipment.setId( shipmentDto.getId() );

        return shipment;
    }

    @Override
    public ShipmentDTO toDto(Shipment shipment) {
        if ( shipment == null ) {
            return null;
        }

        ShipmentDTO shipmentDTO = new ShipmentDTO();

        shipmentDTO.setId( shipment.getId() );

        return shipmentDTO;
    }

    @Override
    public List<ShipmentDTO> toDtoList(List<Shipment> shipments) {
        if ( shipments == null ) {
            return null;
        }

        List<ShipmentDTO> list = new ArrayList<ShipmentDTO>( shipments.size() );
        for ( Shipment shipment : shipments ) {
            list.add( toDto( shipment ) );
        }

        return list;
    }
}
