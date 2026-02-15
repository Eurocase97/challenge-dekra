package com.dekra.challenge.application.product.usercase;

import com.dekra.challenge.domain.product.exception.ProductNotFoundException;
import com.dekra.challenge.domain.product.ports.in.useCases.RemoveProductUserCase;
import com.dekra.challenge.domain.product.ports.out.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class RemoveProductUserCaseImpl implements RemoveProductUserCase {

    private final ProductRepository productRepository;

    public RemoveProductUserCaseImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }


    @Override
    public void execute(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("Id not valid");
        }
        productRepository.deleteById(id);
    }
}
