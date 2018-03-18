package com.skip.challenge.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends FunctionalException {

    public BadRequestException(String message) {
        super(message);
    }

    @Override
    HttpStatus getStatus() {
        return HttpStatus.BAD_REQUEST;
    }
}
