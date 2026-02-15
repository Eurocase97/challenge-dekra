package com.dekra.challenge.application.security.useCase;

import com.dekra.challenge.domain.security.ports.in.usercase.AuthenticationUseCase;
import com.dekra.challenge.domain.security.ports.in.usercase.LogInUserCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogInUserCaseImpl implements LogInUserCase {

    private final AuthenticationUseCase authentication;


    @Override
    public String authenticate(String email, String password) {
        return  authentication.authenticate(email, password);
    }
}
