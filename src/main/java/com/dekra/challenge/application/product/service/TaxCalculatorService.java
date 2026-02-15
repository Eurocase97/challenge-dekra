package com.dekra.challenge.application.product.service;

import com.dekra.challenge.application.product.usercase.TaxCalculatorUserCaserImpl;
import com.dekra.challenge.domain.product.exception.ProductNotFoundException;
import com.dekra.challenge.domain.product.modelo.Product;
import com.dekra.challenge.domain.product.modelo.Tax;
import com.dekra.challenge.domain.product.ports.out.ProductRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TaxCalculatorService {

    private final TaxCalculatorUserCaserImpl taxCalculator;
    private final ProductRepository productRepository;

    @Value("${tax.calculator.type}")
    private String taxType;

    public TaxCalculatorService(TaxCalculatorUserCaserImpl taxCalculator, ProductRepository productRepository) {
        this.taxCalculator = taxCalculator;
        this.productRepository = productRepository;
    }

    public Tax execute(Long id){
        Product product = productRepository.getById(id)
                .orElseThrow(() -> new ProductNotFoundException("Id not valid"));

        BigDecimal priceTaxed = taxCalculator.calculateTax(product.getPrice());
        return createTax(product, priceTaxed);
    }

    private Tax createTax(Product product, BigDecimal priceTaxed){
        Tax tax = new Tax();
        tax.setId(product.getId());
        tax.setName(product.getName());
        tax.setPrice(product.getPrice());
        tax.setTotalPrice(priceTaxed.add(product.getPrice()));
        tax.setTypeTax(taxType);
        tax.setTax(priceTaxed);
        return tax;
    }
}
