package org.example.backspringboot.controller;

import org.example.backspringboot.dto.UsersDtoReceive;
import org.example.backspringboot.dto.UsersDtoSend;
import org.example.backspringboot.entity.Users;
import org.example.backspringboot.service.UsersService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/user/") // URL par défaut : http://localhost:8080/api/users/
public class UsersController {

    // ========== Propriétés ==========

    private final UsersService usersService;

    // ========== Constructeur ==========

    public UsersController(UsersService usersService) {
        this.usersService = usersService;
    }

    // ----- READ ALL -----

    /**
     * Afficher tous
     */
//    @GetMapping // http://localhost:8080/api/user/
//    public ResponseEntity<List<UsersDtoSend>> getAll() {
//        return new ResponseEntity<>(usersService.getAll(), HttpStatus.OK);
//    }

    // ----- READ ONE -----

    /**
     * Afficher un par son id
     */
//    @GetMapping("{id}") // URL : http://localhost:8080/api/user/1
//    public ResponseEntity<UsersDtoSend> getById(@PathVariable int id) {
//        return new ResponseEntity<>(usersService.getById(id), HttpStatus.OK);
//    }

    // ----- CREATE -----

    /**
     * Créer un utilisateur
     */
    @PostMapping // http://localhost:8080/api/user/ en POST
    public ResponseEntity<UsersDtoSend> create(@RequestBody UsersDtoReceive usersDtoReceive) {
        Users users = usersService.create(usersDtoReceive); // Appeler le service pour créer l'utilisateur

        UsersDtoSend usersDtoSend = new UsersDtoSend(
            users.getId(),
            users.getUsername(),
            users.getEmail(),
            users.getCreatedAt(),
            users.getRole().getId() // Récupérer l'ID du rôle ici
        );

        return new ResponseEntity<>(usersDtoSend, HttpStatus.CREATED); // Retourner la réponse HTTP avec le statut 201 Created
    }

    /* Test dans Endpoint :
      "username": "1234",
      "password": "12345678",
      "email": "moi@laposte.net",
      "createdAt": "2024-11-24T18:02:00",
      "roleId": 0
     */

    // ----- UPDATE -----

//    @PutMapping("{id}")
//    public ResponseEntity<UsersDtoSend> update(@PathVariable int id, @RequestBody UsersDtoReceive usersDtoReceive) {
//        return new ResponseEntity<>(usersService.update(id, usersDtoReceive), HttpStatus.OK);
//    }

    // ----- DELETE -----

//    @DeleteMapping("{id}")
//    public ResponseEntity<Void> deleteById(@PathVariable int id) {
//        usersService.deleteById(id);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
}
