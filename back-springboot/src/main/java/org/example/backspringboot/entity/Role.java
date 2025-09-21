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

import java.util.List;

//@Data // Pour lombok
//@AllArgsConstructor // Pour lombok
//@NoArgsConstructor // Pour lombok
//@Builder // Pour lombok
@Entity
@Table(name = "role") // Nom de table BDD (facultatif si identique)
public class Role {

    // ========== Propriétés ==========

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String roleName;

    // ----- Relation -----

    // 👉 Relation @OneToMany (Relation inverse), un rôle peut avoir plusieurs utilisateurs
//    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true) // CascadeType.ALL signifie que si on supprime un rôle, tous les Users liés seront supprimés. Donc à éviter ici
    @OneToMany(mappedBy = "role")
    private List<Users> users; // Relation avec les utilisateurs

    /* On obtiendra dans la BDD :
    - Colonnes dans users : id, username, password, email, created_at, role_id
    - Colonnes dans role : id, role_name
    - Avec la clé étrangère users.role_id → role.id
    */


    // ========== Constructeurs ==========

    public Role() {
        // Constructeur par défaut requis par JPA
    }

    public Role(String roleName) {
        this.roleName = roleName;
    }

    public Role(Long id, String roleName, List<Users> users) {
        this.id = id;
        this.roleName = roleName;
        this.users = users;
    }


    // ========== Getters/Setters ==========

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public List<Users> getUsers() {
        return users;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }


    // ========== Méthodes utilitaires (optionnel) ==========

    public void addUser(Users user) {
        users.add(user);
        user.setRole(this);
    }

    public void removeUser(Users user) {
        users.remove(user);
        user.setRole(null);
    }

}
