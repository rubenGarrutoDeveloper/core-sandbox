package com.spring_compendium.core_sandbox;

import com.spring_compendium.core_sandbox.costants.ApiPaths;
import com.spring_compendium.core_sandbox.dto.Greeting;
import lombok.extern.java.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping(ApiPaths.HELLO)
public class HelloController {

    Logger logger = Logger.getLogger(HelloController.class.getName());

    // 1. GET /api/hello
    // Restituisce un oggetto Greeting. Spring lo trasforma in JSON automaticamente.
    @GetMapping
    public Greeting sayHello() {
        logger.info("GET /api/hello");
        return new Greeting("Benvenuto nel mondo REST!", "Spring Boot");
    }
}
