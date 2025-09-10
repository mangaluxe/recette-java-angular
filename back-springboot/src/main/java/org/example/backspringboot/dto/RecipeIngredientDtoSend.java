package org.example.backspringboot.dto;

public class RecipeIngredientDtoSend {

    // ========== Propriétés ==========

    private String name;
    private int quantity;
    private String unit;


    // ========== Constructeurs ==========

    public RecipeIngredientDtoSend() {}

    public RecipeIngredientDtoSend(String name, int quantity, String unit) {
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
    }


    // ========== Getters/Setters ==========

    public String getName() { return name; }
    public void setName(String ingredientName) { this.name = name; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }

}
