package org.example.backspringboot.dto;

/*
UsersDtoSend :

- Utilisé pour envoyer les données d'un utilisateur en réponse (par exemple, après un GET).
- Protège l'intégrité des données sensibles (par exemple, pas de mot de passe).
*/

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersDtoSend {

    private int id;

    private String username;

    // On retire le champ password, car les mots de passe ne doivent jamais être renvoyés dans une réponse API.

    private String email;

    private LocalDateTime createdAt;

//    private LocalDateTime lastLogin;
//
//    private String lastIp;
//
//    private boolean isActive;

    private int roleId;

}
