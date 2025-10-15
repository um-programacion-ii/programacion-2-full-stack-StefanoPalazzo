package org.example.hexagonalbasico.product.infrastructure.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.hexagonalbasico.product.domain.model.Product;
import org.example.hexagonalbasico.product.domain.ports.in.CreateProductUseCase;
import org.example.hexagonalbasico.product.domain.ports.in.GetAllProductsUseCase;
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

    private final CreateProductUseCase createProductUseCase;
    private final ProductDtoMapper productDtoMapper;
    private final GetAllProductsUseCase getAllProductsUseCase;


    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Hello World");
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductRequest productRequest) {
        Product product = productDtoMapper.toDomain(productRequest);
        Product createdProduct = createProductUseCase.create(product);
        return new ResponseEntity<>(productDtoMapper.toResponse(createdProduct), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAllProducts() {
        List<ProductResponse> productResponses = getAllProductsUseCase.getAllProducts()
                .stream()
                .map(productDtoMapper::toResponse)
                .toList();
        return new ResponseEntity<>(productResponses, HttpStatus.OK);
    }
}
