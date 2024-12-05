package com.example.IngSoftware;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.event.ApplicationStartedEvent;

@SpringBootApplication
public class IngSoftwareApplication {
    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(IngSoftwareApplication.class);

        // Agregar listeners para eventos de inicio
        app.addListeners(event -> {
            if (event instanceof ApplicationStartedEvent) {
                System.out.println(">> Aplicación iniciada correctamente <<");
            }
            if (event instanceof ApplicationReadyEvent) {
                System.out.println(">> Aplicación lista para operar <<");
            }
        });

        // Ejecutar la aplicación
        try {
            app.run(args);
        } catch (Exception e) {
            System.err.println("Error crítico al iniciar la aplicación: " + e.getMessage());
            e.printStackTrace();
        }
    }
}