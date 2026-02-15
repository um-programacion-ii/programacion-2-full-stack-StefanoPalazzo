package org.example.hexagonalbasico.product.domain.ports.in;

import org.example.hexagonalbasico.product.domain.model.Product;

public interface CreateProductUseCase {
    Product create(Product product);
}
