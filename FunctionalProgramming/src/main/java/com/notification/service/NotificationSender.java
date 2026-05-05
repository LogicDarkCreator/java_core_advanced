package com.notification.service;

import com.notification.model.Notification;
import java.util.List;

public interface NotificationSender<T extends Notification> {
    void send(T notification);
    void send(List<T> notifications);
}
