package org.example.backspringboot.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
@Entity
@Table(name = "recipe_ingredient") // Nom de table BDD (facultatif si identique)
public class RecipeIngredient {

    // ========== Propriétés ==========

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // ----- Relation -----

    @ManyToOne
    @JoinColumn(name = "recipe_id", nullable = false)
    @JsonBackReference // <-- correspond à Recipe
    private Recipe recipe; // Relation avec Recipe

    @ManyToOne
    @JoinColumn(name = "ingredient_id", nullable = false)
    private Ingredient ingredient; // Relation avec Ingredient

    @Column(nullable = false)
    private int quantity; // Quantité de cet ingrédient dans la recette


    // ========== Constructeurs ==========

    public RecipeIngredient() {}

    public RecipeIngredient(Recipe recipe, Ingredient ingredient, int quantity) {
        this.recipe = recipe;
        this.ingredient = ingredient;
        this.quantity = quantity;
    }


    // ========== Getters/Setters ==========

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Recipe getRecipe() { return recipe; }
    public void setRecipe(Recipe recipe) { this.recipe = recipe; }

    public Ingredient getIngredient() { return ingredient; }
    public void setIngredient(Ingredient ingredient) { this.ingredient = ingredient; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

}