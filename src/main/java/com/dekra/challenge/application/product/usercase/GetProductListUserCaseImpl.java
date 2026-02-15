package com.dekra.challenge.application.product.usercase;

import com.dekra.challenge.domain.product.modelo.Product;
import com.dekra.challenge.domain.product.ports.in.useCases.GetProductListUserCase;
import com.dekra.challenge.domain.product.ports.out.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetProductListUserCaseImpl implements GetProductListUserCase {
    private final ProductRepository productRepository;

    public GetProductListUserCaseImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public List<Product> execute() {
        return productRepository.getProducts();
    }
}
