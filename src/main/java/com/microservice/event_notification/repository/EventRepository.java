package com.microservice.event_notification.repository;

import com.microservice.event_notification.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
    // Implémenté par Spring
}