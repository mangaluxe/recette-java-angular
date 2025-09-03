package org.example.backspringboot.controller;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    /**
     * Test de Cryptage BCrypt
     */
    @GetMapping("/test")
    public void testCryptage() {
        String password = "1234";
        String hashed = BCrypt.hashpw(password, BCrypt.gensalt()); // BCrypt.hashpw() pour hasher
        System.out.println("Mot de passe hashé : " + hashed); // Exemple: $2a$10$iFp8RTN0gJuFprcbbLu3QemQwGEPqHUi6pLRLJI6Ma004xKGhsUze
    }

    /**
     * Test de Décryptage BCrypt
     */
    @GetMapping("/test2")
    public void testDecryptage() {
        String pseudo = "haiou";
        String motdepasse = "1234";
        String hashedPassword = "$2a$10$iFp8RTN0gJuFprcbbLu3QemQwGEPqHUi6pLRLJI6Ma004xKGhsUze"; // Hash de "1234"

        if (pseudo.equals("haiou") && BCrypt.checkpw(motdepasse, hashedPassword)) { // BCrypt.checkpw() pour vérifier hashage
            System.out.println("Pseudo et mot de passe correctes");
        }
        else {
            System.out.println("Erreur pseudo ou mot de passe");
        }
    }

}
