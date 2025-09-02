package org.example.backspringboot.dto;

/*
UsersDtoSend :

- Utilisé pour envoyer les données d'un utilisateur en réponse (par exemple, après un GET).
- Protège l'intégrité des données sensibles (par exemple, pas de mot de passe).
*/

//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class UsersDtoSend {

    // ========== Propriétés ==========

    private Long id;

    private String username;

    // On retire le champ password, car les mots de passe ne doivent jamais être renvoyés dans une réponse API.

    private String email;

    private LocalDateTime createdAt;

//    private LocalDateTime lastLogin;
//
//    private String lastIp;
//
//    private boolean isActive;

    private Long roleId;


    // ========== Constructeurs ==========

    public UsersDtoSend() {
    }

    public UsersDtoSend(Long id, String username, String email, LocalDateTime createdAt, Long roleId) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.createdAt = createdAt;
        this.roleId = roleId;
    }


    // ========== Getters/Setters ==========

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

}
