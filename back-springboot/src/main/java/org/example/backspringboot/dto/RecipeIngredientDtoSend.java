package org.example.backspringboot.dto;

public class RecipeIngredientDtoSend {

    // ========== Propriétés ==========

    private String ingredientName;
    private int quantity;
    private String unit;


    // ========== Constructeurs ==========

    public RecipeIngredientDtoSend() {}

    public RecipeIngredientDtoSend(String ingredientName, int quantity, String unit) {
        this.ingredientName = ingredientName;
        this.quantity = quantity;
        this.unit = unit;
    }


    // ========== Getters/Setters ==========

    public String getIngredientName() { return ingredientName; }
    public void setIngredientName(String ingredientName) { this.ingredientName = ingredientName; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }

}
