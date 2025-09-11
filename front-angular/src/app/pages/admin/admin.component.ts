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
export class AdminComponent implements OnInit {

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