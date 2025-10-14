// src/app/pages/recipes/recipes.component.ts :

// ng generate component pages/recipes

import { Component, inject, OnInit, signal } from '@angular/core';
import { Recipe } from '../../models/recipe';
import { RecipesService } from '../../services/recipes.service';
import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-recipes',
  imports: [RouterLink, CommonModule],
  templateUrl: './recipes.component.html'
})
export class RecipesComponent implements OnInit { // Note: Sans écrire "implements OnInit", ça marche quand même

  // ========== Propriétés ==========

  // recipes: Recipe[] = []; // Retourne un tableau de recettes, par défaut un tableau vide
  recipes = signal<Recipe[]>([]); // 💥 Déclaration d’un signal

  private readonly recipesService = inject(RecipesService); // Nouvelle façon d'injecter le service


  // ========== Constructeur ==========

  // constructor(private recipesService: RecipesService) {} // Utilisation du service RecipesService


  // ========== Méthodes ==========

  ngOnInit(): void { // Appel automatique au chargement
    this.getRecipes();
  }

  // ----- Read -----

  /**
   * Récupérer toutes les recettes
   */
  getRecipes(): void {
    this.recipesService.getRecipes().subscribe({
      next: (res) => {
        // this.recipes = res;
        this.recipes.set(res); // 💥 .set() met à jour le signal

      },
      error: (err) => {
        console.error('Erreur chargement recettes :', err);
      }
    });
  }

}