package com.example.demo.Mappers;

import com.example.demo.DTO.ProductDTO;
import com.example.demo.product.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(source = "name", target = "name")
    ProductDTO toDto(Product product);

    @Mapping(source = "name", target = "name")
    Product toEntity(ProductDTO productDTO);

    List<ProductDTO> toDtoList(List<Product> products);
}
