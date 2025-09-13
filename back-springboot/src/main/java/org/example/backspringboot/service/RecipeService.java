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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class RecipeService {

    // ========== Propriétés ==========

    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;
    private final UsersRepository usersRepository;
    private final IngredientRepository ingredientRepository; // 💡 Ajout pour gérer les ingrédients

    private final String uploadDir = "uploads/";


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

    /**
     * Recupérer toutes les recettes
     */
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

    /**
     * Récupérer une recette par id
     */
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

    /**
     * Ajouter une recette
     */
    public RecipeDtoSend createRecipe(RecipeDtoReceive dto, MultipartFile file) throws IOException {

        // --- Vérifier catégorie et utilisateur ---
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Catégorie non trouvée"));
        Users user = usersRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        // --- Générer slug unique ---
        String slug = Util.slugify(dto.getTitle());
        int suffix = 1;
        String uniqueSlug = slug;
        while (recipeRepository.existsBySlug(uniqueSlug)) {
            uniqueSlug = slug + "-" + suffix++;
        }

        // --- Créer la recette ---
        Recipe recipe = new Recipe();
        recipe.setTitle(dto.getTitle());
        recipe.setSlug(uniqueSlug); // Slug unique
        recipe.setDescription(dto.getDescription());
        recipe.setInstructions(dto.getInstructions());
        recipe.setPrepTime(dto.getPrepTime());
        recipe.setCookTime(dto.getCookTime());
        recipe.setServings(dto.getServings());
//        recipe.setImage(dto.getImage()); // On fait setImage plus bas en cas d'upload
        recipe.setAllowComment(dto.isAllowComment());
        recipe.setCreatedAt(LocalDateTime.now());
        recipe.setCategory(category);
        recipe.setUser(user);

        // --- Upload image ---
        if (file != null && !file.isEmpty()) {
            // Vérifier extension :
            String originalFilename = file.getOriginalFilename();
            String ext = originalFilename.substring(originalFilename.lastIndexOf(".") + 1).toLowerCase();
            if (!List.of("jpg", "jpeg", "png", "gif", "webp").contains(ext)) {
                throw new RuntimeException("Extension de fichier non supportée : " + ext);
            }

            // Créer dossier si inexistant :
            Path uploadPath = Paths.get(uploadDir);
            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            // Nom de fichier basé sur le slug :
            String uniqueFileName = Util.slugify(dto.getTitle()) + "." + ext;
//            String uniqueFileName = Util.slugify(dto.getTitle()) + "_" + System.currentTimeMillis() + "." + ext; // Nom unique
            Path dest = uploadPath.resolve(uniqueFileName);
            Files.copy(file.getInputStream(), dest, StandardCopyOption.REPLACE_EXISTING);

            recipe.setImage(uniqueFileName); // Sauvegarde juste le nom
        }
        else {
            recipe.setImage(null);
        }

        // --- Gestion des ingrédients (existants ou nouveaux) ---
        if (dto.getIngredients() != null) {
            if (recipe.getRecipeIngredients() == null) {
                recipe.setRecipeIngredients(new ArrayList<>());
            }

            for (RecipeIngredientDtoReceive riDto : dto.getIngredients()) {
                Ingredient ingredient;

                if (riDto.getIngredientId() != null) {
                    // ingrédient existant :
                    ingredient = ingredientRepository.findById(riDto.getIngredientId())
                            .orElseThrow(() -> new RuntimeException("Ingrédient non trouvé : " + riDto.getIngredientId()));
                }
                else if (riDto.getIngredientName() != null && riDto.getUnit() != null) {
                    // nouvel ingrédient :
                    ingredient = new Ingredient();
                    ingredient.setName(riDto.getIngredientName());
                    ingredient.setUnit(riDto.getUnit());
                    ingredient = ingredientRepository.save(ingredient);
                }
                else {
                    throw new RuntimeException("Un ingrédient doit avoir soit un id, soit un nom+unité");
                }

                RecipeIngredient ri = new RecipeIngredient();
                ri.setRecipe(recipe);
                ri.setIngredient(ingredient);
                ri.setQuantity(riDto.getQuantity());

                recipe.getRecipeIngredients().add(ri);
            }
        }

        System.out.println("Création recette : " + recipe.getTitle());
        System.out.println("Nb ingrédients : " + (recipe.getRecipeIngredients() != null ? recipe.getRecipeIngredients().size() : 0));
        recipe.getRecipeIngredients().forEach(ri -> {
            System.out.println("Ingrédient : " + ri.getIngredient().getName() + ", quantité : " + ri.getQuantity());
        });

        Recipe saved = recipeRepository.save(recipe);

        // Transformation en DTO à renvoyer
        List<RecipeIngredientDtoSend> ingredientDtoList = saved.getRecipeIngredients().stream()
                .map(ri -> new RecipeIngredientDtoSend(
                        ri.getIngredient().getName(),
                        ri.getQuantity(),
                        ri.getIngredient().getUnit()
                ))
                .toList();

        System.out.println("Retour DTO : " + ingredientDtoList.size() + " ingrédients");

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

    /**
     * Modifier une recette
     */
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

        // Réinitialiser les ingrédients et les recréer
        recipe.getRecipeIngredients().clear();
        if (dto.getIngredients() != null) {
            for (RecipeIngredientDtoReceive riDto : dto.getIngredients()) {
                Ingredient ingredient;

                if (riDto.getIngredientId() != null) {
                    ingredient = ingredientRepository.findById(riDto.getIngredientId())
                            .orElseThrow(() -> new RuntimeException("Ingrédient non trouvé : " + riDto.getIngredientId()));
                } else if (riDto.getIngredientName() != null && riDto.getUnit() != null) {
                    ingredient = new Ingredient();
                    ingredient.setName(riDto.getIngredientName());
                    ingredient.setUnit(riDto.getUnit());
                    ingredient = ingredientRepository.save(ingredient);
                } else {
                    throw new RuntimeException("Un ingrédient doit avoir soit un ID, soit un nom+unité");
                }

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

    /**
     * Supprimer une recette
     */
    public void deleteRecipe(Long id) {
        recipeRepository.deleteById(id);
    }

}
