package com.dekra.challenge.infrastructure.security.adapter.in.controller;

import com.dekra.challenge.domain.security.ports.in.usercase.LogInUserCase;
import com.dekra.challenge.domain.security.ports.in.usercase.RegisterUserUseCase;
import com.dekra.challenge.infrastructure.product.mapper.UserMapperImpl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private LogInUserCase logInUserCase;

    @MockitoBean
    private RegisterUserUseCase registerUserUseCase;

    @MockitoBean
    private UserMapperImpl userMapper;

    @MockitoBean
    private com.dekra.challenge.infrastructure.security.jwt.JWTService jwtService;

    @MockitoBean
    private com.dekra.challenge.infrastructure.security.jwt.JWTFilter jwtFilter;

    private final String token = "eadfg74asdg7a4dga56dfg";


    @Test
    void login_ShouldReturn200AndToken() throws Exception {
        String requestBody = """
            {
                "email": "test@example.com",
                "password": "password123"
            }
            """;

        when(logInUserCase.authenticate(any(), any())).thenReturn(token);

        mockMvc.perform(post("/api/v1/users/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().string(token));
    }

    @Test
    void register_ShouldReturn200AndToken() throws Exception {
        // Given
        String requestBody = """
            {
                "email": "newuser@example.com",
                "password": "password123",
                "name": "Test User"
            }
            """;

        when(registerUserUseCase.register(any())).thenReturn(token);

        mockMvc.perform(post("/api/v1/users/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().string(token));
    }
}