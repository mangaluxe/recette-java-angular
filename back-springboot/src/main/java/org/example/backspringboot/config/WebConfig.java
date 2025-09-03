package org.example.backspringboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) { // Autoriser les requêtes CORS
        registry.addMapping("/**")  // Applique la configuration à toutes les API (toutes les routes)
                .allowedOrigins("http://localhost:3000", "http://localhost:4200", "http://localhost") // Autorise React, Angular et noms de domaine
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Les méthodes autorisées
                .allowedHeaders("*") // Autorise tous les headers
                .allowCredentials(true); // Autorise les cookies, si nécessaire
    }

}