package com.dekra.challenge.domain.security.ports.in.usercase;

public interface PasswordEncoderUseCase {
    String encode(String password);
}
