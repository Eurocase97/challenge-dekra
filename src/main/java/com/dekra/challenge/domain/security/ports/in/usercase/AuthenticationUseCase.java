package com.dekra.challenge.domain.security.ports.in.usercase;

public interface AuthenticationUseCase {

    String authenticate(String username, String password);

}
