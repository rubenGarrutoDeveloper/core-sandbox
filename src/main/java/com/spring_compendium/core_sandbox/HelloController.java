package com.spring_compendium.core_sandbox;

import com.spring_compendium.core_sandbox.costants.ApiPaths;
import com.spring_compendium.core_sandbox.dto.Greeting;
import com.spring_compendium.core_sandbox.service.IdGeneratorService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping(ApiPaths.HELLO)
public class HelloController {

    Logger logger = Logger.getLogger(HelloController.class.getName());

    // Best Practice: DI tramite costruttore (campo final)
    private final IdGeneratorService idGeneratorService;

    // Spring esegue l'iniezione automaticamente (non serve @Autowired se è l'unico costruttore)
    public HelloController(IdGeneratorService idGeneratorService) {
        this.idGeneratorService = idGeneratorService;
    }

    // 1. GET /api/hello
    // Restituisce un oggetto Greeting. Spring lo trasforma in JSON automaticamente.
    @GetMapping
    public Greeting hello() {
        logger.info("GET "+ApiPaths.HELLO);
        return new Greeting("Benvenuto nel mondo REST!", "Spring Boot");
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Greeting createGreeting(@RequestBody Greeting greeting) {
        // Assegna l'ID prima di restituire l'oggetto
        long newId = idGeneratorService.getNextId();
        greeting.setId(newId);

        logger.info("POST "+ApiPaths.HELLO+" - New Greeting received. Assigned ID: " + newId);

        return greeting;
    }
}
