package org.example.hexagonalbasico.product.infrastructure.web.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ProductResponse {
    private Long id;
    private String description;
    private BigDecimal price;
}
