package com.microservice.event_notification.config;

import com.microservice.event_notification.model.Event;
import com.microservice.event_notification.model.User;
import com.microservice.event_notification.repository.EventRepository;
import com.microservice.event_notification.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.util.Arrays;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner initDatabase(UserRepository userRepo, EventRepository eventRepo) {
        return args -> {
            // 1. Création des Users (10 faux + 1 vrai)
            userRepo.save(new User("lourgouilloux.renaud@gmail.com", "Renaud (Test)")); // Test

            for (int i = 1; i <= 9; i++) {
                userRepo.save(new User("fakeuser" + i + "@example.com", "User " + i));
            }

            // 2. Création des Événements
            Event past = new Event(null, "Conférence Java 1", "C'était bien", LocalDateTime.now().minusMonths(1));
            Event present = new Event(null, "Hackathon", "En cours !", LocalDateTime.now());
            Event future = new Event(null, "Sortie Spring Boot", "Bientôt...", LocalDateTime.now().plusWeeks(2));

            eventRepo.saveAll(Arrays.asList(past, present, future));

            System.out.println("Base de données initialisée avec succès !");
        };
    }
}