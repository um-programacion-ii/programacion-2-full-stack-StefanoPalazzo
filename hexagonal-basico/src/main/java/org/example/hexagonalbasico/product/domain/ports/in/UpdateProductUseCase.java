package org.example.hexagonalbasico.product.domain.ports.in;

import org.example.hexagonalbasico.product.domain.model.Product;
import java.util.Optional;

public interface UpdateProductUseCase {
    Optional<Product> updateProduct(Long id, Product product);
}