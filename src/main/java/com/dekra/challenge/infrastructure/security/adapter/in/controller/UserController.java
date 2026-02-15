package com.dekra.challenge.infrastructure.security.adapter.in.controller;


import com.dekra.challenge.domain.security.modelo.User;
import com.dekra.challenge.domain.security.ports.in.usercase.LogInUserCase;
import com.dekra.challenge.domain.security.ports.in.usercase.RegisterUserUseCase;
import com.dekra.challenge.infrastructure.product.mapper.UserMapper;
import com.dekra.challenge.infrastructure.product.mapper.UserMapperImpl;
import com.dekra.challenge.infrastructure.security.adapter.in.controller.dto.LogInRequest;
import com.dekra.challenge.infrastructure.security.adapter.in.controller.dto.RegisterRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final LogInUserCase logInUserCase;
    private final RegisterUserUseCase registerUserUseCase;
    private final UserMapperImpl userMapper;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LogInRequest logInRequest) {
        String token = logInUserCase.authenticate(logInRequest.getEmail(), logInRequest.getPassword());
        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
        User user = userMapper.toDomain(registerRequest);
        String token = registerUserUseCase.register(user);
        return ResponseEntity.ok(token);
    }
}
