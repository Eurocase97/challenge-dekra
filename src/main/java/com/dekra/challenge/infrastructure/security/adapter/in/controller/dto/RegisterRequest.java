package com.dekra.challenge.infrastructure.security.adapter.in.controller.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String email;
    private String password;
}
