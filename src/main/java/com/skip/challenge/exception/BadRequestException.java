package com.skip.challenge.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends FunctionalException {

    public BadRequestException(String message) {
        super(message);
    }

    @Override
    int getResponseCode() {
        return HttpStatus.BAD_REQUEST.value();
    }
}
