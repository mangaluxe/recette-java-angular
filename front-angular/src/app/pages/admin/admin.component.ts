import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { Recipe } from '../../models/recipe';
import { RecipesService } from '../../services/recipes.service';

@Component({
  selector: 'app-admin',
  imports: [RouterLink, CommonModule],
  templateUrl: './admin.component.html'
})
export class AdminComponent implements OnInit { // Note: Sans écrire "implements OnInit", ça marche quand même

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

  // ----- Delete -----

  /**
   * Supprimer une recette
   */
  deleteRecipe(id: number): void {
    this.recipesService.deleteRecipe(id).subscribe({
      next: () => {
        // console.log('Livre supprimé avec succès');
        this.recipes = this.recipes.filter(recipe => recipe.id !== id); // Mettre à jour la liste localement
      },
      error: (err) => {
        console.error('Erreur suppression de recette :', err);
      }
    });
  }

}