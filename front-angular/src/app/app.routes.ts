// src/app/app.routes.ts

import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { UsersComponent } from './pages/users/users.component';
import { UserComponent } from './pages/user/user.component';
import { RegisterComponent } from './pages/register/register.component';
import { RecipesComponent } from './pages/recipes/recipes.component';
import { RecipeComponent } from './pages/recipe/recipe.component';

import { DashboardComponent } from './pages/admin/dashboard/dashboard.component';
import { AddRecipeComponent } from './pages/admin/add-recipe/add-recipe.component';
import { EditRecipeComponent } from './pages/admin/edit-recipe/edit-recipe.component';
import { SearchComponent } from './pages/search/search.component';

import { BasesComponent } from './cours/bases/bases.component';
import { ParentComponent } from './cours/parent/parent.component';
import { EnfantComponent } from './cours/enfant/enfant.component';
import { authGuard } from './guards/auth.guard';
import { LoginComponent } from './pages/login/login.component';
import { MemberComponent } from './pages/member/member.component';
import { AdminUserComponent } from './pages/admin/admin-user/admin-user.component';
import { AdminUserEditComponent } from './pages/admin/admin-user-edit/admin-user-edit.component';

export const routes: Routes = [
  { path: '', component: HomeComponent, data: { title: "Accueil - Site de recettes" } }, // http://localhost:4200/
  { path: 'utilisateurs', component: UsersComponent, data: { title: "Liste d'utilisateurs", breadcrumb: "Liste d'utilisateurs" } }, // http://localhost:4200/utilisateurs
  { path: 'utilisateur/:id', component: UserComponent, data: { title: "Détail de l'utilisateur", breadcrumb: "Détail de l'utilisateur" } }, // http://localhost:4200/utilisateur/1
  { path: 'inscription', component: RegisterComponent, data: { title: "Inscription", breadcrumb: "Inscription" } }, // http://localhost:4200/inscription
  { path: 'connexion', component: LoginComponent, data: { title: "Connexion", breadcrumb: "Connexion" } }, // http://localhost:4200/connexion
  { path: 'membre', component: MemberComponent, canActivate: [authGuard], data: { title: "Espace membre", breadcrumb: "Espace membre" } },

  { path: 'recettes', component: RecipesComponent, data: { title: "Liste des recettes", breadcrumb: "Liste des recettes" } }, // http://localhost:4200/recettes
  { path: 'recette/:id', component: RecipeComponent, data: { title: "Détail de la recette", breadcrumb: "Détail de la recette" } }, // http://localhost:4200/recette/1
  { path: 'recherche', component: SearchComponent, data: { title: "Recherche de recettes", breadcrumb: "Recherche" } }, // http://localhost:4200/recherche

  { path: 'admin', component: DashboardComponent, canActivate: [authGuard], data: { title: "Espace admin", breadcrumb: "Admin" } }, // http://localhost:4200/admin
  { path: 'admin/ajout-recette', component: AddRecipeComponent, canActivate: [authGuard], data: { title: "Ajouter une recette", breadcrumb: "Ajouter une recette" } }, // http://localhost:4200/admin/ajout-recette
  { path: 'admin/edit-recette/:id', component: EditRecipeComponent, canActivate: [authGuard], data: { title: "Modifier une recette", breadcrumb: "Modifier une recette" } }, // http://localhost:4200/admin/edit-recette/1
  { path: 'admin/admin-utilisateurs', component: AdminUserComponent, canActivate: [authGuard], data: { title: "Gestion d'utilisateurs", breadcrumb: "Gestion d'utilisateurs" } }, // http://localhost:4200/admin/admin-utilisateurs
  { path: 'admin/admin-utilisateurs/edit/:id', component: AdminUserEditComponent, canActivate: [authGuard], data: { title: "Gestion d'utilisateurs", breadcrumb: "Gestion d'utilisateurs" } }, // http://localhost:4200/admin/admin-utilisateurs/1

  { path: 'cours', component: BasesComponent, data: { title: "Les bases d'Angular", breadcrumb: "Les bases d'Angular" } }, // http://localhost:4200/cours
  { path: 'cours/parent', component: ParentComponent, data: { title: "Composant Parent", breadcrumb: "Parent" } }, // http://localhost:4200/parent
  { path: 'cours/enfant', component: EnfantComponent, data: { title: "Composant Enfant", breadcrumb: "Enfant" } } // http://localhost:4200/enfant
];