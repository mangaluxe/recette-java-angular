package org.example.backspringboot.controller;

import jakarta.servlet.http.HttpSession;
import org.example.backspringboot.dto.LoginRequest;
import org.example.backspringboot.dto.LoginResponse;
import org.example.backspringboot.entity.Users;
import org.example.backspringboot.repository.UsersRepository;
import org.example.backspringboot.service.JwtService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")// URL par défaut : http://localhost:8080/api/auth
public class AuthController {

    // ========== Propriétés ==========

    private final UsersRepository usersRepository;
    private final JwtService jwtService;


    // ========== Controller ==========

    public AuthController(UsersRepository usersRepository, JwtService jwtService) {
        this.usersRepository = usersRepository;
        this.jwtService = jwtService;
    }


    // ========== Méthodes ==========

    @PostMapping("/connexion")
    public ResponseEntity<?> login(@RequestBody LoginRequest request, HttpSession session) {
        Users user = usersRepository.findByUsername(request.getUsername());

        if (user == null || !BCrypt.checkpw(request.getPassword(), user.getPassword())) {
            return ResponseEntity.status(401).body("Identifiants invalides");
        }

        String token = jwtService.generateToken(user.getUsername()); // Générer le token JWT

        // Stocker les informations de l'utilisateur dans la session :
        session.setAttribute("userId", user.getId());
        session.setAttribute("username", user.getUsername());
        session.setAttribute("roleName", user.getRole().getRoleName());

        // Récupérer les valeurs depuis la session (à faire dans un autre controller) :
        Long userId = (Long) session.getAttribute("userId");
        String username = (String) session.getAttribute("username");
        String roleName = (String) session.getAttribute("roleName");

        // Test :
        System.out.println("Session userId: " + userId);
        System.out.println("Session username: " + username);
        System.out.println("Session roleName: " + roleName);

        return ResponseEntity.ok(new LoginResponse(token, user.getId(), user.getUsername(), user.getRole().getRoleName())); // Retourner la réponse avec le token JWT
    }



    /* ========== Tester dans Endpoint : ==========

    POST http://localhost:8080/api/auth/connexion
    Content-Type: application/json

    {
      "username": "admin",
      "password": "1234"
    }

    Si erreur, affiche : Identifiants invalides

    Si ok, affiche :
    {
      "token": "eyJhbGciOi...",
      "userId": 1,
      "username": "admin",
      "role": "Super Admin"
    }


    ========== Tester dans Postman : ==========

    POST http://localhost:8080/api/auth/connexion

    Headers : Content-Type: application/json

    Body, raw, JSON :

    {
      "username": "admin",
      "password": "1234"
    }

    Si erreur, affiche : Identifiants invalides

    Si ok, affiche :
    {
      "token": "eyJhbGciOi...",
      "userId": 1,
      "username": "admin",
      "role": "Super Admin"
    }

    */

}
