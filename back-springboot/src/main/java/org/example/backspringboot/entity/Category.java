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
@Table(name = "category") // Nom de table BDD (facultatif si identique)
public class Category {

    // ========== Propriétés ==========

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // ----- Relation -----

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Recipe> recipes; // Relation avec les recettes


    // ========== Constructeurs ==========

    public Category() {}

    public Category(String name) {
        this.name = name;
    }

    public Category(Long id, String name, List<Recipe> recipes) {
        this.id = id;
        this.name = name;
        this.recipes = recipes;
    }


    // ========== Getters/Setters ==========

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public List<Recipe> getRecipes() { return recipes; }

    public void setRecipes(List<Recipe> recipes) { this.recipes = recipes; }

}
