package com.dekra.challenge.application.product.usercase;

import com.dekra.challenge.domain.product.modelo.Product;
import com.dekra.challenge.domain.product.ports.in.useCases.DynamicSearchUseCase;
import com.dekra.challenge.domain.product.ports.out.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DynamicSearchUseCaseImpl implements DynamicSearchUseCase {


    private final ProductRepository productRepository;

    public DynamicSearchUseCaseImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getProduct(Product product) {
        return productRepository.dynamicSearch(product);
    }
}
