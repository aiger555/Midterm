package com.example.demo.Mappers;

import com.example.demo.DTO.OrderDTO;
import com.example.demo.order.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    @Mapping(source = "client.id", target = "clientId")
    @Mapping(source = "product.id", target = "productId")
    @Named("orderToOrderDTOWithCustomMapping")
    OrderDTO orderToOrderDTO(Order order);
    Order toEntity(OrderDTO dto);

    OrderDTO toDto(Order entity);

    List<OrderDTO> toDtoList(List<Order> entities);


    @Named("orderDTOToOrderWithCustomMapping")
    @Mapping(source = "clientId", target = "client.id")
    @Mapping(source = "productId", target = "product.id")
    Order orderDTOToOrder (OrderDTO orderDTO);
}
