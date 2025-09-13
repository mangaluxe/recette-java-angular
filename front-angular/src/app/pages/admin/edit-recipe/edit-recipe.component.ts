// src/app/pages/admin/edit-recipe/edit-recipe.component.ts :

// ng generate component pages/admin/edit-recipe

import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { RecipesService } from '../../../services/recipes.service';
import { CategoriesService } from '../../../services/categories.service';

@Component({
  selector: 'app-edit-recipe',
  imports: [RouterLink, CommonModule, FormsModule, ReactiveFormsModule],
  templateUrl: './edit-recipe.component.html'
})
export class EditRecipeComponent implements OnInit {

  // ========== Propriétés ==========

  recipe_form: FormGroup;
  categories: any[] = [];
  selectedFile: File | null = null;
  recipeId!: number; // Id de la recette à éditer
  currentImage: string | null = null; // Image actuelle


  // ========== Constructeur ==========

  constructor(
    private fb: FormBuilder,
    private recipesService: RecipesService,
    private categoriesService: CategoriesService,
    private router: Router,
    private route: ActivatedRoute
  ) {
    this.recipe_form = this.fb.group({
      title: ['', Validators.required],
      description: ['', Validators.required],
      instructions: ['', Validators.required],
      prepTime: [0, Validators.required],
      cookTime: [0, Validators.required],
      servings: [1, Validators.required],
      image: [''],
      allowComment: [true],
      categoryId: [null, Validators.required],
      userId: [1, Validators.required],
      ingredients: this.fb.array([])
    });
  }


  // ========== Méthodes ==========

  ngOnInit(): void {
    this.loadCategories();

    // Récupérer l'id depuis l'URL
    this.recipeId = +this.route.snapshot.paramMap.get('id')!;
    this.loadRecipe(this.recipeId);
  }

  loadCategories(): void {
    this.categoriesService.getCategories().subscribe({
      next: cats => this.categories = cats,
      error: err => console.error('Erreur chargement catégories', err)
    });
  }

  get ingredients(): FormArray {
    return this.recipe_form.get('ingredients') as FormArray;
  }

  createIngredient(ing?: any): FormGroup {
    return this.fb.group({
      ingredientName: [ing?.ingredientName || '', Validators.required],
      quantity: [ing?.quantity || 0, [Validators.required, Validators.min(1)]],
      unit: [ing?.unit || '']
    });
  }

  addIngredient(): void {
    this.ingredients.push(this.createIngredient());
  }

  removeIngredient(): void {
    if (this.ingredients.length > 1) {
      this.ingredients.removeAt(this.ingredients.length - 1);
    }
  }

  loadRecipe(id: number): void {
    this.recipesService.getRecipeById(id).subscribe({
      next: recipe => {
        // Patch des valeurs simples
        this.recipe_form.patchValue({
          title: recipe.title,
          description: recipe.description,
          instructions: recipe.instructions,
          prepTime: recipe.prepTime,
          cookTime: recipe.cookTime,
          servings: recipe.servings,
          image: recipe.image,
          allowComment: recipe.allowComment,
          categoryId: recipe.categoryId,
          userId: 1
        });
  
        this.currentImage = recipe.image || null; // Image actuelle
  
        // Vider le FormArray actuel
        this.ingredients.clear();
  
        // Ajouter chaque ingrédient correctement
        if (recipe.ingredients && recipe.ingredients.length > 0) {
          recipe.ingredients.forEach((ing: any) => {
            this.ingredients.push(this.createIngredient({
              ingredientName: ing.name,
              quantity: ing.quantity,
              unit: ing.unit
            }));
          });
        } else {
          // Toujours au moins un ingrédient
          this.ingredients.push(this.createIngredient());
        }
      },
      error: err => console.error(err)
    });
  }  

  onFileSelected(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      this.selectedFile = input.files[0];
    }
  }

  updateRecipe(): void {
    if (this.recipe_form.valid) {
      const formData = new FormData();
      formData.append('recipe', new Blob([JSON.stringify(this.recipe_form.value)], { type: 'application/json' }));

      if (this.selectedFile) {
        formData.append('file', this.selectedFile, this.selectedFile.name);
      }

      this.recipesService.updateRecipe(this.recipeId, formData).subscribe({
        next: res => {
          console.log('Recette mise à jour', res);
          this.router.navigate(['/recettes']);
        },
        error: err => console.error(err)
      });
    }
  }

}
