package com.microservice.event_notification.controller;

import com.microservice.event_notification.model.EmailRequest;
import com.microservice.event_notification.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    // Endpoint
    @PostMapping("/send")
    public String sendEmail(@RequestBody EmailRequest request) {
        notificationService.sendTo(
                request.getTo(),
                request.getSubject(),
                request.getBody()
        );
        return "Notification envoyée avec succès à " + request.getTo();
    }
}