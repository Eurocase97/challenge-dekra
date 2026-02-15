package com.dekra.challenge.application.product.service;

import com.dekra.challenge.application.product.usercase.TaxCalculatorUserCaserImpl;
import com.dekra.challenge.domain.product.exception.ProductNotFoundException;
import com.dekra.challenge.domain.product.modelo.Product;
import com.dekra.challenge.domain.product.modelo.Tax;
import com.dekra.challenge.domain.product.ports.out.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TaxCalculatorServiceTest {
    @Mock
    private TaxCalculatorUserCaserImpl taxCalculator;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private TaxCalculatorService taxCalculatorService;

    private Product product;
    private final Long productId = 1L;
    private final String taxType = "IVA";

    @BeforeEach
    void setUp() {

        product = new Product();
        product.setId(productId);
        product.setName("Test Product");
        product.setPrice(new BigDecimal("100.00"));

        ReflectionTestUtils.setField(taxCalculatorService, "taxType", taxType);
    }

    @Test
    void ShouldReturnTaxWithCorrectCalculations() {
        BigDecimal taxAmount = new BigDecimal("21.00");
        when(productRepository.getById(productId)).thenReturn(Optional.of(product));
        when(taxCalculator.calculateTax(product.getPrice())).thenReturn(taxAmount);

        Tax result = taxCalculatorService.execute(productId);

        assertNotNull(result);
        assertEquals(productId, result.getId());
        assertEquals("Test Product", result.getName());
        assertEquals(new BigDecimal("100.00"), result.getPrice());
        assertEquals(new BigDecimal("121.00"), result.getTotalPrice());
        assertEquals(taxAmount, result.getTax());
        assertEquals(taxType, result.getTypeTax());
    }

    @Test
    void ShouldThrowProductNotFoundException() {
        when(productRepository.getById(productId)).thenReturn(Optional.empty());

        ProductNotFoundException exception = assertThrows(
                ProductNotFoundException.class,
                () -> taxCalculatorService.execute(productId)
        );
        assertEquals("Id not valid", exception.getMessage());
    }

}
