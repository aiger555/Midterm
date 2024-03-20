package com.example.demo.mapper;

import com.example.demo.DTO.ProductDTO;
import com.example.demo.Mappers.ProductMapper;
import com.example.demo.product.Product;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductMapperTest {

    private final ProductMapper productMapper = Mappers.getMapper(ProductMapper.class);

    @Test
    public void testToDto() {
        // Given
        Product product = new Product();
        product.setId(1L);
        product.setName("Test Product");

        // When
        ProductDTO productDTO = productMapper.toDto(product);

        // Then
        assertEquals(product.getId(), productDTO.getId());
        assertEquals(product.getName(), productDTO.getName());
    }

    @Test
    public void testToEntity() {
        // Given
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1L);
        productDTO.setName("Test Product");

        // When
        Product product = productMapper.toEntity(productDTO);

        // Then
        assertEquals(productDTO.getId(), product.getId());
        assertEquals(productDTO.getName(), product.getName());
    }

    @Test
    public void testToDtoList() {
        // Given
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Product 1");

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Product 2");

        List<Product> products = Arrays.asList(product1, product2);

        // When
        List<ProductDTO> productDTOs = productMapper.toDtoList(products);

        // Then
        assertEquals(products.size(), productDTOs.size());
        assertEquals(products.get(0).getId(), productDTOs.get(0).getId());
        assertEquals(products.get(1).getId(), productDTOs.get(1).getId());
        assertEquals(products.get(0).getName(), productDTOs.get(0).getName());
        assertEquals(products.get(1).getName(), productDTOs.get(1).getName());
    }
}
