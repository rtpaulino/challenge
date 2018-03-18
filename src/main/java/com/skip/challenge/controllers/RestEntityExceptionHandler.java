package com.skip.challenge.controllers;

import com.skip.challenge.exception.FunctionalException;
import com.skip.challenge.vo.ErrorDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class RestEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(
                new ErrorDetails("Validation Exception", ex.getBindingResult().toString()),
                HttpStatus.NOT_FOUND);
    }

    protected ResponseEntity<Object> handleFunctionException(FunctionalException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(
                new ErrorDetails(ex.getMessage(), null),
                HttpStatus.NOT_FOUND);
    }

}
