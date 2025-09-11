package org.example.backspringboot.dto;

import jakarta.validation.constraints.Positive;

public class RecipeIngredientDtoReceive {

    // ========== Propriétés ==========

    // Cas 1 : ingrédient existant
    private Long ingredientId;

    // Cas 2 : nouvel ingrédient
    private String ingredientName;
    private String unit;

    @Positive(message = "La quantité doit être positive.")
    private int quantity;


    // ========== Constructeurs ==========

    public RecipeIngredientDtoReceive() {}

    // Pour ingrédient existant
    public RecipeIngredientDtoReceive(Long ingredientId, int quantity) {
        this.ingredientId = ingredientId;
        this.quantity = quantity;
    }

    // Pour nouvel ingrédient
    public RecipeIngredientDtoReceive(String ingredientName, String unit, int quantity) {
        this.ingredientName = ingredientName;
        this.unit = unit;
        this.quantity = quantity;
    }


    // ========== Getters/Setters ==========

    public Long getIngredientId() { return ingredientId; }
    public void setIngredientId(Long ingredientId) { this.ingredientId = ingredientId; }

    public String getIngredientName() { return ingredientName; }
    public void setIngredientName(String ingredientName) { this.ingredientName = ingredientName; }

    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

}
