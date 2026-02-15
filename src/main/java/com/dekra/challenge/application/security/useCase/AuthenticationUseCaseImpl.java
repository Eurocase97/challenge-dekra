package com.dekra.challenge.application.security.useCase;

import com.dekra.challenge.domain.security.ports.in.usercase.AuthenticationUseCase;
import com.dekra.challenge.infrastructure.security.adapter.out.entity.UserEntity;
import com.dekra.challenge.infrastructure.security.jwt.JWTService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationUseCaseImpl implements AuthenticationUseCase {

    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    @Override
    public String authenticate(String username, String password) {

        Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        username,
                        password
                )
        );

        UserEntity entity = (UserEntity) authenticate.getPrincipal();

        return jwtService.generateToken(entity);
    }
}
