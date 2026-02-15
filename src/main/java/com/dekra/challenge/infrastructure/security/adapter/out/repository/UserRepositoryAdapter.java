package com.dekra.challenge.infrastructure.security.adapter.out.repository;

import com.dekra.challenge.domain.security.modelo.User;
import com.dekra.challenge.domain.security.ports.out.UserRepository;
import com.dekra.challenge.infrastructure.product.mapper.UserMapper;
import com.dekra.challenge.infrastructure.security.adapter.out.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepository {

    private final UserJPA userRepository;
    private final UserMapper userMapper;

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email).map(userMapper::toDomain);
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public User save(User user) {
        UserEntity userEntity = userMapper.toEntity(user);
        UserEntity saved = userRepository.save(userEntity);
        return userMapper.toDomain(saved);
    }
}
