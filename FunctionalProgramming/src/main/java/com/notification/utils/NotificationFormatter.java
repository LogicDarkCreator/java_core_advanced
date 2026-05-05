package com.notification.utils;

import com.notification.model.*;
import java.util.List;

public class NotificationFormatter {

    public static void printSeparator() {
        System.out.println("==========================================");
    }

    public static void printHeader(String header) {
        printSeparator();
        System.out.println(header);
        printSeparator();
    }

    public static void printEmailNotification(EmailNotification notification) {
        System.out.println("EMAIL");
        System.out.println("subject: " + notification.getSubject());
        System.out.println("receivers: " + String.join(", ", notification.getReceivers()));
        System.out.println("message: " + notification.getFormattedMessage());
        System.out.println();
    }

    public static void printSmsNotification(SmsNotification notification) {
        System.out.println("SMS");
        System.out.println("receivers: " + String.join(", ", notification.getPhoneNumbers()));
        System.out.println("message: " + notification.getFormattedMessage());
        System.out.println();
    }

    public static void printPushNotification(PushNotification notification) {
        System.out.println("PUSH");
        System.out.println("title: " + notification.getTitle());
        System.out.println("receiver: " + notification.getUserAccount());
        System.out.println("message: " + notification.getFormattedMessage());
        System.out.println();
    }
}