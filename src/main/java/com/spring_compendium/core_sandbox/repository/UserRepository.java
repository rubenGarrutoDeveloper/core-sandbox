package com.spring_compendium.core_sandbox.repository;

import com.spring_compendium.core_sandbox.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository per l'entità User.
 * Estende JpaRepository per ottenere operazioni CRUD standard.
 */
@Repository // Opzionale (JpaRepository lo implica), ma buona pratica per chiarezza
public interface UserRepository extends JpaRepository<User, Long> {

    // I metodi CRUD base (save, findAll, findById) sono ereditati.

}
