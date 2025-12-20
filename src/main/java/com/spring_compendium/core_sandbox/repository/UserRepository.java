package com.spring_compendium.core_sandbox.repository;

import com.spring_compendium.core_sandbox.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repository per l'entità User.
 * Estende JpaRepository per ottenere operazioni CRUD standard.
 */
@Repository // Opzionale (JpaRepository lo implica), ma buona pratica per chiarezza
public interface UserRepository extends JpaRepository<User, Long> {

    // I metodi CRUD base (save, findAll, findById) sono ereditati.

    /**
     * Trova un utente in base alla sua email esatta.
     * Restituisce Optional per gestire il caso "non trovato" senza null.
     * SQL: SELECT * FROM users WHERE email = ?
     */
    Optional<User> findByEmail(String email);

    /**
     * Verifica se esiste un utente con questa email.
     * Molto efficiente per validare se un'email è già in uso.
     * SQL: SELECT count(*) > 0 FROM users WHERE email = ?
     */
    boolean existsByEmail(String email);

    /**
     * Trova tutti gli utenti con uno specifico stato.
     * SQL: SELECT * FROM users WHERE status = ?
     */
    List<User> findByStatus(User.UserStatus status);

    /**
     * Cerca utenti il cui nome completo CONTIENE la stringa (Case Insensitive).
     * Utile per le barre di ricerca.
     * SQL: SELECT * FROM users WHERE UPPER(full_name) LIKE UPPER('%fragment%')
     */
    List<User> findByFullNameContainingIgnoreCase(String nameFragment);

}
