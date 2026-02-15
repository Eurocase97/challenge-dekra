package com.dekra.challenge.infrastructure.security.adapter.out.repository;

import com.dekra.challenge.infrastructure.security.adapter.out.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserJPA extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
}
