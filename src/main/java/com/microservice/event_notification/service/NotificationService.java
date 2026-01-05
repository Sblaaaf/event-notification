package com.microservice.event_notification.service;

public interface NotificationService {
    void sendNotification(String subject, String message);
}