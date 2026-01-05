package com.microservice.event_notification.service;

import com.microservice.event_notification.model.Event;
import com.microservice.event_notification.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private NotificationService notificationService; // Middleware

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event createEvent(Event event) {
        Event savedEvent = eventRepository.save(event);
        // Déclenchement de la notification
        notificationService.sendNotification(
                "Nouvel événement créé !",
                "L'événement '" + savedEvent.getTitle() + "' a été ajouté."
        );
        return savedEvent;
    }

    public Event updateEvent(Long id, Event eventDetails) {
        Optional<Event> optionalEvent = eventRepository.findById(id);
        if (optionalEvent.isPresent()) {
            Event existingEvent = optionalEvent.get();
            existingEvent.setTitle(eventDetails.getTitle());
            existingEvent.setDescription(eventDetails.getDescription());
            existingEvent.setDate(eventDetails.getDate());

            Event updatedEvent = eventRepository.save(existingEvent);

            // Notification de mise à jour
            notificationService.sendNotification(
                    "Événement modifié",
                    "L'événement '" + updatedEvent.getTitle() + "' a été mis à jour."
            );
            return updatedEvent;
        }
        return null;
    }

    public void deleteEvent(Long id) {
        Optional<Event> event = eventRepository.findById(id);
        if(event.isPresent()) {
            eventRepository.deleteById(id);
            // Notification de suppression
            notificationService.sendNotification(
                    "Événement supprimé",
                    "L'événement '" + event.get().getTitle() + "' a été annulé."
            );
        }
    }
}