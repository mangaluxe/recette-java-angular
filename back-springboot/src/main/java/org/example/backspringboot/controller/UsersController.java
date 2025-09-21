package org.example.backspringboot.controller;

import jakarta.validation.Valid;
import org.example.backspringboot.dto.ErrorResponse;
import org.example.backspringboot.dto.UsersDtoReceive;
import org.example.backspringboot.dto.UsersDtoSend;
import org.example.backspringboot.entity.Users;
import org.example.backspringboot.service.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user") // URL par défaut : http://localhost:8080/api/user
public class UsersController {

    // ========== Propriétés ==========

    private final UsersService usersService;


    // ========== Constructeur ==========

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }


    // ========== Méthodes ==========

    // ----- Read -----

    /**
     * Récupérer tous les utilisateurs
     */
    @GetMapping // GET http://localhost:8080/api/user
    public List<UsersDtoSend> getAllUsers() {
        return usersService.getAllUsers();
    }

    /**
     * Récupérer un utilisateur par id
     */
    @GetMapping("/{id}") // GET http://localhost:8080/api/user/{id}
    public ResponseEntity<UsersDtoSend> getUserById(@PathVariable Long id) {
        try {
            UsersDtoSend user = usersService.getUserById(id);
            return ResponseEntity.ok(user);
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Récupérer un utilisateur par username
     */
    @GetMapping("/username/{username}") // GET http://localhost:8080/api/user/username/{username}
    public ResponseEntity<UsersDtoSend> getUserByUsername(@PathVariable String username) {
        try {
            UsersDtoSend user = usersService.getUserByUsername(username);
            return ResponseEntity.ok(user);
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }


    // ----- Create -----

    /**
     * Créer un utilisateur
     */
    @PostMapping // POST http://localhost:8080/api/user
//    public ResponseEntity<UsersDtoSend> createUser(@Valid @RequestBody UsersDtoReceive dto) {
    public ResponseEntity<?> createUser(@Valid @RequestBody UsersDtoReceive dto) {
        try {
            UsersDtoSend createdUser = usersService.createUser(dto);
            return ResponseEntity.status(201).body(createdUser);
        }
        catch (IllegalArgumentException e) {
//            return ResponseEntity.badRequest().body(null);
            return ResponseEntity.badRequest().body(new ErrorResponse(e.getMessage())); // On crée une classe "ErrorResponse" dans dto pour gérer pseudo/email déjà utilisé
        }
    }


    // ----- Update -----

    /**
     * Modifier un utilisateur (par l'admin)
     */
    @PutMapping("/{id}") // PUT http://localhost:8080/api/user/{id}
    public ResponseEntity<UsersDtoSend> updateUser(@PathVariable Long id, @RequestBody UsersDtoReceive dto) {
        try {
            UsersDtoSend updatedUser = usersService.updateUser(id, dto);
            return ResponseEntity.ok(updatedUser);
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }


    /**
     * Modifier un utilisateur (par lui-même) : uniquement email et mot de passe
     */
    @PutMapping("/profile/{id}") // PUT http://localhost:8080/api/user/profile/{id}
    public UsersDtoSend updateUserProfile(@PathVariable Long id, @RequestBody UsersDtoReceive dto) {
        return usersService.updateUserProfile(id, dto);
    }


    // ----- Delete -----

    /**
     * Supprimer un utilisateur
     */
    @DeleteMapping("/{id}") // DELETE http://localhost:8080/api/user/{id}
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        try {
            usersService.deleteUser(id);
            return ResponseEntity.noContent().build();
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
