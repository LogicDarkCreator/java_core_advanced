package com.notification.model;

public class PushNotification implements Notification {
    private final String title;
    private final String userAccount;
    private final String message;
    private static final String WAVING_HAND_EMOJI = "\ud83d\udc4b";

    public PushNotification(String title, String userAccount, String message) {
        this.title = title;
        this.userAccount = userAccount;
        this.message = message;
    }

    @Override
    public String getFormattedMessage() {
        return WAVING_HAND_EMOJI + " " + message;
    }

    public String getTitle() {
        return title;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public String getMessage() {
        return message;
    }
}
