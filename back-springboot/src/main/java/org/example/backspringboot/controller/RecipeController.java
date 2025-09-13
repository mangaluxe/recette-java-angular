package org.example.backspringboot.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.backspringboot.dto.RecipeDtoReceive;
import org.example.backspringboot.dto.RecipeDtoSend;
import org.example.backspringboot.service.RecipeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    /**
     * Afficher toutes les recettes
     */
    @GetMapping // GET http://localhost:8080/api/recipe
    public List<RecipeDtoSend> getAllRecipes() {
        return recipeService.getAllRecipes();
    }


    /**
     * Afficher une recette
     */
    @GetMapping("/{id}") // GET http://localhost:8080/api/recipe/{id}
    public ResponseEntity<RecipeDtoSend> getRecipeById(@PathVariable Long id) {
        return ResponseEntity.ok(recipeService.getRecipeById(id));
    }


    // ----- Create -----

//    @PostMapping // POST http://localhost:8080/api/recipe
//    public ResponseEntity<RecipeDtoSend> createRecipe(@RequestBody RecipeDtoReceive dto) {
//        return new ResponseEntity<>(recipeService.createRecipe(dto), HttpStatus.CREATED);
//    }

    /**
     * Créer une recette
     */
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE) // POST http://localhost:8080/api/recipe
    public RecipeDtoSend createRecipe(
            @RequestPart("recipe") String recipeJson,
            @RequestPart(value = "file", required = false) MultipartFile file
    ) throws IOException {
        RecipeDtoReceive dto = new ObjectMapper().readValue(recipeJson, RecipeDtoReceive.class);
        return recipeService.createRecipe(dto, file);
    }


    // ----- Update -----

//    @PutMapping("/{id}") // PUT http://localhost:8080/api/recipe/{id}
//    public ResponseEntity<RecipeDtoSend> updateRecipe(@PathVariable Long id, @RequestBody RecipeDtoReceive dto) {
//        return ResponseEntity.ok(recipeService.updateRecipe(id, dto));
//    }

    /**
     * Modifier une recette
     */
    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE) // PUT http://localhost:8080/api/recipe/{id}
    public RecipeDtoSend updateRecipe(
            @PathVariable Long id,
            @RequestPart("recipe") String recipeJson,
            @RequestPart(value = "file", required = false) MultipartFile file
    ) throws IOException {
        RecipeDtoReceive dto = new ObjectMapper().readValue(recipeJson, RecipeDtoReceive.class);
        return recipeService.updateRecipe(id, dto, file);
    }

    // ----- Delete -----

    /**
     * Supprimer une recette
     */
    @DeleteMapping("/{id}") // DELETE http://localhost:8080/api/recipe/{id}
    public ResponseEntity<Void> deleteRecipe(@PathVariable Long id) {
        recipeService.deleteRecipe(id);
        return ResponseEntity.noContent().build();
    }


    // ========================================

    /* 1️⃣ Créer une recette dans Postman :

    POST http://localhost:8080/api/recipe

    Onglet Body. Sélectionne form-data.

    Ajouter les champs :

    - Champ recipe → dans la colonne Key, écris recipe. Dans la colonne Type (à droite), sélectionne Text. Colle ceci :

{
  "title": "Pizza Margherita",
  "description": "Pizza classique italienne",
  "instructions": "Préparer...\nCuire...\nServir.",
  "prepTime": 15,
  "cookTime": 20,
  "servings": 2,
  "image": null,
  "allowComment": true,
  "categoryId": 1,
  "userId": 1,
  "ingredients": [
    { "ingredientName": "Mozzarella", "unit": "g", "quantity": 150 },
    { "ingredientName": "Tomates fraîches", "unit": "pièce", "quantity": 3 },
    { "ingredientId": 1, "quantity": 200 }
  ]
}

    - Champ file → dans Key, écris file. Dans Type, choisis File. Clique pour sélectionner une image (ex. pizza.jpg).

    Envoyer la requête en cliquant sur Send.


    2️⃣ Récupérer la recette avec ses ingrédients dans Endpoint :

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


    3️⃣ Modifier une recette dans Postman :

    Même chose que pour ajouter, mais faire :

    PUT http://localhost:8080/api/recipe/1

    */
}
