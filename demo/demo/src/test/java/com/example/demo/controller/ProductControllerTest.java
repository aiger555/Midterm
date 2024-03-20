package com.example.demo.controller;

import com.example.demo.product.Product;
import com.example.demo.product.ProductController;
import com.example.demo.product.ProductService;
import com.example.demo.Mappers.ProductMapper;
import com.example.demo.DTO.ProductDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;

@WebMvcTest(ProductController.class)
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ProductService productService;

    @Mock
    private ProductMapper productMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllProducts() throws Exception {
        // Mocking service response
        List<Product> products = Arrays.asList(new Product(), new Product());
        when(productService.getAllProducts()).thenReturn(products);

        // Mocking mapper response
        List<ProductDTO> productDTOs = Arrays.asList(new ProductDTO(), new ProductDTO());
        when(productMapper.toDtoList(products)).thenReturn(productDTOs);

        // Sending GET request
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").exists());
    }

    // Add more test methods for other endpoints...
}
