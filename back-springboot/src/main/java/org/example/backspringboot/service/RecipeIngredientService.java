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
        System.out.println("📥 DTO reçu : id=" + dto.getIngredientId() +
                ", name=" + dto.getIngredientName() +
                ", unit=" + dto.getUnit() +
                ", qty=" + dto.getQuantity());

        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RuntimeException("Recette non trouvée"));

        // Ajouter dans IngredientRepository : Optional<Ingredient> findByNameIgnoreCaseAndUnitIgnoreCase(String name, String unit);

        // Vérifie si l’ingrédient existe déjà (Pour éviter d'insérer plusieurs fois même ingrédient) :
        Ingredient ingredient;
        if (dto.getIngredientId() != null) { // Cas ingrédient existant
            ingredient = ingredientRepository.findById(dto.getIngredientId())
                    .orElseThrow(() -> new RuntimeException("Ingrédient non trouvé"));
        }
        else if (dto.getIngredientName() != null && dto.getUnit() != null) { // Cas nouvel ingrédient ou réutilisation
            ingredient = ingredientRepository
                    .findByNameIgnoreCaseAndUnitIgnoreCase(dto.getIngredientName().trim(), dto.getUnit().trim())
                    .orElseGet(() -> {
                        System.out.println("Nouvel ingrédient créé : " + dto.getIngredientName() + " (" + dto.getUnit() + ")");
                        Ingredient newIngredient = new Ingredient();
                        newIngredient.setName(dto.getIngredientName().trim());
                        newIngredient.setUnit(dto.getUnit().trim());
                        return ingredientRepository.save(newIngredient);
                    });
        }
        else {
            throw new RuntimeException("Ni ingredientId ni ingredientName fournis !");
        }

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
