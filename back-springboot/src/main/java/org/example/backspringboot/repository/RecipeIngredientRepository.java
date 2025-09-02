package org.example.backspringboot.repository;

import org.example.backspringboot.entity.RecipeIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeIngredientRepository  extends JpaRepository<RecipeIngredient, Long> {

    /* Les méthodes automatiquement fournies par Spring Data JPA :
    - CRUD complet : save(), findById(), findAll(), deleteById()...
    - Pagination et tri : findAll(Pageable pageable), findAll(Sort sort).
    */

    List<RecipeIngredient> findByRecipeId(Long recipeId); // Retrouver tous les ingrédients d’une recette

    List<RecipeIngredient> findByIngredientId(Long ingredientId); // Retrouver toutes les recettes qui utilisent un ingrédient donné

}
