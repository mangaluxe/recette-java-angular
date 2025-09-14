# Titre dynamique

app.routes.ts (Routes) :

```ts
export const routes: Routes = [
  { path: '', component: HomeComponent, data: { title: "Accueil - Site de recettes" } }, // http://localhost:4200/
  { path: 'utilisateurs', component: UsersComponent, data: { title: "Liste d'utilisateurs", breadcrumb: "Liste d'utilisateurs" } }, // http://localhost:4200/utilisateurs
  { path: 'utilisateur/:id', component: UserComponent, data: { title: "Détail de l'utilisateur", breadcrumb: "Détail de l'utilisateur" } }, // http://localhost:4200/utilisateur/1
  { path: 'inscription', component: RegisterComponent, data: { title: "Inscription", breadcrumb: "Inscription" } }, // http://localhost:4200/inscription
  { path: 'recettes', component: RecipesComponent, data: { title: "Liste des recettes", breadcrumb: "Liste des recettes" } }, // http://localhost:4200/recettes
  { path: 'recette/:id', component: RecipeComponent, data: { breadcrumb: "Détail de la recette" } }, // http://localhost:4200/recette/1
  { path: 'recherche', component: SearchComponent, data: { title: "Recherche de recettes", breadcrumb: "Recherche" } } // http://localhost:4200/recherche
];
```

app.component.ts (Composant principal) :

```ts
export class AppComponent implements OnInit {

  constructor(
    private titleService: Title, // Injection du service Title
    private router: Router, // Injection du service Router pour la navigation
    private activatedRoute: ActivatedRoute // Injection du service ActivatedRoute
  ) {}

  ngOnInit(): void { // Méthode appelée lors de l'initialisation du composant
    this.router.events.pipe( // Écoute des événements de navigation
      filter(event => event instanceof NavigationEnd), // Filtre pour ne garder que les événements de fin de navigation
      map(() => this.activatedRoute), // Récupère la route active
      map(route => { // Parcourt les enfants de la route pour trouver la route finale
        while (route.firstChild) route = route.firstChild; // Descend dans la hiérarchie des routes
        return route; // Retourne la route finale
      }),
      mergeMap(route => route.data) // Fusionne les données de la route
    ).subscribe(data => { // Souscrit aux données de la route
      this.titleService.setTitle(data['title'] || 'Site de recettes'); // Définit le titre de la page, avec un titre par défaut
    });
  }

}
```

Titre dynamique basé sur une donnée de BDD. Uniquement pour les pages de détail.
recipe.component.ts :

```ts
export class RecipeComponent implements OnInit {

  // ========== Propriétés ==========

  recipe: Recipe | null = null; // Retourne une recette ou null

  // ========== Constructeur ==========

  constructor(
    private route: ActivatedRoute,
    private recipesService: RecipesService,

    private titleService: Title // 💡 Injection du service Title (natif dans Angular) pour mettre à jour le titre dynamique
  ) {}

  // ========== Méthodes ==========

  ngOnInit(): void { // Appel automatique au chargement
    this.getRecipeById();
  }

  // ----- Read -----

  getRecipeById(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.recipesService.getRecipeById(id).subscribe({
      next: (res) => {
        this.recipe = res;

        // --- 💡 Mettre à jour le titre dynamique ---
        if (this.recipe?.title) {
          this.titleService.setTitle(`${this.recipe.title} - Détail de la recette`);
        }
        // --- ---
      },
      error: (err) => {
        console.error('Erreur chargement recette :', err);
      }
    });
  }

}
```