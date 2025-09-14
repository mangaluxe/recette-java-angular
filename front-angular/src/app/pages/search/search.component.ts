// src/app/pages/search/search.component.ts :

// ng generate component pages/search

import { Component, OnInit } from '@angular/core';
import { Recipe } from '../../models/recipe';
import { RecipesService } from '../../services/recipes.service';
import { RouterLink } from '@angular/router';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-search',
  imports: [RouterLink, FormsModule],
  templateUrl: './search.component.html'
})
export class SearchComponent implements OnInit {

  // ========== Propriétés ==========

  recipes: Recipe[] = [];

  searchTerm = '';

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
      next: (res) => this.recipes = res,
      error: (err) => console.error(err)
    });
  }

  /**
   * Recherche
   */
  onSearch(event: Event) {
    const input = event.target as HTMLInputElement;
    this.searchTerm = input.value;
  }

  // ========== Getter ==========

  /**
   * Filtrer recette
   */
  get filteredRecipes() {
    if (!this.searchTerm || this.searchTerm.trim().length < 2) {
      return [];
    }
    const term = this.searchTerm.toLowerCase();
    return this.recipes.filter(recipe =>
      recipe.title.toLowerCase().includes(term) ||
      recipe.description.toLowerCase().includes(term)
    );
  }  

}