package org.example.backspringboot.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String title;

    private String slug;

    private String description;

    @Column(columnDefinition = "TEXT") // Utilisé pour des textes longs
    private String instructions;

    private int prepTime; // Temps de préparation (min)

    private int cookTime; // Temps de cuisson (min)

    private int servings; // Nombre de personnes

    private String image;

    private boolean allowComment = true;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // Clef étrangère vers `Users`
    private Users user; // Auteur de la recette

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false) // Clef étrangère vers `Category`
    private Category category; // Catégorie de la recette

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecipeIngredient> recipeIngredients; // Relation avec RecipeIngredient

}
