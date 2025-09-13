// src/app/pages/recipes/recipes.component.ts :

// ng generate component pages/recipes

import { Component, OnInit } from '@angular/core';
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

  recipes: Recipe[] = []; // Retourne un tableau de recettes, par défaut un tableau vide

  // ========== Constructeur ==========

  constructor(private recipesService: RecipesService) {}

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
        this.recipes = res;
      },
      error: (err) => {
        console.error('Erreur chargement recettes :', err);
      }
    });
  }

}