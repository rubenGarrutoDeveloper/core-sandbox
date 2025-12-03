package com.spring_compendium.core_sandbox.manager;

import com.spring_compendium.core_sandbox.service.MessageService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class NotificationManager {

    private final MessageService messageService;

    // Usiamo @Qualifier per dire a Spring: "Voglio specificamente il Bean 'smsService'"
    public NotificationManager(
            @Qualifier("smsService") // Il nome del Bean è la classe in camelCase
            MessageService messageService) {
        this.messageService = messageService;
        System.out.println("[DI Check] NotificationManager creato e ha ricevuto la dipendenza: " + messageService.getClass().getSimpleName());
    }

    public void sendNotification(String message, String recipient) {
        System.out.println("\n[Manager] Richiesta di notifica in corso...");
        messageService.sendMessage(message, recipient);
    }
}
