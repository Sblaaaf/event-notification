package com.microservice.event_notification.service;

import com.microservice.event_notification.model.User;
import com.microservice.event_notification.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmailNotificationService implements NotificationService {

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    private UserRepository userRepository;

    @Override
    public void sendTo(String to, String subject, String messageBody) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("noreply@monapp.com");
            message.setTo(to);
            message.setSubject(subject);
            message.setText(messageBody);

            System.out.println("Envoi direct à : " + to);
            emailSender.send(message);
        } catch (Exception e) {
            System.err.println("Erreur envoi direct : " + e.getMessage());
        }
    }
}
