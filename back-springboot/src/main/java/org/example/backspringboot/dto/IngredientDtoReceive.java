package org.example.backspringboot.dto;

import jakarta.validation.constraints.NotBlank;

public class IngredientDtoReceive {

    // ========== Propriétés ==========

    @NotBlank(message = "Le nom de l'ingrédient est obligatoire.")
    private String name;

    private String unit;


    // ========== Constructeurs ==========

    public IngredientDtoReceive() {}

    public IngredientDtoReceive(String name, String unit) {
        this.name = name;
        this.unit = unit;
    }


    // ========== Getters/Setters ==========

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }

}
