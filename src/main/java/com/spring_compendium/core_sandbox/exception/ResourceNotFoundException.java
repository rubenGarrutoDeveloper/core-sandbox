package com.spring_compendium.core_sandbox.exception;


/**
 * Eccezione Custom per simulare il caso in cui una risorsa richiesta non esista.
 * Quando lanciata, vogliamo che Spring restituisca un codice 404 Not Found.
 */
public class ResourceNotFoundException extends RuntimeException {

    // Costruttore standard che accetta un messaggio per l'errore.
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
