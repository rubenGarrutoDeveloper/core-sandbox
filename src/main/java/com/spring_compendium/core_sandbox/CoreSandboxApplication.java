package com.spring_compendium.core_sandbox;

import com.spring_compendium.core_sandbox.manager.NotificationManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class CoreSandboxApplication {

	public static void main(String[] args) {
		// Avvia il Container Spring e restituisce il contesto
		SpringApplication.run(CoreSandboxApplication.class, args);
	}
}
