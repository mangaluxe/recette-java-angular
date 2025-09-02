package org.example.backspringboot.service;

import org.example.backspringboot.dto.RecipeIngredientDtoReceive;
import org.example.backspringboot.dto.RecipeIngredientDtoSend;
import org.example.backspringboot.entity.Ingredient;
import org.example.backspringboot.entity.Recipe;
import org.example.backspringboot.entity.RecipeIngredient;
import org.example.backspringboot.repository.IngredientRepository;
import org.example.backspringboot.repository.RecipeIngredientRepository;
import org.example.backspringboot.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeIngredientService {

    // ========== Propriétés ==========

    private final RecipeIngredientRepository recipeIngredientRepository;
    private final RecipeRepository recipeRepository;
    private final IngredientRepository ingredientRepository;


    // ========== Constructeur ==========

    public RecipeIngredientService(RecipeIngredientRepository recipeIngredientRepository,
                                   RecipeRepository recipeRepository,
                                   IngredientRepository ingredientRepository) {
        this.recipeIngredientRepository = recipeIngredientRepository;
        this.recipeRepository = recipeRepository;
        this.ingredientRepository = ingredientRepository;
    }


    // ========== Méthodes ==========

    // ----- Read -----

    public List<RecipeIngredientDtoSend> getIngredientsByRecipe(Long recipeId) {
        return recipeIngredientRepository.findByRecipeId(recipeId).stream()
                .map(ri -> new RecipeIngredientDtoSend(
                        ri.getIngredient().getName(),
                        ri.getQuantity(),
                        ri.getIngredient().getUnit()
                ))
                .toList();
    }


    // ----- Create -----

    public RecipeIngredientDtoSend addIngredientToRecipe(Long recipeId, RecipeIngredientDtoReceive dto) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RuntimeException("Recette non trouvée"));
        Ingredient ingredient = ingredientRepository.findById(dto.getIngredientId())
                .orElseThrow(() -> new RuntimeException("Ingrédient non trouvé"));

        RecipeIngredient recipeIngredient = new RecipeIngredient();
        recipeIngredient.setRecipe(recipe);
        recipeIngredient.setIngredient(ingredient);
        recipeIngredient.setQuantity(dto.getQuantity());

        RecipeIngredient saved = recipeIngredientRepository.save(recipeIngredient);

        return new RecipeIngredientDtoSend(
                saved.getIngredient().getName(),
                saved.getQuantity(),
                saved.getIngredient().getUnit()
        );
    }


    // ----- Update -----

    // ----- Delete -----

    public void removeIngredientFromRecipe(Long id) {
        recipeIngredientRepository.deleteById(id);
    }

}
