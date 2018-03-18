package com.skip.challenge.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundException extends FunctionalException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    @Override
    int getResponseCode() {
        return HttpStatus.NOT_FOUND.value();
    }
}
