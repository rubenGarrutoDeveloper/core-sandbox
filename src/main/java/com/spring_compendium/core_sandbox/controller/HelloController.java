package com.spring_compendium.core_sandbox.controller;

import com.spring_compendium.core_sandbox.costants.ApiPaths;
import com.spring_compendium.core_sandbox.dto.Greeting;
import com.spring_compendium.core_sandbox.exception.ResourceNotFoundException;
import com.spring_compendium.core_sandbox.service.IdGeneratorService;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    // 1. GET /api/hello - Endpoint base
    // Nuova implementazione con ResponseEntity.ok() per un 200 OK esplicito.
    @GetMapping
    public ResponseEntity<Greeting> hello() {
        logger.info("GET /api/hello - Base");
        Greeting greeting = new Greeting("Benvenuto nel mondo REST!", "ResponseEntity Demo");

        // Restituisce Status 200 OK
        return ResponseEntity.ok(greeting);
    }

    // 2. POST /api/hello - CREATE
    // Nuova implementazione con ResponseEntity.created() per 201 Created + Header Location.
    @PostMapping
    public ResponseEntity<Greeting> createGreeting(@Valid @RequestBody Greeting greeting) {

        // 1. Assegna l'ID
        long newId = idGeneratorService.getNextId();
        greeting.setId(newId);

        logger.info("POST /api/hello - New Greeting received. Assigned ID: " + newId);

        // 2. Costruisce l'URI della nuova risorsa (es. /api/hello/1)
        // Questa è la Best Practice per le risposte 201 Created.
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest() // Prende l'URL base corrente (/api/hello)
                .path("/{id}")        // Aggiunge la parte /{id}
                .buildAndExpand(newId) // Sostituisce {id} con l'ID assegnato
                .toUri();

        // 3. Restituisce: Status 201 CREATED + Header Location + Corpo JSON
        return ResponseEntity.created(location).body(greeting);
    }

    @GetMapping("/{name}")
    public Greeting greetUser(@PathVariable String name) {
        logger.info("GET "+ApiPaths.HELLO+"/"+name);


        if(name.equals("NotFound")) {
            logger.info("GET "+ApiPaths.HELLO+"/"+name+" - Forbidden");
            throw new ResourceNotFoundException("Resource not found");
        }


        // ... Spring assegnerà "Marco" alla variabile 'name' se l'URL è /api/hello/Marco
        return new Greeting("Ciao, " + name + "...", "PathVariable Demo");
    }

    @GetMapping("/search")
    public String searchGreetings(
            @RequestParam(name = "level") String level,
            @RequestParam(name = "sortBy", required = false) String sortBy) {

        logger.info("GET "+ApiPaths.HELLO+"/search?level="+level+"&sortBy="+sortBy);
        return "Search level: " + level + ", sortBy: " + sortBy;
    }
}
