package com.dekra.challenge.domain.security.ports.in.usercase;

import com.dekra.challenge.domain.security.modelo.User;

public interface RegisterUserUseCase {

    String register(User user);
}
