package com.spring_compendium.core_sandbox.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotNull(message = "Il messaggio non può essere nullo.")
    @Size(min = 1, max = 50, message = "Il messaggio deve contenere tra 1 e 50 caratteri.")
    private String message;

    @NotNull(message = "Il campo sender è obbligatorio.")
    @NotEmpty(message = "Il campo sender non può essere vuoto.")
    private String sender;

    private int charactersUsed;

    /**
     * Costruttore completo, usato dal Controller quando RESTITUISCE l'oggetto (con ID).
     */
    public Greeting(Long id, String message, String sender) {
        this.id = id;
        this.setMessage(message);
        this.sender = sender;
    }

    /**
     * Costruttore per la creazione dell'oggetto IN ENTRATA (@RequestBody), dove l'ID è null/0.
     */
    public Greeting(String message, String sender) {
        this(null, message, sender);
    }

    public void setMessage(String message) {
        this.message = message;
        // CORREZIONE: Calcola la lunghezza non appena il campo viene impostato
        this.charactersUsed = (message != null) ? message.length() : 0;
    }
}
