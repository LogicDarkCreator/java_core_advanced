package com.customer.exception;

public class CustomerException extends RuntimeException {
    private final String userMessage;
    private final String detailedMessage;

    public CustomerException(String userMessage, String detailedMessage) {
        super(detailedMessage);
        this.userMessage = userMessage;
        this.detailedMessage = detailedMessage;
    }

    public CustomerException(String userMessage, String detailedMessage, Throwable cause) {
        super(detailedMessage, cause);
        this.userMessage = userMessage;
        this.detailedMessage = detailedMessage;
    }

    public String getUserMessage() { return userMessage; }
    public String getDetailedMessage() { return detailedMessage; }
}
