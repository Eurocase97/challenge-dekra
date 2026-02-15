package com.dekra.challenge.infrastructure.product.adpater.out.persistence.repository;

import com.dekra.challenge.domain.product.modelo.Product;
import com.dekra.challenge.infrastructure.product.adpater.out.persistence.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductJPA extends JpaRepository<ProductEntity, Long> , JpaSpecificationExecutor<ProductEntity> {
}
