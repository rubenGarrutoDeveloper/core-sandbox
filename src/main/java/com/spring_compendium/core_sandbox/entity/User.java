package com.spring_compendium.core_sandbox.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * Entità JPA che rappresenta un Utente del sistema.
 * Mappata sulla tabella "users" nel database.
 */
@Entity
@Table(name = "users") // Specifichiamo il nome della tabella (spesso plurale)
@Getter
@Setter
@NoArgsConstructor // JPA richiede un costruttore vuoto obbligatorio
@AllArgsConstructor // Utile per i test
@ToString
public class User {

    @Id // Chiave Primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment (1, 2, 3...)
    private Long id;

    // Vincolo: Email deve essere unica e non nulla. Lunghezza max 100.
    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    // Vincolo: Nome obbligatorio.
    @Column(name = "full_name", nullable = false)
    private String fullName;

    // Mappatura di un Enum: Salviamo la STRINGA ("ACTIVE"), non il numero (0).
    // Questo rende il DB leggibile e resistente ai cambiamenti dell'ordine dell'Enum.
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private UserStatus status;

    // Timestamp di creazione. Updatable=false impedisce modifiche successive.
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // Callback JPA: Eseguito automaticamente PRIMA di salvare un nuovo record.
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        if (this.status == null) {
            this.status = UserStatus.ACTIVE; // Default
        }
    }

    // Enum interno per lo stato dell'utente
    public enum UserStatus {
        ACTIVE, INACTIVE, BANNED
    }
}
