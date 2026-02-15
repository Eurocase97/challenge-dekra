package com.dekra.challenge.infrastructure.product.adpater.out.persistence.repository;

import com.dekra.challenge.domain.product.modelo.Product;
import com.dekra.challenge.domain.product.ports.out.ProductRepository;
import com.dekra.challenge.infrastructure.product.adpater.out.persistence.entity.ProductEntity;
import com.dekra.challenge.infrastructure.product.mapper.ProductMapperImpl;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepositoryAdapter implements ProductRepository {

    private final ProductJPA productJPA;
    private final ProductMapperImpl productMapper;
    private final ProductSpecifications productSpecifications;

    public ProductRepositoryAdapter(ProductJPA productJPA, ProductMapperImpl productMapper, ProductSpecifications productSpecifications) {
        this.productJPA = productJPA;
        this.productMapper = productMapper;
        this.productSpecifications = productSpecifications;
    }

    @Override
    public Product save(Product product) {
        ProductEntity productEntity = productMapper.toEntity(product);
        ProductEntity response = productJPA.save(productEntity);
        return productMapper.toDomain(response);
    }

    @Override
    public Optional<Product> getById(Long id) {
        Optional<ProductEntity> response = productJPA.findById(id);
        return response.map(productMapper::toDomain);
    }

    @Override
    public List<Product> getProducts() {
        return productJPA.findAll().stream().map(productMapper::toDomain).toList();
    }

    @Override
    public boolean existsById(Long id) {
        return productJPA.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
         productJPA.deleteById(id);
    }

    @Override
    public List<Product> dynamicSearch(Product product) {
        ProductEntity productEntity = productMapper.toEntity(product);
        Specification<ProductEntity> productEntitySpecification  = productSpecifications.filter(productEntity);
        return productJPA.findAll(productEntitySpecification).stream().map(productMapper::toDomain).toList();
    }
}
