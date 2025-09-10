// src/app/pages/recipes/recipes.component.ts :

import { Component } from '@angular/core';
import { Recipe } from '../../models/recipe';
import { RecipesService } from '../../services/recipes.service';
import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-recipes',
  imports: [RouterLink, CommonModule],
  templateUrl: './recipes.component.html'
})
export class RecipesComponent {

  // ========== Propriétés ==========

  recipes: Recipe[] = []; // Retourne un tableau de recettes, par défaut un tableau vide

  // ========== Constructeur ==========

  constructor(private recipesService: RecipesService) {}

  // ========== Méthodes ==========

  ngOnInit(): void {
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