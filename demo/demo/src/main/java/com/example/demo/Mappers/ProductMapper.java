package com.example.demo.Mappers;

import com.example.demo.DTO.ProductDTO;
import com.example.demo.product.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping(target = "id", ignore = true)
    Product toEntity(ProductDTO productDto);

    ProductDTO toDto(Product product);
}
