package com.dekra.challenge.infrastructure.product.mapper;

import com.dekra.challenge.domain.product.modelo.Tax;
import com.dekra.challenge.infrastructure.product.adpater.in.dto.TaxResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaxMapper {
    Tax toDomain(TaxResponse taxResponse);
    TaxResponse toResponse(Tax tax);
}
