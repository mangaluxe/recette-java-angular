package org.example.backspringboot.repository;

import org.example.backspringboot.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    /* Les méthodes automatiquement fournies par Spring Data JPA :
    - CRUD complet : save(), findById(), findAll(), deleteById()...
    - Pagination et tri : findAll(Pageable pageable), findAll(Sort sort).
    */

    Role findByRoleName(String roleName); // Spring Data JPA va automatiquement créer la requête SQL correspondante (SELECT * FROM role WHERE role_name = ?)

    List<Role> findAllByOrderByIdAsc(); // 👍 Renvoie id de rôle dans l'ordre, sinon dans le désordre

}
