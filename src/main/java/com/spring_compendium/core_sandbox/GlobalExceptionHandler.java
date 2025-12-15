package com.spring_compendium.core_sandbox;

import com.spring_compendium.core_sandbox.dto.ErrorDetails;
import com.spring_compendium.core_sandbox.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

/**
 * Classe che funge da Gestore di Eccezioni centralizzato.
 * @RestControllerAdvice combina @ControllerAdvice e @ResponseBody,
 * garantendo che l'output sia formattato in JSON.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Questo metodo intercetta tutte le eccezioni di tipo ResourceNotFoundException
     * lanciate da QUALSIASI Controller.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleResourceNotFoundException(
            ResourceNotFoundException ex,
            WebRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND;

        ErrorDetails errorDetails = new ErrorDetails(
                ex.getMessage(),
                request.getDescription(false).replace("uri=", ""), // Cattura l'URL della richiesta
                status.value()
        );

        // Restituisce Status 404 NOT FOUND e il body strutturato
        return new ResponseEntity<>(errorDetails, status);
    }

    /**
     * (Esempio) Gestione per tutte le altre eccezioni non gestite esplicitamente.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleGlobalException(
            Exception ex,
            WebRequest request) {

        ErrorDetails errorDetails = new ErrorDetails(
                "Errore interno del server: " + ex.getMessage(),
                request.getDescription(false).replace("uri=", ""),
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );

        // Restituisce Status 500 INTERNAL SERVER ERROR
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}