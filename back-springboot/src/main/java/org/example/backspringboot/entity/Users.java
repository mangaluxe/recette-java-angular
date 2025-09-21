package org.example.backspringboot.entity;

/* L’ordre idéal dans un projet spring boot :

- entity → structure de la base
- dto → validation et transfert de données
- repository → accès aux données
- service → logique métier / mappage DTO ↔ Entity
- controller → API REST
*/

import jakarta.persistence.*;
//import lombok.Builder;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

//@Data // Annotation Lombok qui génère automatiquement les getters/setters, toString...
//@AllArgsConstructor // Annotation Lombok qui génère un constructeur avec tous les arguments.
//@NoArgsConstructor // Annotation Lombok qui génère un constructeur sans arguments.
//@Builder
@Entity
@Table(name = "users") // user est un mot réservé en postgresql, on va mettre "users"
public class Users {

    // ========== Propriétés ==========

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ID auto-généré
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

//    private LocalDateTime lastLogin;
//
//    private String lastIp;
//
//    private boolean isActive;


    // ----- Relation -----

    // 👉 Relation ManyToOne : plusieurs utilisateurs peuvent avoir le même rôle
    @ManyToOne(fetch = FetchType.LAZY) // Lazy pour éviter de charger le rôle inutilement
    @JoinColumn(name = "role_id", nullable = false) // Clef étrangère vers `Role`
    private Role role;

    /* On obtiendra dans la BDD :
    - Colonnes dans users : id, username, password, email, created_at, role_id
    - Colonnes dans role : id, role_name
    - Avec la clé étrangère users.role_id → role.id
    */


    // ========== Constructeurs ==========

    public Users() {
        // requis par JPA
    }

    public Users(String username, String password, String email, Role role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.createdAt = LocalDateTime.now();
    }

    public Users(Long id, String username, String password, String email, LocalDateTime createdAt, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.createdAt = createdAt;
        this.role = role;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

}
