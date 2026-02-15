package com.dekra.challenge.application.security.useCase;

import com.dekra.challenge.domain.security.ports.in.usercase.PasswordEncoderUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PasswordEncoderUseCaseImpl implements PasswordEncoderUseCase {

    private final PasswordEncoder passwordEncoder;

    @Override
    public String encode(String password) {
        return passwordEncoder.encode(password);
    }
}
