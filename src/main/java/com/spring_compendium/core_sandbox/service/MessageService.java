package com.spring_compendium.core_sandbox.service;

/**
 * Interfaccia che definisce il contratto per l'invio di un messaggio.
 * Questo è il "cosa" fa il servizio, indipendentemente dal "come".
 */
public interface MessageService {
    void sendMessage(String message, String recipient);
}
