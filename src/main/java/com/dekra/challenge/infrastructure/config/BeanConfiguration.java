package com.dekra.challenge.infrastructure.config;

import com.dekra.challenge.application.product.calculator.TaxCalculatorFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public TaxCalculatorFactory taxCalculatorFactory(@Value("${tax.calculator.type}") String calculatorType) {
        return new TaxCalculatorFactory(calculatorType);
    }
}
