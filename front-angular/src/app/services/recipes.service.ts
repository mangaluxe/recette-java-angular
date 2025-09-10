// src/app/services/recipes.service.ts :

// ng generate service services/recipes

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Recipe } from '../models/recipe';

// export interface Recipe { // On peut mettre le model directement ici ou dans un fichier séparé : src/app/models/recipe.ts
//   id: number;
//   title: string;
//   description: string;
//   prepTime: number;
//   cookTime: number;
//   servings: number;
//   image?: string;
//   allowComment: boolean;
//   createdAt: Date;
//   categoryName: string;
//   authorName: string;
// }

@Injectable({
  providedIn: 'root'
})
export class RecipesService {

  // ========== Propriétés ==========

  private apiUrl = 'http://localhost:8080/api/recipe'; // URL à remplacer en fonction de l'API backend


  // ========== Constructeur ==========

  constructor(private http: HttpClient) {}


  // ========== Méthodes ==========

  // ----- Read -----

  /**
   * Récupérer toutes les recettes
   */
  getRecipes(): Observable<Recipe[]> {
    return this.http.get<Recipe[]>(this.apiUrl);
  }

  /**
   * Récupérer une recette par id
   */
  getRecipeById(id: number): Observable<Recipe> {
    return this.http.get<Recipe>(`${this.apiUrl}/${id}`);
  }

}