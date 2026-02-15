package com.dekra.challenge.application.product.usercase;

import com.dekra.challenge.domain.product.exception.ProductNotFoundException;
import com.dekra.challenge.domain.product.modelo.Product;
import com.dekra.challenge.domain.product.ports.in.useCases.UpdateProductUserCase;
import com.dekra.challenge.domain.product.ports.out.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UpdateProductUserCaseImpl implements UpdateProductUserCase {

    private final ProductRepository productRepository;

    public UpdateProductUserCaseImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public Product execute(Product updateProduct) {

        if(updateProduct.getId() == null){
            throw new ProductNotFoundException("Id not valid");
        }

        Product dbProduct = productRepository.getById(updateProduct.getId())
                .orElseThrow(() -> new ProductNotFoundException("Id not valid"));


        if(updateProduct.getName() == null){
            updateProduct.setName(dbProduct.getName());
        }

        if(updateProduct.getDescription() == null){
            updateProduct.setDescription(dbProduct.getDescription());
        }

        if(updateProduct.getPrice() == null){
            updateProduct.setPrice(dbProduct.getPrice());
        }

       return productRepository.save(updateProduct);
    }
}
