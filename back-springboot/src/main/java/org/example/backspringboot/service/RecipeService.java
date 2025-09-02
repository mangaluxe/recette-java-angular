package org.example.backspringboot.service;

import org.example.backspringboot.dto.RecipeDtoReceive;
import org.example.backspringboot.dto.RecipeDtoSend;
import org.example.backspringboot.entity.Category;
import org.example.backspringboot.entity.Recipe;
import org.example.backspringboot.entity.Users;
import org.example.backspringboot.repository.CategoryRepository;
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


    // ========== Constructeur ==========

    public RecipeService(RecipeRepository recipeRepository,
                         CategoryRepository categoryRepository,
                         UsersRepository usersRepository) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.usersRepository = usersRepository;
    }


    // ========== Méthodes ==========

    // ----- Read -----

    public List<RecipeDtoSend> getAllRecipes() {
        return recipeRepository.findAll().stream()
                .map(r -> new RecipeDtoSend(
                        r.getId(),
                        r.getTitle(),
                        r.getDescription(),
                        r.getPrepTime(),
                        r.getCookTime(),
                        r.getServings(),
                        r.getImage(),
                        r.isAllowComment(),
                        r.getCreatedAt(),
                        r.getCategory().getName(),
                        r.getUser().getUsername()
                ))
                .toList();
    }

    public RecipeDtoSend getRecipeById(Long id) {
        Recipe recipe = recipeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recette non trouvée"));

        return new RecipeDtoSend(
                recipe.getId(),
                recipe.getTitle(),
                recipe.getDescription(),
                recipe.getPrepTime(),
                recipe.getCookTime(),
                recipe.getServings(),
                recipe.getImage(),
                recipe.isAllowComment(),
                recipe.getCreatedAt(),
                recipe.getCategory().getName(),
                recipe.getUser().getUsername()
        );
    }


    // ----- Create -----

    public RecipeDtoSend createRecipe(RecipeDtoReceive dto) {
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Catégorie non trouvée"));
        Users user = usersRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        Recipe recipe = new Recipe();
        recipe.setTitle(dto.getTitle());
        recipe.setSlug(Util.slugify(dto.getTitle()));
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

        Recipe saved = recipeRepository.save(recipe);

        return new RecipeDtoSend(
                saved.getId(),
                saved.getTitle(),
                saved.getDescription(),
                saved.getPrepTime(),
                saved.getCookTime(),
                saved.getServings(),
                saved.getImage(),
                saved.isAllowComment(),
                saved.getCreatedAt(),
                saved.getCategory().getName(),
                saved.getUser().getUsername()
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

        Recipe updated = recipeRepository.save(recipe);

        return new RecipeDtoSend(
                updated.getId(),
                updated.getTitle(),
                updated.getDescription(),
                updated.getPrepTime(),
                updated.getCookTime(),
                updated.getServings(),
                updated.getImage(),
                updated.isAllowComment(),
                updated.getCreatedAt(),
                updated.getCategory().getName(),
                updated.getUser().getUsername()
        );
    }

    // ----- Delete -----

    public void deleteRecipe(Long id) {
        recipeRepository.deleteById(id);
    }

}
