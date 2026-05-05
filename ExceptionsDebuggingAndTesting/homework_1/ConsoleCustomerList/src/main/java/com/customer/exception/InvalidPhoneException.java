package com.customer.exception;

public class InvalidPhoneException extends CustomerException {
    public InvalidPhoneException(String userMessage, String detailedMessage) {
        super(userMessage, detailedMessage);
    }
}
