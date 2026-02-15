package com.dekra.challenge.application.product.calculator;

import com.dekra.challenge.domain.product.ports.in.useCases.TaxCalculatorUserCaser;

import java.math.BigDecimal;

public class IVATaxCalculatorUserCaser implements TaxCalculatorUserCaser {

    private static final BigDecimal IVA_RATE = new BigDecimal("0.21");

    @Override
    public BigDecimal calculateTax(BigDecimal price) {
        return price.multiply(IVA_RATE);
    }
}
