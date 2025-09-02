package org.example.backspringboot.controller;

import org.example.backspringboot.dto.RecipeIngredientDtoReceive;
import org.example.backspringboot.dto.RecipeIngredientDtoSend;
import org.example.backspringboot.service.RecipeIngredientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipe/{recipeId}/ingredients") // URL par défaut : http://localhost:8080/api/recipe/{recipeId}/ingredients
public class RecipeIngredientController {

    // ========== Propriétés ==========

    private final RecipeIngredientService recipeIngredientService;


    // ========== Constructeur ==========

    public RecipeIngredientController(RecipeIngredientService recipeIngredientService) {
        this.recipeIngredientService = recipeIngredientService;
    }


    // ========== Méthodes ==========

    // ----- Read -----

    @GetMapping
    public List<RecipeIngredientDtoSend> getIngredientsByRecipe(@PathVariable Long recipeId) {
        return recipeIngredientService.getIngredientsByRecipe(recipeId);
    }


    // ----- Create -----

    @PostMapping
    public ResponseEntity<RecipeIngredientDtoSend> addIngredientToRecipe(
            @PathVariable Long recipeId,
            @RequestBody RecipeIngredientDtoReceive dto) {
        return new ResponseEntity<>(recipeIngredientService.addIngredientToRecipe(recipeId, dto), HttpStatus.CREATED);
    }


    // ----- Delete -----

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeIngredientFromRecipe(@PathVariable Long id) {
        recipeIngredientService.removeIngredientFromRecipe(id);
        return ResponseEntity.noContent().build();
    }

}
