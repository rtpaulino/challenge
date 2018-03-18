package com.skip.challenge.exception;

public abstract class FunctionalException extends RuntimeException {

    public FunctionalException(String message) {
        super(message);
    }

    abstract int getResponseCode();
}
