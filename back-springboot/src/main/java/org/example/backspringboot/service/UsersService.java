package org.example.backspringboot.service;

import org.example.backspringboot.dto.UsersDtoReceive;
import org.example.backspringboot.entity.Role;
import org.example.backspringboot.entity.Users;
import org.example.backspringboot.repository.RoleRepository;
import org.example.backspringboot.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UsersService {

    // ========== Propriétés ==========

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private RoleRepository roleRepository;

    // ========== Constructeur ==========

    // ========== Méthodes ==========

    // ----- Read -----

    /**
     * Obtenir tous les utilisateurs
     */
    public List<Users> getAll() {
        return (List<Users>) usersRepository.findAll();
    }

    /**
     * Obtenir un utilisateur par son id
     */
    public Users getById(int id) {
        return usersRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Utilisateur " + id + " introuvable"));
    }

    // ----- Create -----

    /**
     * Créer un utilisateur
     */
    public Users create(UsersDtoReceive usersDtoReceive) {
        // Récupérer le rôle à partir de l'ID du rôle
        Role role = roleRepository.findById(usersDtoReceive.getRoleId())
                .orElseThrow(() -> new RuntimeException("Role not found"));

        Users user = Users.builder()
                .username(usersDtoReceive.getUsername())
                .email(usersDtoReceive.getEmail())
                .password(usersDtoReceive.getPassword())
                .createdAt(usersDtoReceive.getCreatedAt()) // Ajout de createdAt si nécessaire
                .role(role) // Associer le rôle récupéré à l'utilisateur
                .build();

        return usersRepository.save(user);
    }

    // ----- Update -----

    /**
     * Modifier un utilisateur
     */


    // ----- Delete -----

    /**
     * Supprimer un utilisateur
     */
    public void deleteById(int id) {
        if (!usersRepository.existsById(id)) {
            throw new NoSuchElementException("Utilisateur " + id + " inexistant");
        }
        usersRepository.deleteById(id);
    }
}
