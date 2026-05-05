package com.notification.service;

import com.notification.model.PushNotification;
import java.util.List;

public class PushNotificationSender implements NotificationSender<PushNotification> {

    @Override
    public void send(PushNotification notification) {
        System.out.println("PUSH");
        System.out.println("title: " + notification.getTitle());
        System.out.println("receiver: " + notification.getUserAccount());
        System.out.println("message: " + notification.getFormattedMessage());
        System.out.println();
    }

    @Override
    public void send(List<PushNotification> notifications) {
        for (PushNotification notification : notifications) {
            send(notification);
        }
    }
}