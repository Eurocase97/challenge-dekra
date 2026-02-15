package com.dekra.challenge.infrastructure.product.mapper;

import com.dekra.challenge.domain.security.modelo.User;
import com.dekra.challenge.infrastructure.security.adapter.in.controller.dto.RegisterRequest;
import com.dekra.challenge.infrastructure.security.adapter.out.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "authorities", ignore = true)
    UserEntity toEntity(User user);

    User toDomain(UserEntity userEntity);

    @Mapping(target = "role", ignore = true)
    @Mapping(target = "id", ignore = true)
    User toDomain(RegisterRequest registerRequest);
}
