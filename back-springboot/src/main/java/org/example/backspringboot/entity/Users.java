package org.example.backspringboot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data // Annotation Lombok qui génère automatiquement les getters/setters, toString...
@AllArgsConstructor // Annotation Lombok qui génère un constructeur avec tous les arguments.
@NoArgsConstructor // Annotation Lombok qui génère un constructeur sans arguments.
@Builder
@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID auto-généré
    private int id;

    private String username;

    private String password;

    private String email;

    private LocalDateTime createdAt;

//    private LocalDateTime lastLogin;
//
//    private String lastIp;
//
//    private boolean isActive;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false) // Clef étrangère vers `Role`
    private Role role;
}
