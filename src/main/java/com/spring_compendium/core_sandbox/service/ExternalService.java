package com.spring_compendium.core_sandbox.service;

import com.spring_compendium.core_sandbox.config.ExternalServiceProperties;
import org.springframework.stereotype.Service;

/**
 * Un servizio fittizio che dipende dalle proprietà di configurazione.
 * Inietta l'intero contenitore di proprietà, non i singoli campi.
 */
@Service
public class ExternalService {

    private final ExternalServiceProperties properties;

    // Spring inietta l'oggetto ConfigurationProperties completo
    public ExternalService(ExternalServiceProperties properties) {
        this.properties = properties;
        System.out.println("[DI Check] ExternalService creato. Configurazione: " + properties.toString());
    }

    public void callApi(String endpoint) {
        System.out.println("--- API Call ---");
        System.out.println("Endpoint: " + properties.getUrl() + endpoint);
        System.out.println("Chiave usata: " + properties.getApiKey());
        System.out.println("Timeout configurato: " + properties.getTimeoutMs() + "ms");
        System.out.println("----------------");
    }
}
