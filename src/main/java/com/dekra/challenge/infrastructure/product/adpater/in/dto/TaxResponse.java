package com.dekra.challenge.infrastructure.product.adpater.in.dto;

import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaxResponse {
    private Long id;
    private String name;
    private BigDecimal price;
    private String typeTax;
    private BigDecimal totalPrice;
    private BigDecimal tax;
}
