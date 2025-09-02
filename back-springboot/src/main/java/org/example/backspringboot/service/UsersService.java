package org.example.backspringboot.service;

import org.example.backspringboot.dto.UsersDtoReceive;
import org.example.backspringboot.dto.UsersDtoSend;
import org.example.backspringboot.entity.Role;
import org.example.backspringboot.entity.Users;
import org.example.backspringboot.repository.RoleRepository;
import org.example.backspringboot.repository.UsersRepository;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsersService {

    // ========== Propriétés ==========

    private final UsersRepository usersRepository;
    private final RoleRepository roleRepository;

    // ========== Constructeur ==========

    public UsersService(UsersRepository usersRepository, RoleRepository roleRepository) {
        this.usersRepository = usersRepository;
        this.roleRepository = roleRepository;
    }

    // ========== Méthodes ==========

    // ----- Read -----

    /**
     * Récupérer tous les utilisateurs
     */
    public List<UsersDtoSend> getAllUsers() {
        return usersRepository.findAll().stream()
                .map(this::convertToDtoSend)
                .toList();
    }

    /**
     * Récupérer un utilisateur par id
     */
    public UsersDtoSend getUserById(Long id) {
        Users user = usersRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé avec l'id : " + id));
        return convertToDtoSend(user);
    }

    /**
     * Récupérer un utilisateur par username
     */
    public UsersDtoSend getUserByUsername(String username) {
        Users user = usersRepository.findByUsername(username);
        if (user == null) throw new IllegalArgumentException("Utilisateur non trouvé avec le username : " + username);
        return convertToDtoSend(user);
    }

    // ----- Create -----

    /**
     * Créer un utilisateur
     */
    public UsersDtoSend createUser(UsersDtoReceive dto) {
        // Vérifie que le username et l'email n'existent pas déjà
        if (usersRepository.findByUsername(dto.getUsername()) != null) {
            throw new IllegalArgumentException("Le nom d'utilisateur existe déjà : " + dto.getUsername());
        }

        if (usersRepository.findByEmail(dto.getEmail()) != null) {
            throw new IllegalArgumentException("L'email existe déjà : " + dto.getEmail());
        }

        // Récupère le rôle
        Role role = roleRepository.findById(dto.getRoleId())
                .orElseThrow(() -> new IllegalArgumentException("Rôle non trouvé avec l'id : " + dto.getRoleId()));

        Users user = new Users();
        user.setUsername(dto.getUsername());
        user.setPassword(dto.getPassword()); // idéalement, hasher le mot de passe ici
        user.setEmail(dto.getEmail());
        user.setCreatedAt(LocalDateTime.now());
        user.setRole(role);

        Users savedUser = usersRepository.save(user);

        return convertToDtoSend(savedUser);
    }

    // ----- Update -----

    /**
     * Modifier un utilisateur
     */
    public UsersDtoSend updateUser(Long id, UsersDtoReceive dto) {
        Users existingUser = usersRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé avec l'id : " + id));

        // Met à jour les champs
        existingUser.setUsername(dto.getUsername());
        existingUser.setPassword(dto.getPassword()); // hasher idéalement
        existingUser.setEmail(dto.getEmail());

        Role role = roleRepository.findById(dto.getRoleId())
                .orElseThrow(() -> new IllegalArgumentException("Rôle non trouvé avec l'id : " + dto.getRoleId()));

        existingUser.setRole(role);

        Users savedUser = usersRepository.save(existingUser);

        return convertToDtoSend(savedUser);
    }


    // ----- Delete -----

    /**
     * Supprimer un utilisateur
     */
    public void deleteUser(Long id) {
        Users existingUser = usersRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé avec l'id : " + id));
        usersRepository.delete(existingUser);
    }


    // ----- Utilitaires -----

    /**
     * Convertir User → UsersDtoSend
     */
    private UsersDtoSend convertToDtoSend(Users user) {
        UsersDtoSend dto = new UsersDtoSend();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setCreatedAt(user.getCreatedAt());
        dto.setRoleId(user.getRole().getId());
        return dto;
    }


}
