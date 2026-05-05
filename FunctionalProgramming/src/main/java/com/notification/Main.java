package com.notification;

import com.notification.model.*;
import com.notification.service.*;
import com.notification.factory.NotificationFactory;
import com.notification.utils.NotificationFormatter;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // Вариант 1: Использование сервисов (основное задание)
        demonstrateWithServices();

        // Вариант 2: Использование фабрики и утилит (дополнительно)
        demonstrateWithFactoryAndUtils();
    }

    private static void demonstrateWithServices() {
        NotificationFormatter.printHeader("=== Демонстрация с использованием сервисов ===");

        // Создаем уведомления напрямую
        EmailNotification email1 = new EmailNotification(
                "Успешная регистрация!",
                List.of("oleg@java.skillbox.ru", "masha@java.skillbox.ru", "yan@java.skillbox.ru"),
                "Спасибо за регистрацию на сервисе!"
        );

        SmsNotification sms1 = new SmsNotification(
                List.of("+70001234567"),
                "Спасибо за регистрацию на сервисе!"
        );

        PushNotification push1 = new PushNotification(
                "Успешная регистрация!",
                "o.yanovich",
                "Спасибо за регистрацию на сервисе!"
        );

        // Создаем списки
        List<EmailNotification> emails = List.of(
                email1,
                new EmailNotification("Важный апдейт", List.of("admin@skillbox.ru"), "Обновление в 23:00"),
                new EmailNotification("Новости", List.of("all@skillbox.ru"), "Новые функции")
        );

        // Создаем сервисы
        EmailNotificationSender emailSender = new EmailNotificationSender();
        SmsNotificationSender smsSender = new SmsNotificationSender();
        PushNotificationSender pushSender = new PushNotificationSender();

        // Отправляем отдельные уведомления
        NotificationFormatter.printHeader("Отправка отдельных уведомлений");
        emailSender.send(email1);
        smsSender.send(sms1);
        pushSender.send(push1);

        // Отправляем списки
        NotificationFormatter.printHeader("Отправка списка Email уведомлений");
        emailSender.send(emails);
    }

    private static void demonstrateWithFactoryAndUtils() {
        NotificationFormatter.printHeader("=== Демонстрация с использованием фабрики и утилит ===");

        // Получаем уведомления из фабрики
        List<EmailNotification> emails = NotificationFactory.createSampleEmailNotifications();
        List<SmsNotification> smses = NotificationFactory.createSampleSmsNotifications();
        List<PushNotification> pushes = NotificationFactory.createSamplePushNotifications();

        // Выводим с помощью утилит
        NotificationFormatter.printHeader("Email уведомления");
        emails.forEach(NotificationFormatter::printEmailNotification);

        NotificationFormatter.printHeader("SMS уведомления");
        smses.forEach(NotificationFormatter::printSmsNotification);

        NotificationFormatter.printHeader("Push уведомления");
        pushes.forEach(NotificationFormatter::printPushNotification);
    }
}