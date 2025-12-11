package com.spring_compendium.core_sandbox.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * DTO (Data Transfer Object) per il Greeting.
 * - @Getter / @Setter: Generano i metodi necessari per Jackson.
 * - @NoArgsConstructor: Necessario a Jackson per la deserializzazione JSON in entrata.
 */
@Getter
@Setter // Aggiungiamo @Setter per permettere l'uso di setId() nel Controller
@NoArgsConstructor
public class Greeting {

    private Long id; // Si preferisce Long per gli ID
    private String message;
    private String sender;

    /**
     * Costruttore completo, usato dal Controller quando RESTITUISCE l'oggetto (con ID).
     */
    public Greeting(Long id, String message, String sender) {
        this.id = id;
        this.message = message;
        this.sender = sender;
    }

    /**
     * Costruttore per la creazione dell'oggetto IN ENTRATA (@RequestBody), dove l'ID è null/0.
     */
    public Greeting(String message, String sender) {
        this(null, message, sender);
    }
}
