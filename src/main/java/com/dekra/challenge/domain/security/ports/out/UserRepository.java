package com.dekra.challenge.domain.security.ports.out;

import com.dekra.challenge.domain.security.modelo.User;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);

    User save(User user);
}
