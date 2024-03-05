package com.workintech.eCommerceBackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
public class ProductDto {
    private String name;
    private String description;
    private BigDecimal price;
}
