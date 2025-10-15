package org.example.hexagonalbasico.product.application.service;

import lombok.RequiredArgsConstructor;
import org.example.hexagonalbasico.product.domain.model.Product;
import org.example.hexagonalbasico.product.domain.ports.in.CreateProductUseCase;
import org.example.hexagonalbasico.product.domain.ports.in.GetAllProductsUseCase;
import org.example.hexagonalbasico.product.domain.ports.out.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService implements GetAllProductsUseCase, CreateProductUseCase {
    private final ProductRepository productRepository; // alternativa a @AutoWired

    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
