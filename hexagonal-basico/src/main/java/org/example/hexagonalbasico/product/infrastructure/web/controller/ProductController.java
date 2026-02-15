package org.example.hexagonalbasico.product.infrastructure.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.hexagonalbasico.product.domain.model.Product;
import org.example.hexagonalbasico.product.domain.ports.in.*;
import org.example.hexagonalbasico.product.infrastructure.web.DTO.ProductRequest;
import org.example.hexagonalbasico.product.infrastructure.web.DTO.ProductResponse;
import org.example.hexagonalbasico.product.infrastructure.web.mapper.ProductDtoMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final CreateProductUseCase createUseCase;
    private final GetAllProductsUseCase getAllUseCase;
    private final GetProductByIdUseCase getByIdUseCase;
    private final UpdateProductUseCase updateUseCase;
    private final DeleteProductUseCase deleteUseCase;
    private final ProductDtoMapper mapper;

    @PostMapping
    public ResponseEntity<ProductResponse> create(@Valid @RequestBody ProductRequest req) {
        Product created = createUseCase.create(mapper.toDomain(req));
        return new ResponseEntity<>(mapper.toResponse(created), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAll() {
        List<ProductResponse> list = getAllUseCase.getAllProducts()
                .stream().map(mapper::toResponse).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getById(@PathVariable Long id) {
        return getByIdUseCase.getProductById(id)
                .map(product -> ResponseEntity.ok(mapper.toResponse(product)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> update(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequest req) {
        Product toUpdate = mapper.toDomain(req);
        return updateUseCase.updateProduct(id, toUpdate)
                .map(updated -> ResponseEntity.ok(mapper.toResponse(updated)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        boolean deleted = deleteUseCase.deleteProduct(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}
