package com.dekra.challenge.application.product.usercase;

import com.dekra.challenge.domain.product.exception.ProductNotFoundException;
import com.dekra.challenge.domain.product.modelo.Product;
import com.dekra.challenge.domain.product.ports.in.useCases.GetProductByIdUserCase;
import com.dekra.challenge.domain.product.ports.out.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class GetProductByIdUserCaseImpl implements GetProductByIdUserCase {

    private final ProductRepository productRepository;

    public GetProductByIdUserCaseImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public Product execute(Long id) {
        return productRepository.getById(id).orElseThrow(()-> new ProductNotFoundException("Not id valid"));
    }
}
