package org.example.backspringboot.repository;

import org.example.backspringboot.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {

    /* Les méthodes automatiquement fournies par Spring Data JPA :
    - CRUD complet : save(), findById(), findAll(), deleteById()...
    - Pagination et tri : findAll(Pageable pageable), findAll(Sort sort).
    */

    Ingredient findByName(String name);

    Optional<Ingredient> findByNameAndUnit(String name, String unit);

    Optional<Ingredient> findByNameIgnoreCaseAndUnitIgnoreCase(String name, String unit);

}