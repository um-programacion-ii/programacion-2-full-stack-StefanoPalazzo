package org.example.hexagonalbasico.product.application.service;

import lombok.RequiredArgsConstructor;
import org.example.hexagonalbasico.product.domain.model.Product;
import org.example.hexagonalbasico.product.domain.ports.in.*;
import org.example.hexagonalbasico.product.domain.ports.out.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService implements
        GetAllProductsUseCase,
        CreateProductUseCase,
        GetProductByIdUseCase,
        UpdateProductUseCase,
        DeleteProductUseCase {

    private final ProductRepository productRepository;

    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Optional<Product> updateProduct(Long id, Product product) {
        return productRepository.update(id, product);
    }

    @Override
    public boolean deleteProduct(Long id) {
        return productRepository.deleteById(id);
    }
}
