package com.dekra.challenge.infrastructure.product.adpater.in.controller;


import com.dekra.challenge.application.product.service.TaxCalculatorService;
import com.dekra.challenge.domain.product.modelo.Product;
import com.dekra.challenge.domain.product.modelo.Tax;
import com.dekra.challenge.domain.product.ports.in.useCases.*;
import com.dekra.challenge.infrastructure.product.adpater.in.dto.ProductResponse;

import com.dekra.challenge.infrastructure.product.mapper.ProductMapperImpl;
import com.dekra.challenge.infrastructure.product.mapper.TaxMapperImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;


@RestController
@RequestMapping("api/v1/product")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    private final ProductMapperImpl productMapper;
    private final CreateProductUserCase createProductUserCase;
    private final GetProductByIdUserCase getProductByIdUserCase;
    private final GetProductListUserCase getProductListUserCase;
    private final UpdateProductUserCase updateProductUserCase;
    private final RemoveProductUserCase removeProductUserCase;
    private final TaxCalculatorService taxCalculatorService;
    private final TaxMapperImpl taxMapper;
    private final DynamicSearchUseCase dynamicSearchUseCase;



    @GetMapping("/search")
    public ResponseEntity<?> dynamicSearch(  @RequestParam(required = false) Long id,
                                             @RequestParam(required = false) String name,
                                             @RequestParam(required = false) String description,
                                             @RequestParam(required = false) BigDecimal price){
        Product product = new Product(id, name, description, price);
        return ResponseEntity.ok(dynamicSearchUseCase.getProduct(product));
    }

    @PostMapping
    public ResponseEntity<ProductResponse> create(@RequestBody Product product) {
        Product created = createProductUserCase.execute(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productMapper.toProductResponse(created));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getById(@PathVariable Long id) {
         Product dbProduct  = getProductByIdUserCase.execute(id);
        return ResponseEntity.status(HttpStatus.OK).body(productMapper.toProductResponse(dbProduct));
    }

    @GetMapping
    public ResponseEntity<List<ProductResponse>> getAll() {
        List<Product> products = getProductListUserCase.execute();
        return ResponseEntity.ok(products.stream().map(productMapper :: toProductResponse).toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody Product product) {
        product.setId(id);
        Product updated = updateProductUserCase.execute(product);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        removeProductUserCase.execute(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/tax/{id}")
    public ResponseEntity<?> getTax(@PathVariable Long id) {
        Tax tax =  taxCalculatorService.execute(id);
        return ResponseEntity.status(HttpStatus.OK).body(taxMapper.toResponse(tax));
    }

}