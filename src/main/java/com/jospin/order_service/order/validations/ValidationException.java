package com.jospin.order_service.order.validations;

public class ValidationException extends RuntimeException {

    private final ValidationErrorType type;

    public ValidationException(String message, ValidationErrorType type) {
        super(message);
        this.type = type;
    }

    public ValidationErrorType getType() {
        return type;
    }
}