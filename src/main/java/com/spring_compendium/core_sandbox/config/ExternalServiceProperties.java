package com.spring_compendium.core_sandbox.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Classe che funge da contenitore per un gruppo di proprietà esterne.
 * Questo è l'approccio "Senior" per la configurazione.
 * * 1. @Component: Registra questa classe come Bean nel container Spring.
 * 2. @ConfigurationProperties: Mappa le proprietà che iniziano con "external.service"
 * sui campi di questa classe (es. external.service.url -> url).
 */
@Component
@ConfigurationProperties(prefix = "external.service")
@Getter
@Setter
@ToString
public class ExternalServiceProperties {

    // external.service.url
    private String url;

    // external.service.api-key
    private String apiKey;

    // external.service.timeout-ms (Spring converte automaticamente il tipo)
    private int timeoutMs;
}

