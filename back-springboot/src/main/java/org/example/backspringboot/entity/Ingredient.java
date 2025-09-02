package org.example.backspringboot.entity;

import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;

import java.util.List;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
@Entity
@Table(name = "ingredient") // Nom de table BDD (facultatif si identique)
public class Ingredient {

    // ========== Propriétés ==========

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String unit; // Par exemple : "grammes", "ml", etc.

    // ----- Relation -----

    @OneToMany(mappedBy = "ingredient", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecipeIngredient> recipeIngredients; // Relation avec RecipeIngredient


    // ========== Constructeurs ==========

    public Ingredient() {}

    public Ingredient(String name, String unit) {
        this.name = name;
        this.unit = unit;
    }

    public Ingredient(Long id, String name, String unit, List<RecipeIngredient> recipeIngredients) {
        this.id = id;
        this.name = name;
        this.unit = unit;
        this.recipeIngredients = recipeIngredients;
    }


    // ========== Getters/Setters ==========

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getUnit() { return unit; }

    public void setUnit(String unit) { this.unit = unit; }

    public List<RecipeIngredient> getRecipeIngredients() { return recipeIngredients; }

    public void setRecipeIngredients(List<RecipeIngredient> recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }

}
