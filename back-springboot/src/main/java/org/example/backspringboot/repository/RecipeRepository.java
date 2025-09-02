package org.example.backspringboot.repository;

import org.example.backspringboot.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    /* Les méthodes automatiquement fournies par Spring Data JPA :
    - CRUD complet : save(), findById(), findAll(), deleteById()...
    - Pagination et tri : findAll(Pageable pageable), findAll(Sort sort).
    */

    List<Recipe> findByUserId(Long userId); // Trouver toutes les recettes d’un utilisateur

    List<Recipe> findByCategoryId(Integer categoryId); // Trouver toutes les recettes d’une catégorie

    List<Recipe> findByTitleContainingIgnoreCase(String title); // Rechercher par titre

}
