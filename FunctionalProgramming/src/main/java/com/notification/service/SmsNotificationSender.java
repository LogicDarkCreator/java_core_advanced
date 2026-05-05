package com.notification.service;

import com.notification.model.SmsNotification;
import java.util.List;

public class SmsNotificationSender implements NotificationSender<SmsNotification> {

    @Override
    public void send(SmsNotification notification) {
        System.out.println("SMS");
        System.out.println("receivers: " + String.join(", ", notification.getPhoneNumbers()));
        System.out.println("message: " + notification.getFormattedMessage());
        System.out.println();
    }

    @Override
    public void send(List<SmsNotification> notifications) {
        for (SmsNotification notification : notifications) {
            send(notification);
        }
    }
}