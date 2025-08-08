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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersDtoReceive {

    @NotBlank(message = "Pseudo obligatoire.")
    @Size(min = 3, max = 50, message = "Pseudo entre 3 et 50 caractères")
    private String username;

    @NotBlank(message = "Mot de passe obligatoire.")
    @Size(min = 4, message = "Minimum 4 caractères.")
    private String password;

    @NotBlank(message = "Email is required.")
    @Email(message = "Invalid email format.")
    private String email;

    private LocalDateTime createdAt;

//    private LocalDateTime lastLogin;
//
//    private String lastIp;
//
//    private boolean isActive;

    private int roleId;

}
