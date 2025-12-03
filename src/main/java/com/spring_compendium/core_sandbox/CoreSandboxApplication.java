package com.spring_compendium.core_sandbox;

import com.spring_compendium.core_sandbox.manager.NotificationManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class CoreSandboxApplication {

	public static void main(String[] args) {
		// Avvia il Container Spring e restituisce il contesto
		ConfigurableApplicationContext context = SpringApplication.run(CoreSandboxApplication.class, args);

		System.out.println("\n=============================================");
		System.out.println("  1. Spring Container avviato. ");
		System.out.println("=============================================");

		// Chiediamo al Container di darci il NotificationManager, senza chiamare 'new'.
		// Spring lo recupera, completo di tutte le dipendenze iniettate.
		NotificationManager manager = context.getBean(NotificationManager.class);

		// Usiamo il Bean.
		manager.sendNotification("Benvenuto nel Compendio Spring!", "studente@example.com");

		System.out.println("=============================================");
	}
}
