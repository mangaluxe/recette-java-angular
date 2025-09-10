import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { Recipe } from '../../models/recipe';
import { RecipesService } from '../../services/recipes.service';

@Component({
  selector: 'app-recipe',
  imports: [RouterLink, CommonModule],
  templateUrl: './recipe.component.html'
})
export class RecipeComponent implements OnInit {

  // ========== Propriétés ==========

  recipe: Recipe | null = null; // Retourne une recette ou null

  // ========== Constructeur ==========

  constructor(
    private route: ActivatedRoute,
    private recipesService: RecipesService
  ) {}

  // ========== Méthodes ==========

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.recipesService.getRecipeById(id).subscribe({
      next: (res) => {
        this.recipe = res;
      },
      error: (err) => {
        console.error('Erreur chargement recette :', err);
      }
    });
  }

}
