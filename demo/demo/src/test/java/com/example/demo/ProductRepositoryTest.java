package com.example.demo;
import com.example.demo.product.Product;
import com.example.demo.product.ProductRepository;
import com.example.demo.product.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@DataJpaTest
@ExtendWith(MockitoExtension.class)
public class ProductRepositoryTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    public void testFindAllProducts() {
        // Given
        LocalDateTime createdAt1 = LocalDateTime.of(2024, 3, 25, 9, 22); // LocalDateTime for Product1
        LocalDateTime createdAt2 = LocalDateTime.of(2022, 3, 19, 2, 23); // LocalDateTime for Product1

        Product product1 = new Product("Product1", "Description 1", BigDecimal.valueOf(100), 10, createdAt1);

        Product product2 = new Product("Product 2", "Description 2", BigDecimal.valueOf(200), 20, createdAt2);
        List<Product> products = List.of(product1, product2);

        // When
        when(productRepository.findAll()).thenReturn(products);
        List<Product> foundProducts = productService.getAllProducts();

        // Then
        assertThat(foundProducts).isNotNull();
        assertThat(foundProducts.size()).isEqualTo(2);
        assertThat(foundProducts).contains(product1, product2);
    }

    @Test
    public void testFindProductById() {
        // Given
        Long productId = 1L;
        BigDecimal price = new BigDecimal("100.00");
        LocalDateTime createdAt = LocalDateTime.of(2024, 3, 24, 0, 0); // Assuming midnight

        Product product = new Product("product 3", "Description 3", price, 10, createdAt);

        // When
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));
        Optional<Product> foundProduct = Optional.ofNullable(productService.getProductById(productId));

        // Then
        assertThat(foundProduct).isPresent();
        assertThat(foundProduct.get()).isEqualTo(product);
    }

    @Test
    public void testSaveProduct() {
        // Given
        LocalDateTime createdAt1 = LocalDateTime.of(2024, 3, 25, 9, 22); // LocalDateTime for Product1
        Product product = new Product("Product 1", "Description 1", BigDecimal.valueOf(100), 10, createdAt1);

        // When
        when(productRepository.save(product)).thenReturn(product);
        Product savedProduct = productService.save(product);

        // Then
        assertThat(savedProduct).isNotNull();
        assertThat(savedProduct).isEqualTo(product);
    }

    @Test
    public void testDeleteProduct() {
        // Given
        Long productId = 1L;

        // When
        productService.deleteProduct(productId);

        // No need to verify anything in this case
    }


}

