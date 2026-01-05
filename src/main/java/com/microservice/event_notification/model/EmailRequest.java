package com.microservice.event_notification.model;

import lombok.Data;

@Data
public class EmailRequest {
    private String to;      // L'adresse du destinataire
    private String subject; // Le sujet
    private String body;    // Le message
}