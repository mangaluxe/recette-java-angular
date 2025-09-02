package org.example.backspringboot.dto;

/*
UsersDtoReceive :

- Utilisé pour recevoir les données d'une requête (par exemple, lors d'une création ou d'une mise à jour).
- Inclut les annotations de validation pour garantir que seules des données valides sont acceptées.
*/

// Validation à mettre dans DtoReceive uniquement

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;

//import java.time.LocalDateTime;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class UsersDtoReceive {

    // ========== Propriétés ==========

    @NotBlank(message = "Pseudo obligatoire.")
    @Size(min = 3, max = 50, message = "Pseudo entre 3 et 50 caractères")
    private String username;

    @NotBlank(message = "Mot de passe obligatoire.")
    @Size(min = 4, message = "Minimum 4 caractères.")
    private String password;

    @NotBlank(message = "Email obligatoire.")
    @Email(message = "Format email invalide.")
    private String email;

//    private LocalDateTime createdAt; // Pas besoin de createdAt dans UsersDtoReceive, car cette valeur est générée automatiquement côté serveur lors de la création de l’utilisateur (LocalDateTime.now()) et ne doit pas être fournie par le client

//    private LocalDateTime lastLogin;
//
//    private String lastIp;
//
//    private boolean isActive;

    private Long roleId;


    // ========== Constructeurs ==========

    public UsersDtoReceive() {
    }

    public UsersDtoReceive(String username, String password, String email, Long roleId) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.roleId = roleId;
    }


    // ========== Getters/Setters ==========

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

}
