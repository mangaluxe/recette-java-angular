package org.example.backspringboot.controller;

import org.example.backspringboot.dto.RecipeDtoReceive;
import org.example.backspringboot.dto.RecipeDtoSend;
import org.example.backspringboot.service.RecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipe") // URL par défaut : http://localhost:8080/api/recipe
public class RecipeController {

    // ========== Propriétés ==========

    private final RecipeService recipeService;


    // ========== Constructeur ==========

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }


    // ========== Méthodes ==========

    // ----- Read -----

    @GetMapping
    public List<RecipeDtoSend> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecipeDtoSend> getRecipeById(@PathVariable Long id) {
        return ResponseEntity.ok(recipeService.getRecipeById(id));
    }


    // ----- Create -----

    @PostMapping
    public ResponseEntity<RecipeDtoSend> createRecipe(@RequestBody RecipeDtoReceive dto) {
        return new ResponseEntity<>(recipeService.createRecipe(dto), HttpStatus.CREATED);
    }


    // ----- Update -----

    @PutMapping("/{id}")
    public ResponseEntity<RecipeDtoSend> updateRecipe(@PathVariable Long id, @RequestBody RecipeDtoReceive dto) {
        return ResponseEntity.ok(recipeService.updateRecipe(id, dto));
    }


    // ----- Delete -----

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecipe(@PathVariable Long id) {
        recipeService.deleteRecipe(id);
        return ResponseEntity.noContent().build();
    }


    // ========================================

    /* 1️⃣ Créer une recette dans Endpoint :

    POST http://localhost:8080/api/recipe/
    Content-Type: application/json

    {
      "title": "Pizza Margherita",
      "description": "Pizza classique italienne avec tomate, mozzarella et basilic",
      "instructions": "Préchauffer le four, étaler la pâte, ajouter garniture...\nMettre la pizza dans le four.\nAttendre.\nSortir.",
      "prepTime": 15,
      "cookTime": 20,
      "servings": 2,
      "image": "pizza.jpg",
      "allowComment": true,
      "categoryId": 1,
      "userId": 1,
      "ingredients": [
        {
           "ingredientName": "Mozzarella",
           "unit": "g",
           "quantity": 150
         },
        {
          "ingredientName": "Tomates fraîches",
          "unit": "pièce",
          "quantity": 3
        },
        {
          "ingredientId": 1,
          "quantity": 200
        }
      ]
    }

    2️⃣ Récupérer la recette avec ses ingrédients

    GET http://localhost:8080/api/recipe/1

    Affiche :

    {
      "id": 6,
      "title": "Pizza Margherita",
      "description": "Pizza classique italienne avec tomate, mozzarella et basilic",
      "instructions": "Préchauffer le four, étaler la pâte, ajouter garniture...\nMettre la pizza dans le four.\nAttendre.\nSortir.",
      "prepTime": 15,
      "cookTime": 20,
      "servings": 2,
      "image": "pizza.jpg",
      "allowComment": true,
      "createdAt": "2025-09-11T15:16:42",
      "categoryName": "Entrée",
      "authorName": "admin",
      "ingredients": [
        {
          "name": "Mozzarella",
          "quantity": 150,
          "unit": "g"
        },
        {
          "name": "Tomates fraîches",
          "quantity": 3,
          "unit": "pièce"
        },
        {
          "name": "Eau",
          "quantity": 200,
          "unit": "ml"
        }
      ]
    }

    */
}
