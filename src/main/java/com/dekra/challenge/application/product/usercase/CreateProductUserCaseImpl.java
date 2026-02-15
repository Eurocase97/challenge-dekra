package com.dekra.challenge.application.product.usercase;

import com.dekra.challenge.domain.product.modelo.Product;
import com.dekra.challenge.domain.product.ports.out.ProductRepository;
import com.dekra.challenge.domain.product.ports.in.useCases.CreateProductUserCase;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class CreateProductUserCaseImpl implements CreateProductUserCase {

    private final ProductRepository productRepository;

    public CreateProductUserCaseImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public Product execute(Product product) {
        return productRepository.save(product);
    }
}
