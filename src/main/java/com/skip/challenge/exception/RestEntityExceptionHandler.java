package com.skip.challenge.exception;

import com.skip.challenge.vo.ErrorDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@RestController
@ControllerAdvice
public class RestEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(
                new ErrorDetails("Validation Exception", ex.getBindingResult().toString()),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = { FunctionalException.class })
    protected ResponseEntity<Object> handleFunctionException(HttpServletRequest req, Exception ex) {
        return new ResponseEntity<>(
                new ErrorDetails(ex.getMessage(), null),
                ((FunctionalException)ex).getStatus());
    }

}
