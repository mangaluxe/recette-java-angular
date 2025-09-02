package org.example.backspringboot.dto;

import jakarta.validation.constraints.NotBlank;

public class CategoryDtoReceive {

    // ========== Propriétés ==========

    @NotBlank(message = "Le nom de la catégorie est obligatoire.")
    private String name;


    // ========== Constructeurs ==========

    public CategoryDtoReceive() {}

    public CategoryDtoReceive(String name) {
        this.name = name;
    }


    // ========== Getters/Setters ==========

    public String getName() {return name;}
    public void setName(String name) {this.name = name;}

}