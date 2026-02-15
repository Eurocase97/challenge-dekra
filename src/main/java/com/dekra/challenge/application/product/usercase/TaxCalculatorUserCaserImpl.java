package com.dekra.challenge.application.product.usercase;

import com.dekra.challenge.application.product.calculator.TaxCalculatorFactory;
import com.dekra.challenge.domain.product.ports.in.useCases.TaxCalculatorUserCaser;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TaxCalculatorUserCaserImpl implements TaxCalculatorUserCaser {

    private final TaxCalculatorFactory taxCalculatorFactory;

    public TaxCalculatorUserCaserImpl(TaxCalculatorFactory taxCalculatorFactory) {
        this.taxCalculatorFactory = taxCalculatorFactory;
    }


    @Override
    public BigDecimal calculateTax(BigDecimal price) {
        TaxCalculatorUserCaser taxCalculatorUserCaser = taxCalculatorFactory.getTaxCalculator();
        return taxCalculatorUserCaser.calculateTax(price) ;
    }
}
