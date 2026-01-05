package com.microservice.event_notification.service;

public interface NotificationService {
    void sendNotification(String subject, String message);
    void sendTo(String to, String subject, String message);
}