package org.example.backspringboot.entity;

import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

//@Data
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
@Table(name = "recipe", uniqueConstraints = {
        @UniqueConstraint(columnNames = "slug")
}) // Nom de table BDD (facultatif si identique). @UniqueConstraint pour que chaque entrée dans la colonne "slug" soit unique
@Entity
public class Recipe {

    // ========== Propriétés ==========

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    // ----- Relation -----

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false) // Clef étrangère vers `Users`
    private Users user; // Auteur de la recette

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false) // Clef étrangère vers `Category`
    private Category category; // Catégorie de la recette

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecipeIngredient> recipeIngredients; // Relation avec RecipeIngredient


    // ========== Constructeurs ==========

    public Recipe() {}

    public Recipe(String title, String slug, String description, String instructions,
                  int prepTime, int cookTime, int servings, String image,
                  boolean allowComment, LocalDateTime createdAt,
                  Users user, Category category) {
        this.title = title;
        this.slug = slug;
        this.description = description;
        this.instructions = instructions;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.servings = servings;
        this.image = image;
        this.allowComment = allowComment;
        this.createdAt = createdAt;
        this.user = user;
        this.category = category;
    }


    // ========== Getters/Setters ==========

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getSlug() { return slug; }
    public void setSlug(String slug) { this.slug = slug; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getInstructions() { return instructions; }
    public void setInstructions(String instructions) { this.instructions = instructions; }

    public int getPrepTime() { return prepTime; }
    public void setPrepTime(int prepTime) { this.prepTime = prepTime; }

    public int getCookTime() { return cookTime; }
    public void setCookTime(int cookTime) { this.cookTime = cookTime; }

    public int getServings() { return servings; }
    public void setServings(int servings) { this.servings = servings; }

    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public boolean isAllowComment() { return allowComment; }
    public void setAllowComment(boolean allowComment) { this.allowComment = allowComment; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public Users getUser() { return user; }
    public void setUser(Users user) { this.user = user; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }

    public List<RecipeIngredient> getRecipeIngredients() { return recipeIngredients; }
    public void setRecipeIngredients(List<RecipeIngredient> recipeIngredients) {
        this.recipeIngredients = recipeIngredients;
    }

}
