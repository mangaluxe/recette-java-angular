## 1️⃣ ----- Créer un modèle dans Angular -----

src/app/models/user.ts :

```ts
export interface Recipe {
  id: number;
  title: string;
  description: string;
  prepTime: number;
  cookTime: number;
  servings: number;
  image?: string;
  allowComment: boolean;
  createdAt: Date;
  categoryName: string;
  authorName: string;
}
```


## 2️⃣ ----- Créer un service Angular pour les appels HTTP -----

```bash
ng generate service services/recipes
```

src/app/services/recipes.service.ts :

```ts
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

  private apiUrl = 'http://localhost:8080/api/recipe/'; // URL à remplacer en fonction de l'API backend

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
```


## 3️⃣ ----- Utiliser le service Angular dans un composant -----

```bash
ng generate component pages/recipes

ng generate component pages/recipe

ng generate component pages/admin

ng generate component pages/admin-add-recipe
```

Exemple dans RecipesComponent : src/app/pages/recipes/recipes.component.ts :

```ts
@Component({
  selector: 'app-recipes',
  imports: [RouterLink, CommonModule],
  templateUrl: './recipes.component.html'
})
export class RecipesComponent {

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
```

src/app/pages/recipes/recipes.component.html :

```html
<div class="container">
    <main>

        <h1 class="center">Liste de recettes</h1>

        <h2 class="h-triangle">Toutes nos recettes</h2>

        <div class="recipes-container">
    
            @for (recipe of recipes; track recipe) {
    
                <div class="recipe-box">
                    <div class="recipe-box-in">
                        <a [routerLink]="['/recette', recipe.id]" class="img"><img src="assets/img/{{ recipe.image }}" alt="{{ recipe.title }}"></a>
                        <div class="txt">
                            <h2>{{ recipe.title }}</h2>
                            <p>{{ recipe.description }}</p>
                            <a [routerLink]="['/recette', recipe.id]" class="mini-btn btn-success">Voir Détails</a>
                        </div>
                    </div>
                </div>
            }
            @empty {
                <p class="center">Aucune recette</p>
            }

        </div>

    </main>
</div>
```

src/app/app.routes.ts :

```ts
export const routes: Routes = [
  {path: '', component: HomeComponent}, // http://localhost:4200/
  {path: 'bases', component: BasesComponent}, // http://localhost:4200/bases
  {path: 'test', component: TestComponent}, // http://localhost:4200/test
  {path: 'utilisateurs', component: UsersComponent}, // http://localhost:4200/utilisateurs
  { path: 'utilisateur/:id', component: UserComponent }, // http://localhost:4200/utilisateur/1
  { path: 'inscription', component: RegisterComponent }, // http://localhost:4200/inscription
  { path: 'recettes', component: RecipesComponent }, // http://localhost:4200/recettes
  { path: 'recette/:id', component: RecipeComponent }, // http://localhost:4200/recette/1
  { path: 'admin', component: AdminComponent } // http://localhost:4200/admin
];
```

src/app/partials/header/header.component.html :

Ajouter lien :

```html
<li><a routerLink="/recettes">Recettes</a></li>
```

src/app/pages/recipe/recipe.component.ts :

```ts
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
```