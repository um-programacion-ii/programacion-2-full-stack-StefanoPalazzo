package org.example.hexagonalbasico.product.infrastructure.web.mapper;

import org.example.hexagonalbasico.product.domain.model.Product;
import org.example.hexagonalbasico.product.infrastructure.web.DTO.ProductRequest;
import org.example.hexagonalbasico.product.infrastructure.web.DTO.ProductResponse;
import org.springframework.stereotype.Component;

@Component
public class ProductDtoMapper {
    public Product toDomain(ProductRequest productRequest) {
        if (productRequest == null) {
            return null;
        }

        return new Product(null, productRequest.getDescription(), productRequest.getPrice());
    }

    public ProductResponse toResponse(Product product) {
        if (product == null) {
            return null;
        }

        return new ProductResponse(product.getId(), product.getDescription(), product.getPrice());
    }}
