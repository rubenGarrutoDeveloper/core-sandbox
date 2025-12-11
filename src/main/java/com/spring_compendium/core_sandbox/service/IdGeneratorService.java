package com.spring_compendium.core_sandbox.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Questo è un Bean Singleton (@Service).
 * AtomicLong garantisce che l'incremento sia thread-safe in un ambiente multi-thread come Spring.
 * E' il nostro "database" fittizio per l'assegnazione degli ID.
 */
@Service
public class IdGeneratorService {

    // Inizia il contatore da 1. AtomicLong per sicurezza in ambienti concorrenti.
    private final AtomicLong counter = new AtomicLong(0);

    /**
     * Incrementa il contatore e restituisce il nuovo valore.
     * @return Il prossimo ID univoco.
     */
    public long getNextId() {
        return counter.incrementAndGet();
    }
}
