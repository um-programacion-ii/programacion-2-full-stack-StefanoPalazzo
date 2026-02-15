package org.example.hexagonalbasico.product.infrastructure.persistence.repository;

import lombok.RequiredArgsConstructor;
import org.example.hexagonalbasico.product.domain.model.Product;
import org.example.hexagonalbasico.product.domain.ports.out.ProductRepository;
import org.example.hexagonalbasico.product.infrastructure.persistence.entity.ProductEntity;
import org.example.hexagonalbasico.product.infrastructure.persistence.mapper.ProductMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class JpaProductRepositoryAdapter implements ProductRepository {

    private final JpaProductRepository jpaProductRepository;
    private final ProductMapper productMapper;

    @Override
    @Transactional
    public Product save(Product product) {
        ProductEntity entity = productMapper.toEntity(product);
        ProductEntity saved = jpaProductRepository.save(entity);
        return productMapper.toDomain(saved); // devuelve el producto con ID generado
    }

    @Override
    public Optional<Product> findById(Long id) {
        return jpaProductRepository.findById(id).map(productMapper::toDomain);
    }

    @Override
    public List<Product> findAll() {
        return jpaProductRepository.findAll()
                .stream()
                .map(productMapper::toDomain)
                .toList();
    }

    @Override
    @Transactional
    public Optional<Product> update(Long id, Product product) {
        return jpaProductRepository.findById(id)
                .map(existing -> {
                    existing.setDescription(product.getDescription());
                    existing.setPrice(product.getPrice());
                    ProductEntity updated = jpaProductRepository.save(existing);
                    return productMapper.toDomain(updated);
                });
    }

    @Override
    @Transactional
    public boolean deleteById(long id) {
        if (!jpaProductRepository.existsById(id)) return false;
        jpaProductRepository.deleteById(id);
        return true;
    }
}
