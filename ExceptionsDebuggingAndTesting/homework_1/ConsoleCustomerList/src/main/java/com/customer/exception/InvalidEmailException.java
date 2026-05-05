package com.customer.exception;

public class InvalidEmailException extends CustomerException {
    public InvalidEmailException(String userMessage, String detailedMessage) {
        super(userMessage, detailedMessage);
    }
}
