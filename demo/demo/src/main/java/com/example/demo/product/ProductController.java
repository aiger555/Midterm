package com.example.demo.product;

import com.example.demo.DTO.ProductDTO;
import com.example.demo.Mappers.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    private ProductMapper productMapper;

    // Get all products
    @GetMapping
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        // Convert Product entities to ProductDto objects using the mapper
        List<ProductDTO> productDtos = productMapper.toDtoList(products);
        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }

    // Get product by ID
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        Product product = productService.getProductById(id);
        // Convert Product entity to ProductDto object using the mapper
        ProductDTO productDto = productMapper.toDto(product);
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    // Create a new product
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDto) {
        // Convert ProductDto object to Product entity using the mapper
        Product product = productMapper.toEntity(productDto);
        Product newProduct = productService.createProduct(product);
        // Convert created Product entity to ProductDto object using the mapper
        ProductDTO newProductDto = productMapper.toDto(newProduct);
        return new ResponseEntity<>(newProductDto, HttpStatus.CREATED);
    }

    // Update an existing product
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDto) {
        // Convert ProductDto object to Product entity using the mapper
        Product product = productMapper.toEntity(productDto);
        Product updatedProduct = productService.updateProduct(id, product);
        // Convert updated Product entity to ProductDto object using the mapper
        ProductDTO updatedProductDto = productMapper.toDto(updatedProduct);
        return new ResponseEntity<>(updatedProductDto, HttpStatus.OK);
    }

    // Delete a product by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
