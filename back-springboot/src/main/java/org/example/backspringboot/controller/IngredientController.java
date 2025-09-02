package org.example.backspringboot.controller;

import org.example.backspringboot.dto.IngredientDtoReceive;
import org.example.backspringboot.dto.IngredientDtoSend;
import org.example.backspringboot.service.IngredientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ingredient") // URL par défaut : http://localhost:8080/api/ingredient
public class IngredientController {

    // ========== Propriétés ==========

    private final IngredientService ingredientService;


    // ========== Constructeur ==========

    public IngredientController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }


    // ========== Méthodes ==========

    // ----- Read -----

    @GetMapping
    public List<IngredientDtoSend> getAllIngredients() {
        return ingredientService.getAllIngredients();
    }

    @GetMapping("/{id}")
    public ResponseEntity<IngredientDtoSend> getIngredientById(@PathVariable Long id) {
        return ResponseEntity.ok(ingredientService.getIngredientById(id));
    }


    // ----- Create -----

    @PostMapping
    public ResponseEntity<IngredientDtoSend> createIngredient(@RequestBody IngredientDtoReceive dto) {
        return new ResponseEntity<>(ingredientService.createIngredient(dto), HttpStatus.CREATED);
    }


    // ----- Update -----

    @PutMapping("/{id}")
    public ResponseEntity<IngredientDtoSend> updateIngredient(@PathVariable Long id, @RequestBody IngredientDtoReceive dto) {
        return ResponseEntity.ok(ingredientService.updateIngredient(id, dto));
    }

    // ----- Delete -----

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIngredient(@PathVariable Long id) {
        ingredientService.deleteIngredient(id);
        return ResponseEntity.noContent().build();
    }

}
