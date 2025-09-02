package org.example.backspringboot.dto;

import jakarta.validation.constraints.Positive;

public class RecipeIngredientDtoReceive {

    // ========== Propriétés ==========

    private Long ingredientId;

    @Positive(message = "La quantité doit être positive.")
    private int quantity;


    // ========== Constructeurs ==========

    public RecipeIngredientDtoReceive() {}

    public RecipeIngredientDtoReceive(Long ingredientId, int quantity) {
        this.ingredientId = ingredientId;
        this.quantity = quantity;
    }


    // ========== Getters/Setters ==========

    public Long getIngredientId() { return ingredientId; }
    public void setIngredientId(Long ingredientId) { this.ingredientId = ingredientId; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

}
