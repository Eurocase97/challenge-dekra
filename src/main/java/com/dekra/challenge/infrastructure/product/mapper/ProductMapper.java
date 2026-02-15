package com.dekra.challenge.infrastructure.product.mapper;

import com.dekra.challenge.domain.product.modelo.Product;
import com.dekra.challenge.infrastructure.product.adpater.in.dto.ProductResponse;
import com.dekra.challenge.infrastructure.product.adpater.out.persistence.entity.ProductEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    Product toDomain(ProductEntity productEntity);
    ProductEntity toEntity(Product domain);
    ProductResponse toProductResponse(Product domain);
    Product toDomain(ProductResponse productResponse);
}
