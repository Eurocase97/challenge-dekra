package com.dekra.challenge.application.product.calculator;


import com.dekra.challenge.domain.product.ports.in.useCases.TaxCalculatorUserCaser;


public class TaxCalculatorFactory {

    private final String calculatorType;

    public TaxCalculatorFactory(String calculatorType) {
        this.calculatorType = calculatorType;
    }

    public TaxCalculatorUserCaser getTaxCalculator() {
        return switch (calculatorType.toUpperCase()) {
            case "IVA" -> new IVATaxCalculatorUserCaser();
            case "ITBIS" -> new ITBISTaxCalculatorUserCaser();
            default -> throw new IllegalArgumentException(
                    "Invalid tax calculator type: " + calculatorType);
        };
    }
}