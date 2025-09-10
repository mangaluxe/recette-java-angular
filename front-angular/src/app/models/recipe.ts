// src/app/models/recipe.ts :

export interface RecipeIngredient {
  name: string;
  quantity: number;
  unit: string;
}

export interface Recipe {
  id: number;
  title: string;
  description: string;
  prepTime: number;
  cookTime: number;
  servings: number;
  image?: string;
  instructions: string;
  ingredients: RecipeIngredient[]; // <-- Liste des ingrédients
  allowComment: boolean;
  createdAt: Date;
  categoryName: string;
  authorName: string;
}