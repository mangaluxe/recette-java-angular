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

    private String oldPassword; // 💡 Ancien mot de passe pour vérification

    @NotBlank(message = "Email obligatoire.")
    @Email(message = "Format email invalide.")
    private String email;

    // Pas besoin de createdAt dans UsersDtoReceive, car cette valeur est générée automatiquement côté serveur lors de la création de l’utilisateur avec LocalDateTime.now()

//    private LocalDateTime lastLogin;
//
//    private String lastIp;
//
//    private boolean isActive;

    private Long roleId; // Besoin pour update, mais pas pour create (qui force roleId à 1 par défaut)


    // ========== Constructeurs ==========

    public UsersDtoReceive() {
    }

    public UsersDtoReceive(String username, String password, String oldPassword,  String email, Long roleId) {
        this.username = username;
        this.password = password;
        this.oldPassword = oldPassword;
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

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
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
