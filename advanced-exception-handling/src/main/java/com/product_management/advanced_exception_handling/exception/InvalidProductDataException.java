package com.product_management.advanced_exception_handling.exception;

public class InvalidProductDataException extends RuntimeException{
    public InvalidProductDataException(String message) {
        super(message);
    }
}
