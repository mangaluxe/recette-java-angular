package org.example.backspringboot.service;

import org.example.backspringboot.dto.CategoryDtoReceive;
import org.example.backspringboot.dto.CategoryDtoSend;
import org.example.backspringboot.entity.Category;
import org.example.backspringboot.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    // ========== Propriétés ==========

    private final CategoryRepository categoryRepository;


    // ========== Constructeur ==========

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    // ========== Méthodes ==========

    // ----- Read -----

    public List<CategoryDtoSend> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(c -> new CategoryDtoSend(c.getId(), c.getName()))
                .toList();
    }

    public CategoryDtoSend getCategoryById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Catégorie non trouvée"));
        return new CategoryDtoSend(category.getId(), category.getName());
    }


    // ----- Create -----

    public CategoryDtoSend createCategory(CategoryDtoReceive dto) {
        Category category = new Category();
        category.setName(dto.getName());

        Category saved = categoryRepository.save(category);
        return new CategoryDtoSend(saved.getId(), saved.getName());
    }


    // ----- Update -----

    public CategoryDtoSend updateCategory(Long id, CategoryDtoReceive dto) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Catégorie non trouvée"));
        category.setName(dto.getName());
        Category updated = categoryRepository.save(category);
        return new CategoryDtoSend(updated.getId(), updated.getName());
    }


    // ----- Delete -----

    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

}
