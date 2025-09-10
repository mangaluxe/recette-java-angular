package org.example.backspringboot.service;

import org.example.backspringboot.dto.RecipeDtoReceive;
import org.example.backspringboot.dto.RecipeDtoSend;
import org.example.backspringboot.dto.RecipeIngredientDtoReceive;
import org.example.backspringboot.dto.RecipeIngredientDtoSend;
import org.example.backspringboot.entity.*;
import org.example.backspringboot.repository.CategoryRepository;
import org.example.backspringboot.repository.IngredientRepository;
import org.example.backspringboot.repository.RecipeRepository;
import org.example.backspringboot.repository.UsersRepository;
import org.example.backspringboot.util.Util;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class RecipeService {

    // ========== Propriétés ==========

    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;
    private final UsersRepository usersRepository;
    private final IngredientRepository ingredientRepository; // 💡 Ajout pour gérer les ingrédients


    // ========== Constructeur ==========

    public RecipeService(RecipeRepository recipeRepository,
                         CategoryRepository categoryRepository,
                         UsersRepository usersRepository, IngredientRepository ingredientRepository) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.usersRepository = usersRepository;
        this.ingredientRepository = ingredientRepository;
    }


    // ========== Méthodes ==========

    // ----- Read -----

//    public List<RecipeDtoSend> getAllRecipes() {
//        return recipeRepository.findAll().stream()
//                .map(r -> new RecipeDtoSend(
//                        r.getId(),
//                        r.getTitle(),
//                        r.getDescription(),
//                        r.getPrepTime(),
//                        r.getCookTime(),
//                        r.getServings(),
//                        r.getImage(),
//                        r.isAllowComment(),
//                        r.getCreatedAt(),
//                        r.getCategory().getName(),
//                        r.getUser().getUsername()
//                ))
//                .toList();
//    }

    public List<RecipeDtoSend> getAllRecipes() {
        return recipeRepository.findAll().stream()
                .map(r -> {
                    List<RecipeIngredientDtoSend> ingredients = r.getRecipeIngredients().stream()
                            .map(ri -> new RecipeIngredientDtoSend(
                                    ri.getIngredient().getName(),
                                    ri.getQuantity(),
                                    ri.getIngredient().getUnit()
                            ))
                            .toList();

                    RecipeDtoSend dto = new RecipeDtoSend(
                            r.getId(),
                            r.getTitle(),
                            r.getDescription(),
                            r.getInstructions(),
                            r.getPrepTime(),
                            r.getCookTime(),
                            r.getServings(),
                            r.getImage(),
                            r.isAllowComment(),
                            r.getCreatedAt(),
                            r.getCategory().getName(),
                            r.getUser().getUsername(),
                            ingredients
                    );

                    dto.setIngredients(ingredients); // Ajoute la liste des ingrédients
                    return dto;
                })
                .toList();
    }

