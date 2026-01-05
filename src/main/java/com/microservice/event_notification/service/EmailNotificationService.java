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
    public void sendNotification(String subject, String messageBody) {
        List<User> users = userRepository.findAll();

        for (User user : users) {
            try {
                SimpleMailMessage message = new SimpleMailMessage();
                // message.setFrom("noreply@monapp.com"); // Configuré dans properties
                message.setTo(user.getEmail());
                message.setSubject(subject);
                message.setText("Bonjour " + user.getName() + ",\n\n" + messageBody);

                // Si pas de SMTP configuré, on log juste
                System.out.println("SIMULATION ENVOI MAIL à : " + user.getEmail() + " | Sujet: " + subject);

                // SMTP (Gmail, Mailtrap) configuré
                emailSender.send(message);

            } catch (Exception e) {
                System.err.println("Erreur lors de l'envoi à " + user.getEmail());
            }
        }
    }
}
