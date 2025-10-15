package org.example.hexagonalbasico.product.domain.ports.in;

import org.example.hexagonalbasico.product.domain.model.Product;

import java.util.List;

public interface GetAllProductsUseCase {
    List<Product> getAllProducts();
}
