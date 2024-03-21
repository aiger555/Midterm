package com.example.demo.controller;

import com.example.demo.DTO.ProductDTO;
import com.example.demo.Mappers.ProductMapper;
import com.example.demo.product.Product;
import com.example.demo.product.ProductService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@Validated
public class ProductController {

    @Autowired
    private ProductService productService;

    private ProductMapper productMapper;

    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable Long id) {
        try {
            Product product = productService.getProductById(id);
            if (product == null) {
                throw new EntityNotFoundException("Product not found with id: " + id);
            }
            ProductDTO productDto = productMapper.toDto(product);
            return new ResponseEntity<>(productDto, HttpStatus.OK);
        } catch (EntityNotFoundException ex) {
            return new ResponseEntity<>(new ProductDTO(), HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(new ProductDTO(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO productDto) {
        try {
            Product product = productMapper.toEntity(productDto);
            Product newProduct = productService.createProduct(product);
            ProductDTO newProductDto = productMapper.toDto(newProduct);
            return new ResponseEntity<>(newProductDto, HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>(new ProductDTO(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDto) {
        try {
            Product product = productMapper.toEntity(productDto);
            Product updatedProduct = productService.updateProduct(id, product);
            ProductDTO updatedProductDto = productMapper.toDto(updatedProduct);
            return new ResponseEntity<>(updatedProductDto, HttpStatus.OK);
        } catch (EntityNotFoundException ex) {
            return new ResponseEntity<>(productDto, HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(productDto, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        try {
            productService.deleteProduct(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (EntityNotFoundException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
