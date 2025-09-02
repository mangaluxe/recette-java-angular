package org.example.backspringboot.service;

import org.example.backspringboot.dto.IngredientDtoReceive;
import org.example.backspringboot.dto.IngredientDtoSend;
import org.example.backspringboot.entity.Ingredient;
import org.example.backspringboot.repository.IngredientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IngredientService {


    // ========== Propriétés ==========

    private final IngredientRepository ingredientRepository;


    // ========== Constructeur ==========

    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }


    // ========== Méthodes ==========

    // ----- Read -----

    public List<IngredientDtoSend> getAllIngredients() {
        return ingredientRepository.findAll().stream()
                .map(i -> new IngredientDtoSend(i.getId(), i.getName(), i.getUnit()))
                .toList();
    }

    public IngredientDtoSend getIngredientById(Long id) {
        Ingredient ingredient = ingredientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ingrédient non trouvé"));
        return new IngredientDtoSend(ingredient.getId(), ingredient.getName(), ingredient.getUnit());
    }

    // ----- Create -----

    public IngredientDtoSend createIngredient(IngredientDtoReceive dto) {
        Ingredient ingredient = new Ingredient();
        ingredient.setName(dto.getName());
        ingredient.setUnit(dto.getUnit());

        Ingredient saved = ingredientRepository.save(ingredient);
        return new IngredientDtoSend(saved.getId(), saved.getName(), saved.getUnit());
    }


    // ----- Update -----

    public IngredientDtoSend updateIngredient(Long id, IngredientDtoReceive dto) {
        Ingredient ingredient = ingredientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ingrédient non trouvé"));
        ingredient.setName(dto.getName());
        ingredient.setUnit(dto.getUnit());

        Ingredient updated = ingredientRepository.save(ingredient);
        return new IngredientDtoSend(updated.getId(), updated.getName(), updated.getUnit());
    }


    // ----- Delete -----

    public void deleteIngredient(Long id) {
        ingredientRepository.deleteById(id);
    }

}
