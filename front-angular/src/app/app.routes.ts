// src/app/app.routes.ts

import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { BasesComponent } from './pages/bases/bases.component';
import { TestComponent } from './pages/test/test.component';
import { UsersComponent } from './pages/users/users.component';
import { UserComponent } from './pages/user/user.component';
import { RegisterComponent } from './pages/register/register.component';
import { RecipesComponent } from './pages/recipes/recipes.component';
import { RecipeComponent } from './pages/recipe/recipe.component';

import { DashboardComponent } from './pages/admin/dashboard/dashboard.component';
import { AddRecipeComponent } from './pages/admin/add-recipe/add-recipe.component';
import { EditRecipeComponent } from './pages/admin/edit-recipe/edit-recipe.component';


export const routes: Routes = [
  {path: '', component: HomeComponent}, // http://localhost:4200/
  {path: 'bases', component: BasesComponent}, // http://localhost:4200/bases
  {path: 'test', component: TestComponent}, // http://localhost:4200/test
  {path: 'utilisateurs', component: UsersComponent}, // http://localhost:4200/utilisateurs
  { path: 'utilisateur/:id', component: UserComponent }, // http://localhost:4200/utilisateur/1
  { path: 'inscription', component: RegisterComponent }, // http://localhost:4200/inscription
  { path: 'recettes', component: RecipesComponent }, // http://localhost:4200/recettes
  { path: 'recette/:id', component: RecipeComponent }, // http://localhost:4200/recette/1

  { path: 'admin', component: DashboardComponent }, // http://localhost:4200/admin
  { path: 'admin/ajout-recette', component: AddRecipeComponent }, // http://localhost:4200/admin/ajout-recette
  { path: 'admin/edit-recette/:id', component: EditRecipeComponent } // http://localhost:4200/admin/edit-recette/1
];