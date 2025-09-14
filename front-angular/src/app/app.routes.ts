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
import { SearchComponent } from './pages/search/search.component';

export const routes: Routes = [
  { path: '', component: HomeComponent, data: { title: "Accueil - Site de recettes" } }, // http://localhost:4200/
  { path: 'bases', component: BasesComponent, data: { title: "Bases", breadcrumb: "Bases" } }, // http://localhost:4200/bases
  { path: 'test', component: TestComponent, data: { title: "Test", breadcrumb: "Test" } }, // http://localhost:4200/test
  { path: 'utilisateurs', component: UsersComponent, data: { title: "Liste d'utilisateurs", breadcrumb: "Liste d'utilisateurs" } }, // http://localhost:4200/utilisateurs
  { path: 'utilisateur/:id', component: UserComponent, data: { title: "Détail de l'utilisateur", breadcrumb: "Détail de l'utilisateur" } }, // http://localhost:4200/utilisateur/1
  { path: 'inscription', component: RegisterComponent, data: { title: "Inscription", breadcrumb: "Inscription" } }, // http://localhost:4200/inscription
  { path: 'recettes', component: RecipesComponent, data: { title: "Liste des recettes", breadcrumb: "Liste des recettes" } }, // http://localhost:4200/recettes
  { path: 'recette/:id', component: RecipeComponent, data: { title: "Détail de la recette", breadcrumb: "Détail de la recette" } }, // http://localhost:4200/recette/1
  { path: 'recherche', component: SearchComponent, data: { title: "Recherche de recettes", breadcrumb: "Recherche" } }, // http://localhost:4200/recherche

  { path: 'admin', component: DashboardComponent, data: { title: "Espace admin", breadcrumb: "Admin" } }, // http://localhost:4200/admin
  { path: 'admin/ajout-recette', component: AddRecipeComponent, data: { title: "Ajouter une recette", breadcrumb: "Ajouter une recette" } }, // http://localhost:4200/admin/ajout-recette
  { path: 'admin/edit-recette/:id', component: EditRecipeComponent, data: { title: "Modifier une recette", breadcrumb: "Modifier une recette" } } // http://localhost:4200/admin/edit-recette/1
];