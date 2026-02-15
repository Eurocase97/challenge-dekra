package com.dekra.challenge.domain.product.ports.out;

import com.dekra.challenge.domain.product.modelo.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    Product save(Product product);
    Optional<Product> getById(Long id);
    List<Product> getProducts();
    boolean existsById(Long id);
    void deleteById(Long id);
    List<Product>  dynamicSearch(Product product);
}