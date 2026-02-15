package org.example.hexagonalbasico.product.domain.ports.in;

import org.example.hexagonalbasico.product.domain.model.Product;
import java.util.Optional;

public interface GetProductByIdUseCase {
    Optional<Product> getProductById(Long id);
}