//    public RecipeDtoSend getRecipeById(Long id) {
//        Recipe recipe = recipeRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Recette non trouvée"));
//
//        return new RecipeDtoSend(
//                recipe.getId(),
//                recipe.getTitle(),
//                recipe.getDescription(),
//                recipe.getPrepTime(),
//                recipe.getCookTime(),
//                recipe.getServings(),
//                recipe.getImage(),
//                recipe.isAllowComment(),
//                recipe.getCreatedAt(),
//                recipe.getCategory().getName(),
//                recipe.getUser().getUsername()
//        );
//    }

    public RecipeDtoSend getRecipeById(Long id) {
        Recipe r = recipeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recette non trouvée"));

        List<RecipeIngredientDtoSend> ingredients = r.getRecipeIngredients().stream()
                .map(ri -> new RecipeIngredientDtoSend(
                        ri.getIngredient().getName(),
                        ri.getQuantity(),
                        ri.getIngredient().getUnit()
                ))
                .toList();

        RecipeDtoSend dto = new RecipeDtoSend(
                r.getId(),
                r.getTitle(),
                r.getDescription(),
                r.getInstructions(),
                r.getPrepTime(),
                r.getCookTime(),
                r.getServings(),
                r.getImage(),
                r.isAllowComment(),
                r.getCreatedAt(),
                r.getCategory().getName(),
                r.getUser().getUsername(),
                ingredients
        );

        dto.setIngredients(ingredients); // Ajoute la liste des ingrédients

        return dto;
    }


    // ----- Create -----

    public RecipeDtoSend createRecipe(RecipeDtoReceive dto) {
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Catégorie non trouvée"));
        Users user = usersRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        String slug = Util.slugify(dto.getTitle());

        // Vérifie si le slug existe déjà
        int suffix = 1;
        String uniqueSlug = slug;
        while (recipeRepository.existsBySlug(uniqueSlug)) {
            uniqueSlug = slug + "-" + suffix++;
        }

        Recipe recipe = new Recipe();
        recipe.setTitle(dto.getTitle());
        recipe.setSlug(uniqueSlug); // Slug unique
        recipe.setDescription(dto.getDescription());
        recipe.setInstructions(dto.getInstructions());
        recipe.setPrepTime(dto.getPrepTime());
        recipe.setCookTime(dto.getCookTime());
        recipe.setServings(dto.getServings());
        recipe.setImage(dto.getImage());
        recipe.setAllowComment(dto.isAllowComment());
        recipe.setCreatedAt(LocalDateTime.now());
        recipe.setCategory(category);
        recipe.setUser(user);

        // Gestion des ingrédients
        if (dto.getIngredients() != null) {
            for (RecipeIngredientDtoReceive riDto : dto.getIngredients()) {
                Ingredient ingredient = ingredientRepository.findById(riDto.getIngredientId())
                        .orElseThrow(() -> new RuntimeException("Ingrédient non trouvé : " + riDto.getIngredientId()));
                RecipeIngredient ri = new RecipeIngredient();
                ri.setRecipe(recipe);
                ri.setIngredient(ingredient);
                ri.setQuantity(riDto.getQuantity());

                recipe.getRecipeIngredients().add(ri);
            }
        }

        Recipe saved = recipeRepository.save(recipe);

        // Transformation en DTO à renvoyer
        List<RecipeIngredientDtoSend> ingredientDtoList = saved.getRecipeIngredients().stream()
                .map(ri -> new RecipeIngredientDtoSend(
                        ri.getIngredient().getName(),
                        ri.getQuantity(),
                        ri.getIngredient().getUnit()
                ))
                .toList();

        return new RecipeDtoSend(
                saved.getId(),
                saved.getTitle(),
                saved.getDescription(),
                saved.getInstructions(),
                saved.getPrepTime(),
                saved.getCookTime(),
                saved.getServings(),
                saved.getImage(),
                saved.isAllowComment(),
                saved.getCreatedAt(),
                saved.getCategory().getName(),
                saved.getUser().getUsername(),
                ingredientDtoList
        );
    }


    // ----- Update -----

    public RecipeDtoSend updateRecipe(Long id, RecipeDtoReceive dto) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recette non trouvée"));

        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Catégorie non trouvée"));
        Users user = usersRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        recipe.setTitle(dto.getTitle());
        recipe.setDescription(dto.getDescription());
        recipe.setInstructions(dto.getInstructions());
        recipe.setPrepTime(dto.getPrepTime());
        recipe.setCookTime(dto.getCookTime());
        recipe.setServings(dto.getServings());
        recipe.setImage(dto.getImage());
        recipe.setAllowComment(dto.isAllowComment());
        recipe.setCategory(category);
        recipe.setUser(user);

        // On peut réinitialiser et mettre à jour les ingrédients si nécessaire
        recipe.getRecipeIngredients().clear();
        if (dto.getIngredients() != null) {
            for (RecipeIngredientDtoReceive riDto : dto.getIngredients()) {
                Ingredient ingredient = ingredientRepository.findById(riDto.getIngredientId())
                        .orElseThrow(() -> new RuntimeException("Ingrédient non trouvé : " + riDto.getIngredientId()));
                RecipeIngredient ri = new RecipeIngredient();
                ri.setRecipe(recipe);
                ri.setIngredient(ingredient);
                ri.setQuantity(riDto.getQuantity());

                recipe.getRecipeIngredients().add(ri);
            }
        }

        Recipe updated = recipeRepository.save(recipe);

        List<RecipeIngredientDtoSend> ingredientDtoList = updated.getRecipeIngredients().stream()
                .map(ri -> new RecipeIngredientDtoSend(
                        ri.getIngredient().getName(),
                        ri.getQuantity(),
                        ri.getIngredient().getUnit()
                ))
                .toList();

        return new RecipeDtoSend(
                updated.getId(),
                updated.getTitle(),
                updated.getDescription(),
                updated.getInstructions(),
                updated.getPrepTime(),
                updated.getCookTime(),
                updated.getServings(),
                updated.getImage(),
                updated.isAllowComment(),
                updated.getCreatedAt(),
                updated.getCategory().getName(),
                updated.getUser().getUsername(),
                ingredientDtoList
        );
    }

    // ----- Delete -----

    public void deleteRecipe(Long id) {
        recipeRepository.deleteById(id);
    }

}
