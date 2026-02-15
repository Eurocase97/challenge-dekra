package com.dekra.challenge.infrastructure.product.adpater.out.persistence.repository;

import com.dekra.challenge.infrastructure.product.adpater.out.persistence.entity.ProductEntity;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class ProductSpecifications {

    public Specification<ProductEntity> filter(ProductEntity filter) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (filter.getId() != null) {
                predicates.add(
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("id")),
                                "%" + filter.getId().toString().toLowerCase() + "%")
                );
            }

            if (filter.getName() != null) {
                predicates.add(
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),
                                "%" + filter.getName().toLowerCase() + "%")
                );
            }

            if (filter.getDescription() != null) {
                predicates.add(
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("description")),
                                "%" + filter.getDescription().toLowerCase() + "%")
                );
            }

            if (filter.getPrice() != null) {
                predicates.add(
                        criteriaBuilder.lessThanOrEqualTo(root.get("price"), filter.getPrice())
                );
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

}
