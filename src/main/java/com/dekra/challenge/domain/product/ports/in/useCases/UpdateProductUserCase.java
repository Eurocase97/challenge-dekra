package com.dekra.challenge.domain.product.ports.in.useCases;

import com.dekra.challenge.domain.product.modelo.Product;

public interface UpdateProductUserCase {
    Product execute(Product UpdateProduct);
}
