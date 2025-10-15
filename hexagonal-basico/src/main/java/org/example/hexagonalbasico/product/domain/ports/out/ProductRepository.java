package org.example.hexagonalbasico.product.domain.ports.out;

import org.example.hexagonalbasico.product.domain.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface ProductRepository {
    Product save(Product product);
    Optional<Product> findById(Long id);

    Optional<Product> findById(long id);

    List<Product> findAll();
    Optional<Product> update(Long id, Product product);
    boolean deleteById(long id);}
