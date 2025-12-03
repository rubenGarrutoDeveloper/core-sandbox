package com.spring_compendium.core_sandbox.service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary // Diciamo a Spring: "Se non specifichi, scegli questo!"
public class EmailService implements MessageService {

    private boolean isConnectionOpen = false; // Stato per dimostrazione

    // 1. FASE DI NASCITA: Eseguita dopo che il Bean è stato creato e iniettato.
    @PostConstruct
    public void setupService() {
        System.out.println("[LIFECYCLE] 🟢 EmailService: Avvio e apertura risorse (es. connessione SMTP).");
        this.isConnectionOpen = true;
    }

    @Override
    public void sendMessage(String message, String recipient) {
        if (!isConnectionOpen) {
            System.err.println("!!! ERRORE: La connessione email non è aperta. Impossibile inviare.");
            return;
        }
        System.out.println("--- EMAIL SERVICE (PRIMARY) ---");
        System.out.println("Invio email a: " + recipient);
        System.out.println("Corpo: " + message);
    }

    // 2. FASE DI MORTE: Eseguita quando l'ApplicationContext viene chiuso.
    @PreDestroy
    public void cleanupService() {
        System.out.println("[LIFECYCLE] 🔴 EmailService: Chiusura delle risorse (es. disconnessione SMTP).");
        this.isConnectionOpen = false;
    }
}
