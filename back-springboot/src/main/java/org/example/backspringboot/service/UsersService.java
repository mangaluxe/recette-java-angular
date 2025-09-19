package org.example.backspringboot.service;

import org.example.backspringboot.dto.UsersDtoReceive;
import org.example.backspringboot.dto.UsersDtoSend;
import org.example.backspringboot.entity.Role;
import org.example.backspringboot.entity.Users;
import org.example.backspringboot.repository.RoleRepository;
import org.example.backspringboot.repository.UsersRepository;
//import org.springframework.beans.factory.annotation.Autowired;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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
        // Vérifie que le username et l'email n'existent pas déjà :
        if (usersRepository.findByUsername(dto.getUsername()) != null) {
            throw new IllegalArgumentException("Le nom d'utilisateur existe déjà : " + dto.getUsername());
        }

        if (usersRepository.findByEmail(dto.getEmail()) != null) {
            throw new IllegalArgumentException("L'email existe déjà : " + dto.getEmail());
        }

        // Récupère le rôle et on force le roleId à 1 par défaut :
        Role role = roleRepository.findById(1L)
                .orElseThrow(() -> new IllegalArgumentException("Rôle non trouvé"));

        Users user = new Users();
        user.setUsername(dto.getUsername());
//        user.setPassword(dto.getPassword()); // Sans hasher le mot de passe
        String hashedPassword = BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt()); // Hasher le mot de passe avec BCrypt. BCrypt.gensalt(12) : Génère un sel avec un facteur de coût spécifié (12) au lieu de 10 par défaut
        user.setPassword(hashedPassword);
        user.setEmail(dto.getEmail());
        user.setCreatedAt(LocalDateTime.now());
        user.setRole(role);

        Users savedUser = usersRepository.save(user);

        return convertToDtoSend(savedUser);
    }

    // ----- Update -----

    /**
     * Modifier un utilisateur (par l'admin)
     */
    public UsersDtoSend updateUser(Long id, UsersDtoReceive dto) {
        Users existingUser = usersRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé avec l'id : " + id));

        // Met à jour les champs
        existingUser.setUsername(dto.getUsername());
//        existingUser.setPassword(dto.getPassword()); // Sans hasher
        if (dto.getPassword() != null && !dto.getPassword().isBlank() && !BCrypt.checkpw(dto.getPassword(), existingUser.getPassword())) { // Re-hash seulement si un nouveau mot de passe est fourni et différent
            existingUser.setPassword(BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt()));
        }
        existingUser.setEmail(dto.getEmail());

        Role role = roleRepository.findById(dto.getRoleId())
                .orElseThrow(() -> new IllegalArgumentException("Rôle non trouvé"));

        existingUser.setRole(role);

        Users savedUser = usersRepository.save(existingUser);

        return convertToDtoSend(savedUser);
    }


    /**
     * Modifier un utilisateur (par lui-même) : uniquement email et mot de passe
     */
    public UsersDtoSend updateUserProfile(Long id, UsersDtoReceive dto) {
        Users user = usersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        // Vérifie l’ancien mot de passe si le nouveau mot de passe est fourni
        if (dto.getPassword() != null && !dto.getPassword().isBlank()) {
            if (!BCrypt.checkpw(dto.getOldPassword(), user.getPassword())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Ancien mot de passe incorrect");
            }
            user.setPassword(BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt()));
        }

        // Met à jour l’email
        if (dto.getEmail() != null) {
            user.setEmail(dto.getEmail());
        }

        Users savedUser = usersRepository.save(user);
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
        dto.setRoleName(user.getRole().getRoleName()); // 👈 Pour éviter de devoir récupérer le nom de rôle avec des requêtes supplémentaires
        return dto;
    }


}
