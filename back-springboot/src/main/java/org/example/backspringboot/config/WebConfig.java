package org.example.backspringboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // ----- Autoriser les requêtes CORS -----

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Applique la configuration à toutes les API (toutes les routes)
                .allowedOrigins("http://localhost:3000", "http://localhost:4200", "http://localhost") // Autorise React, Angular et noms de domaine
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Les méthodes autorisées
                .allowedHeaders("*") // Autorise tous les headers
                .allowCredentials(true); // Autorise les cookies, si nécessaire
    }

    // ----- Upload -----

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/**")
                .addResourceLocations("file:uploads/"); // Dossier local "uploads"
    }

}