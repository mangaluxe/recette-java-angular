package org.example.backspringboot.dto;

import java.time.LocalDateTime;
import java.util.List;

public class RecipeDtoSend {

    // ========== Propriétés ==========

    private Long id;
    private String title;
    private String description;
    private String instructions;
    private int prepTime;
    private int cookTime;
    private int servings;
    private String image;
    private boolean allowComment;
    private LocalDateTime createdAt;

    private String categoryName;
    private String authorName;

    private List<RecipeIngredientDtoSend> ingredients; // 💡 Pour récupérer les ingrédients liés à la recette


    // ========== Constructeurs ==========

    public RecipeDtoSend() {}

    public RecipeDtoSend(Long id, String title, String description, String instructions,
                         int prepTime, int cookTime, int servings,
                         String image, boolean allowComment, LocalDateTime createdAt,
                         String categoryName, String authorName,
                         List<RecipeIngredientDtoSend> ingredients) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.instructions = instructions;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.servings = servings;
        this.image = image;
        this.allowComment = allowComment;
        this.createdAt = createdAt;
        this.categoryName = categoryName;
        this.authorName = authorName;

        this.ingredients = ingredients;
    }


    // ========== Getters/Setters ==========

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

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

    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String categoryName) { this.categoryName = categoryName; }

    public String getAuthorName() { return authorName; }
    public void setAuthorName(String authorName) { this.authorName = authorName; }

    public List<RecipeIngredientDtoSend> getIngredients() { return ingredients; } // 💡 Pour récupérer les ingrédients liés à la recette
    public void setIngredients(List<RecipeIngredientDtoSend> ingredients) { this.ingredients = ingredients; }

}
