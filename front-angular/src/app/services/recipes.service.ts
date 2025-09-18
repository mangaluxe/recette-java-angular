// src/app/services/recipes.service.ts :

// ng generate service services/recipes

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Recipe } from '../models/recipe';

// export interface RecipeIngredient {
//   name: string;
//   quantity: number;
//   unit: string;
// }
// export interface Recipe { // On peut mettre le model directement ici ou dans un fichier séparé : src/app/models/recipe.ts
//   id: number;
//   title: string;
//   description: string;
//   prepTime: number;
//   cookTime: number;
//   servings: number;
//   image?: string;
//   instructions: string;
//   ingredients: RecipeIngredient[]; // <-- Liste des ingrédients
//   allowComment: boolean;
//   createdAt: Date;
//   categoryName: string;
//   authorName: string;
// }

@Injectable({ // @Injectable = Service. Utilisation de ce service dans un composant : constructor(private recipesService: RecipesService) {}
  providedIn: 'root'
})
export class RecipesService {

  // ========== Propriétés ==========

  private apiUrl = 'http://localhost:8080/api/recipe'; // URL à adapter selon l'API backend


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

  // ----- Create -----

  /**
   * Créer une recette (sans upload)
   */
  // createRecipe(recipe: any): Observable<Recipe> {
  //   return this.http.post<Recipe>(this.apiUrl, recipe);
  // }

  /**
   * Créer une recette (avec upload)
   */
  createRecipe(data: FormData) {
    return this.http.post<any>(this.apiUrl, data);
  }

  // ----- Update -----

  /**
   * Modifier une recette (avec upload)
   */
  updateRecipe(id: number, formData: FormData): Observable<any> {
    return this.http.put<any>(`${this.apiUrl}/${id}`, formData);
  }

  // ----- Delete -----

  /**
   * Supprimer une recette
   */
  deleteRecipe(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

}