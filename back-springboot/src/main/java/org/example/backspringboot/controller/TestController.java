package org.example.backspringboot.controller;

import jakarta.servlet.http.HttpSession;
import org.example.backspringboot.dto.SessionDto;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    // ---------- Mot de passe ----------

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


    // ---------- Session ----------

    /**
     * Afficher les données stockées en session
     */
    @GetMapping("/session")
    public ResponseEntity<?> getUserProfile(HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        String username = (String) session.getAttribute("username");
        String roleName = (String) session.getAttribute("roleName");

        // return ResponseEntity.ok("userId: " + userId + " / username: " + username + " / roleName: " + roleName);

        SessionDto profile = new SessionDto(userId, username, roleName);
        return ResponseEntity.ok(profile);
    }

}
