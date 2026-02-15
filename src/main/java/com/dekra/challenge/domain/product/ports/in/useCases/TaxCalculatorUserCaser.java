package com.dekra.challenge.domain.product.ports.in.useCases;

import java.math.BigDecimal;

@FunctionalInterface
public interface TaxCalculatorUserCaser {
    BigDecimal calculateTax(BigDecimal price);
}
