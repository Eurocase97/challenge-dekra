package com.dekra.challenge.application.product.calculator;

import com.dekra.challenge.domain.product.ports.in.useCases.TaxCalculatorUserCaser;

import java.math.BigDecimal;

public class ITBISTaxCalculatorUserCaser implements TaxCalculatorUserCaser {

    private static final BigDecimal ITBIS_RATE = new BigDecimal("0.18");

    @Override
    public BigDecimal calculateTax(BigDecimal price) {
        return price.multiply(ITBIS_RATE);
    }
}