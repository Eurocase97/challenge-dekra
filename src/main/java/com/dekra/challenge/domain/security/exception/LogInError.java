package com.dekra.challenge.domain.security.exception;

public class LogInError  extends  RuntimeException{

    public LogInError(String message)  {
        super(message);
    }
}
