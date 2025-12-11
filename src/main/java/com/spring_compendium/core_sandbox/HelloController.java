package com.spring_compendium.core_sandbox;

import com.spring_compendium.core_sandbox.costants.ApiPaths;
import com.spring_compendium.core_sandbox.dto.Greeting;
import com.spring_compendium.core_sandbox.service.IdGeneratorService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/{name}")
    public Greeting greetUser(@PathVariable String name) {
        logger.info("GET "+ApiPaths.HELLO+"/"+name);
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
