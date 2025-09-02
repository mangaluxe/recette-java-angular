package org.example.backspringboot.repository;

import org.example.backspringboot.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long> {

    /* Les méthodes automatiquement fournies par Spring Data JPA :
    - CRUD complet : save(), findById(), findAll(), deleteById()...
    - Pagination et tri : findAll(Pageable pageable), findAll(Sort sort).
    */

    Users findByUsername(String username);

    Users findByEmail(String email);

}
