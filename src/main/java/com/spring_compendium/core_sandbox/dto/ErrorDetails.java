package com.spring_compendium.core_sandbox.dto;

import lombok.Getter;

import java.time.LocalDateTime;

/**
 * DTO standardizzato per le risposte di errore.
 * Questo assicura che il JSON in caso di errore sia sempre uniforme.
 */
@Getter
public class ErrorDetails {

    private final LocalDateTime timestamp;
    private final String message;
    private final String path;
    private final int status;

    public ErrorDetails(String message, String path, int status) {
        this.timestamp = LocalDateTime.now();
        this.message = message;
        this.path = path;
        this.status = status;
    }
}
