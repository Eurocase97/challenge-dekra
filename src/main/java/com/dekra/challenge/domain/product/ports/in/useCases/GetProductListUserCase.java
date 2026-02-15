package com.dekra.challenge.domain.product.ports.in.useCases;

import com.dekra.challenge.domain.product.modelo.Product;

import java.util.List;

public interface GetProductListUserCase {
   List<Product> execute();
}
