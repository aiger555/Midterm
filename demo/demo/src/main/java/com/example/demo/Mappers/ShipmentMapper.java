package com.example.demo.Mappers;

import com.example.demo.DTO.ShipmentDTO;
import com.example.demo.shipment.Shipment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ShipmentMapper {

    ShipmentMapper INSTANCE = Mappers.getMapper(ShipmentMapper.class);

    @Mapping(target = "id", ignore = true)
    Shipment toEntity(ShipmentDTO shipmentDto);

    ShipmentDTO toDto(Shipment shipment);
}

