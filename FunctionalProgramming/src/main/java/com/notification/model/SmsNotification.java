package com.notification.model;

import java.util.List;

public class SmsNotification implements Notification {
    private final List<String> phoneNumbers;
    private final String message;

    public SmsNotification(List<String> phoneNumbers, String message) {
        this.phoneNumbers = phoneNumbers;
        this.message = message;
    }

    @Override
    public String getFormattedMessage() {
        return message;
    }

    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    public String getMessage() {
        return message;
    }
}
