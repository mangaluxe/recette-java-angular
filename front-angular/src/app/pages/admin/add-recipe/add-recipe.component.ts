// src/app/pages/admin/add-recipe/add-recipe.component.ts :

// ng generate component pages/admin/add-recipe

import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { RecipesService } from '../../../services/recipes.service';
import { Router, RouterLink } from '@angular/router';
import { CategoriesService } from '../../../services/categories.service';

@Component({
  selector: 'app-add-recipe',
  imports: [RouterLink, CommonModule, FormsModule, ReactiveFormsModule],
  templateUrl: './add-recipe.component.html'
})
export class AddRecipeComponent implements OnInit {

  // ========== Propriétés ==========

  recipe_form: FormGroup; // Formulaire pour l'ajout de recette
  categories: any[] = []; // Liste des catégories récupérées depuis le backend

  selectedFile: File | null = null; // Upload


  // ========== Constructeur ==========

  constructor(private fb: FormBuilder, private recipesService: RecipesService,
    private categoriesService: CategoriesService, private router: Router) { // Ne pas oublier l'injection du Router pour la redirection
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
      ingredients: this.fb.array([
        this.createIngredient() // Au moins un ingrédient par défaut
      ])
    });
  }


  // ========== Méthodes ==========

  ngOnInit(): void { // Appel automatique au chargement
    this.loadCategories();
  }

  loadCategories(): void {
    this.categoriesService.getCategories().subscribe({
      next: (cats) => this.categories = cats,
      error: (err) => console.error('Erreur chargement catégories', err)
    });
  }

  // Getter pratique pour manipuler les ingrédients
  get ingredients(): FormArray {
    return this.recipe_form.get('ingredients') as FormArray;
  }

  createIngredient(): FormGroup {
    return this.fb.group({
      ingredientName: ['', Validators.required],
      quantity: [0, [Validators.required, Validators.min(1)]],
      unit: ['']
    });
  }

  addIngredient(): void {
    this.ingredients.push(this.createIngredient());
  }

  removeIngredient(): void {
    if (this.ingredients.length > 1) { // toujours garder au moins 1
      this.ingredients.removeAt(this.ingredients.length - 1);
    }
  }


  // ----- Create -----

  /**
   * Ajouter recette (sans upload)
   */
  // addRecipe(): void {
  //   if (this.recipe_form.valid) {
  //     this.recipesService.createRecipe(this.recipe_form.value).subscribe({
  //       next: (res) => {
  //         console.log('Recette ajoutée', res);
  //         this.router.navigate(['/recettes']);
  //       },
  //       error: (err) => console.error(err)
  //     });
  //   }
  // }

  /**
   * Ajouter recette (avec upload)
   */
  addRecipe(): void {
    if (this.recipe_form.valid) {
      const formData = new FormData();
      
      // Ajouter les données de la recette (DTO)
      formData.append('recipe', new Blob([JSON.stringify(this.recipe_form.value)], { type: 'application/json' }));
      
      // Ajouter le fichier si présent
      if (this.selectedFile) {
        formData.append('file', this.selectedFile, this.selectedFile.name);
      }
  
      this.recipesService.createRecipe(formData).subscribe({
        next: (res) => {
          console.log("Envoi ingrédient :", this.recipe_form.value.ingredients);
          console.log('Recette ajoutée', res);
          this.router.navigate(['/recettes']);
        },
        error: (err) => console.error(err)
      });
    }
  }


  // ----- Upload -----

  onFileSelected(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (input.files && input.files.length > 0) {
      this.selectedFile = input.files[0];
    }
  }  

}
