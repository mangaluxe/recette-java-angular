// src/app/pages/recipe/recipe.component.ts :

// ng generate component pages/recipe

import { CommonModule } from '@angular/common';
import { Component, inject, OnInit, signal } from '@angular/core';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { Recipe } from '../../models/recipe';
import { RecipesService } from '../../services/recipes.service';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-recipe',
  imports: [RouterLink, CommonModule],
  templateUrl: './recipe.component.html'
})
export class RecipeComponent implements OnInit {

  // ========== Propriétés ==========

  // recipe: Recipe | null = null; // Retourne une recette ou null
  recipe = signal<Recipe | null>(null); // ⚡ Signal pour la recette

  private readonly recipesService = inject(RecipesService); // Nouvelle façon d'injecter le service. Pour nos propres services, mais pas pour des services Angular comme ActivatedRoute ou Title.


  // ========== Constructeur ==========

  constructor(
    private route: ActivatedRoute,
    private titleService: Title // 💡 Injection du service Title (natif dans Angular) pour mettre à jour le titre dynamique

    // private recipesService: RecipesService, // Utilisation du service RecipesService
  ) {}


  // ========== Méthodes ==========

  ngOnInit(): void { // Appel automatique au chargement
    this.getRecipeById();
  }

  // ----- Read -----

  getRecipeById(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.recipesService.getRecipeById(id).subscribe({
      next: (res) => {
        // this.recipe = res;
        this.recipe.set(res); // 💥 .set() met à jour le signal

        // --- 💡 Mettre à jour le titre dynamique ---
        // if (this.recipe?.title) {
        //   this.titleService.setTitle(`${this.recipe.title} - Recette`);
        // }
        if (res?.title) { // 💥 Avec signal
          this.titleService.setTitle(`${res.title} - Recette`);
        }
        // --- ---
      },
      error: (err) => {
        console.error('Erreur chargement recette :', err);
      }
    });
  }

}