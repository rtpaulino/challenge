package com.skip.challenge.exception;

import org.springframework.http.HttpStatus;

public abstract class FunctionalException extends RuntimeException {

    public FunctionalException(String message) {
        super(message);
    }

    abstract HttpStatus getStatus();
}
