package com.jospin.order_service.order.validations;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidationHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponseValidation> handle(ValidationException ex) {

        ErrorResponseValidation response = ErrorResponseValidation.builder()
                .message(ex.getMessage())
                .type(ex.getType())
                .timestamp(LocalDateTime.now())
                .build();

        HttpStatus status = switch (ex.getType()) {
            case NOT_FOUND -> HttpStatus.NOT_FOUND;
            case INVALID_REQUEST -> HttpStatus.BAD_REQUEST;
            default -> HttpStatus.BAD_REQUEST;
        };

        return new ResponseEntity<>(response, status);
    }
}
