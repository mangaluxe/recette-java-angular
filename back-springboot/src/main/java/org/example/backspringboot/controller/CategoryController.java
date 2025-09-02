package org.example.backspringboot.controller;

import org.example.backspringboot.dto.CategoryDtoReceive;
import org.example.backspringboot.dto.CategoryDtoSend;
import org.example.backspringboot.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category") // URL par défaut : http://localhost:8080/api/category
public class CategoryController {

    // ========== Propriétés ==========

    private final CategoryService categoryService;


    // ========== Constructeur ==========

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    // ========== Méthodes ==========

    // ----- Read -----

    @GetMapping
    public List<CategoryDtoSend> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDtoSend> getCategoryById(@PathVariable Long id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }


    // ----- Create -----

    @PostMapping
    public ResponseEntity<CategoryDtoSend> createCategory(@RequestBody CategoryDtoReceive dto) {
        return new ResponseEntity<>(categoryService.createCategory(dto), HttpStatus.CREATED);
    }


    // ----- Update -----

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDtoSend> updateCategory(@PathVariable Long id, @RequestBody CategoryDtoReceive dto) {
        return ResponseEntity.ok(categoryService.updateCategory(id, dto));
    }


    // ----- Delete -----

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

}
