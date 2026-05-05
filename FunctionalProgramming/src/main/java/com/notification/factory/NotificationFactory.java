package com.notification.factory;

import com.notification.model.*;
import java.util.Arrays;
import java.util.List;

public class NotificationFactory {

    public static List<EmailNotification> createSampleEmailNotifications() {
        return Arrays.asList(
                new EmailNotification(
                        "Успешная регистрация!",
                        Arrays.asList("oleg@java.skillbox.ru", "masha@java.skillbox.ru", "yan@java.skillbox.ru"),
                        "Спасибо за регистрацию на сервисе!"
                ),
                new EmailNotification(
                        "Важный апдейт",
                        Arrays.asList("admin@skillbox.ru", "support@skillbox.ru"),
                        "Обновление системы произойдет сегодня в 23:00"
                ),
                new EmailNotification(
                        "Новости сервиса",
                        Arrays.asList("all-users@skillbox.ru"),
                        "Новые функции уже доступны!"
                )
        );
    }

    public static List<SmsNotification> createSampleSmsNotifications() {
        return Arrays.asList(
                new SmsNotification(
                        Arrays.asList("+70001234567"),
                        "Спасибо за регистрацию на сервисе!"
                ),
                new SmsNotification(
                        Arrays.asList("+70007654321", "+70009876543"),
                        "Ваш код подтверждения: 123456"
                ),
                new SmsNotification(
                        Arrays.asList("+70001112233"),
                        "Акция! Скидка 20% на все курсы"
                )
        );
    }

    public static List<PushNotification> createSamplePushNotifications() {
        return Arrays.asList(
                new PushNotification(
                        "Успешная регистрация!",
                        "o.yanovich",
                        "Спасибо за регистрацию на сервисе!"
                ),
                new PushNotification(
                        "Новое сообщение",
                        "ivan_petrov",
                        "У вас новое сообщение в чате"
                ),
                new PushNotification(
                        "Напоминание",
                        "maria.s",
                        "Ваш вебинар начнется через 15 минут"
                )
        );
    }
}