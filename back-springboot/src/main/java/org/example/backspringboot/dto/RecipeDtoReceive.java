package org.example.backspringboot.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

public class RecipeDtoReceive {

    // ========== Propriétés ==========

    @NotBlank(message = "Le titre est obligatoire.")
    private String title;

    private String description;

    private String instructions;

    @Positive(message = "Le temps de préparation doit être positif.")
    private int prepTime;

    @Positive(message = "Le temps de cuisson doit être positif.")
    private int cookTime;

    private int servings;

    private String image;

    private boolean allowComment = true;

    private Long categoryId; // ID de la catégorie

    private Long userId; // ID de l'auteur


    // ========== Constructeurs ==========

    public RecipeDtoReceive() {}

    public RecipeDtoReceive(String title, String description, String instructions,
                            int prepTime, int cookTime, int servings, String image,
                            boolean allowComment, Long categoryId, Long userId) {
        this.title = title;
        this.description = description;
        this.instructions = instructions;
        this.prepTime = prepTime;
        this.cookTime = cookTime;
        this.servings = servings;
        this.image = image;
        this.allowComment = allowComment;
        this.categoryId = categoryId;
        this.userId = userId;
    }


    // ========== Getters/Setters ==========

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

    public Long getCategoryId() { return categoryId; }
    public void setCategoryId(Long categoryId) { this.categoryId = categoryId; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

}
