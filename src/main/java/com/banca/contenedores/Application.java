package com.banca.contenedores;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.Arrays;

@SpringBootApplication
@EnableAsync
@EnableScheduling
public class Application {
    private final Environment environment;

    public Application(Environment environment) {
        this.environment = environment;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        String activeProfiles = Arrays.toString(environment.getActiveProfiles());
        System.out.printf("\n\n=== Aplicación iniciada con perfiles: %s ===\n", activeProfiles);
        System.out.println("Servicio de orquestación de contenedores listo para operar.");
        System.out.println("Endpoints disponibles:");
        System.out.println("- API REST: http://localhost:" + environment.getProperty("server.port", "8080"));
        System.out.println("- Actuator: http://localhost:" + environment.getProperty("server.port", "8080") + "/actuator");
    }

    @EventListener(org.springframework.context.event.ContextClosedEvent.class)
    public void onContextClosed() {
        System.out.println("\n=== Aplicación detenida ===\n");
    }
}