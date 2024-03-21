package com.example.demo.Mappers;

import com.example.demo.DTO.OrderDTO;
import com.example.demo.client.Client;
import com.example.demo.order.Order;
import com.example.demo.order.OrderStatus;
import com.example.demo.product.Product;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-21T23:23:36+0600",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 18.0.1.1 (Oracle Corporation)"
)
public class OrderMapperImpl implements OrderMapper {

    @Override
    public OrderDTO orderToOrderDTO(Order order) {
        if ( order == null ) {
            return null;
        }

        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setClientId( orderClientId( order ) );
        orderDTO.setProductId( orderProductId( order ) );
        orderDTO.setId( order.getId() );
        if ( order.getQuantity() != null ) {
            orderDTO.setQuantity( order.getQuantity() );
        }
        if ( order.getStatus() != null ) {
            orderDTO.setStatus( order.getStatus().name() );
        }

        return orderDTO;
    }

    @Override
    public Order toEntity(OrderDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Order order = new Order();

        order.setId( dto.getId() );
        order.setQuantity( dto.getQuantity() );
        if ( dto.getStatus() != null ) {
            order.setStatus( Enum.valueOf( OrderStatus.class, dto.getStatus() ) );
        }

        return order;
    }

    @Override
    public OrderDTO toDto(Order entity) {
        if ( entity == null ) {
            return null;
        }

        OrderDTO orderDTO = new OrderDTO();

        orderDTO.setId( entity.getId() );
        if ( entity.getQuantity() != null ) {
            orderDTO.setQuantity( entity.getQuantity() );
        }
        if ( entity.getStatus() != null ) {
            orderDTO.setStatus( entity.getStatus().name() );
        }

        return orderDTO;
    }

    @Override
    public List<OrderDTO> toDtoList(List<Order> entities) {
        if ( entities == null ) {
            return null;
        }

        List<OrderDTO> list = new ArrayList<OrderDTO>( entities.size() );
        for ( Order order : entities ) {
            list.add( toDto( order ) );
        }

        return list;
    }

    @Override
    public Order orderDTOToOrder(OrderDTO orderDTO) {
        if ( orderDTO == null ) {
            return null;
        }

        Order order = new Order();

        order.setClient( orderDTOToClient( orderDTO ) );
        order.setProduct( orderDTOToProduct( orderDTO ) );
        order.setId( orderDTO.getId() );
        order.setQuantity( orderDTO.getQuantity() );
        if ( orderDTO.getStatus() != null ) {
            order.setStatus( Enum.valueOf( OrderStatus.class, orderDTO.getStatus() ) );
        }

        return order;
    }

    private Long orderClientId(Order order) {
        if ( order == null ) {
            return null;
        }
        Client client = order.getClient();
        if ( client == null ) {
            return null;
        }
        Long id = client.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long orderProductId(Order order) {
        if ( order == null ) {
            return null;
        }
        Product product = order.getProduct();
        if ( product == null ) {
            return null;
        }
        Long id = product.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected Client orderDTOToClient(OrderDTO orderDTO) {
        if ( orderDTO == null ) {
            return null;
        }

        Client client = new Client();

        client.setId( orderDTO.getClientId() );

        return client;
    }

    protected Product orderDTOToProduct(OrderDTO orderDTO) {
        if ( orderDTO == null ) {
            return null;
        }

        Product product = new Product();

        product.setId( orderDTO.getProductId() );

        return product;
    }
}
