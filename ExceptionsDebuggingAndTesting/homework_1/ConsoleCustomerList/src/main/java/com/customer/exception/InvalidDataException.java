package com.customer.exception;

public class InvalidDataException extends CustomerException {
    public InvalidDataException(String userMessage, String detailedMessage) {
        super(userMessage, detailedMessage);
    }
}
