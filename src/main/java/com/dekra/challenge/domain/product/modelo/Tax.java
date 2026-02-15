package com.dekra.challenge.domain.product.modelo;

import lombok.*;

import java.math.BigDecimal;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Tax {

    private Long id;
    private String name;
    private BigDecimal price;
    private String typeTax;
    private BigDecimal totalPrice;
    private BigDecimal tax;
}