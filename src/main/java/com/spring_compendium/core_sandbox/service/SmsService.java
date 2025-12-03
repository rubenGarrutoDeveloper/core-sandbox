package com.spring_compendium.core_sandbox.service;

import org.springframework.stereotype.Service;

/**
 * Implementazione specifica per l'invio di SMS.
 * L'annotazione @Service lo registra come Bean nel Container Spring.
 */
@Service
public class SmsService implements MessageService {

    @Override
    public void sendMessage(String message, String recipient) {
        System.out.println("--- SMS SERVICE ---");
        System.out.println("Invio SMS al numero: " + recipient);
        System.out.println("Corpo: " + message);
    }
}
