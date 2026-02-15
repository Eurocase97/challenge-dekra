package com.dekra.challenge.domain.security.ports.in.usercase;

public interface LogInUserCase {

   String  authenticate(String email, String password);
}
