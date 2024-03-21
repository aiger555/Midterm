package com.example.demo.Mappers;

import com.example.demo.DTO.ProductDTO;
import com.example.demo.product.Product;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-21T23:28:00+0600",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 18.0.1.1 (Oracle Corporation)"
)
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductDTO toDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDTO productDTO = new ProductDTO();

        productDTO.setName( product.getName() );
        productDTO.setId( product.getId() );
        productDTO.setPrice( product.getPrice() );
        if ( product.getQuantity() != null ) {
            productDTO.setQuantity( product.getQuantity() );
        }

        return productDTO;
    }

    @Override
    public Product toEntity(ProductDTO productDTO) {
        if ( productDTO == null ) {
            return null;
        }

        Product product = new Product();

        product.setName( productDTO.getName() );
        product.setId( productDTO.getId() );
        product.setPrice( productDTO.getPrice() );
        product.setQuantity( productDTO.getQuantity() );

        return product;
    }

    @Override
    public List<ProductDTO> toDtoList(List<Product> products) {
        if ( products == null ) {
            return null;
        }

        List<ProductDTO> list = new ArrayList<ProductDTO>( products.size() );
        for ( Product product : products ) {
            list.add( toDto( product ) );
        }

        return list;
    }
}
