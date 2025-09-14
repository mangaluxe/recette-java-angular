# Fil d'Ariane (Breadcrumb)

ng generate component components/breadcrumb


breadcrumb.component.html :
```html
<div class="breadcrumb">
    <a routerLink="/"><span class="icon-home"></span> Accueil</a> <span> > </span>
  
    @for (breadcrumb of breadcrumbs; track breadcrumb; let last = $last) {
        @if (!last) {
            <a [routerLink]="breadcrumb.url">{{ breadcrumb.label }}</a>
        }
        @if (last) {
            <span>{{ breadcrumb.label }}</span>
        }
    }
</div>
```


breadcrumb.component.ts :
```ts
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, NavigationEnd, Router, RouterLink } from '@angular/router';
import { filter, map } from 'rxjs';

export interface Breadcrumb {
  label: string;
  url: string;
}

@Component({
  selector: 'app-breadcrumb',
  imports: [RouterLink],
  templateUrl: './breadcrumb.component.html'
})
export class BreadcrumbComponent implements OnInit {

  // ----- Propriétés -----

  breadcrumbs: Breadcrumb[] = [];


  // ----- Constructeur -----

  constructor(private router: Router, private route: ActivatedRoute) {}


  // ----- Méthodes -----

  ngOnInit(): void {
    this.router.events
      .pipe(
        filter(event => event instanceof NavigationEnd),
        map(() => this.buildBreadcrumb(this.route.root))
      )
      .subscribe(breadcrumbs => {
        this.breadcrumbs = breadcrumbs;
      });
  }

  private buildBreadcrumb(route: ActivatedRoute, url: string = '', breadcrumbs: Breadcrumb[] = []): Breadcrumb[] {
    const children: ActivatedRoute[] = route.children;

    if (children.length === 0) {
      return breadcrumbs;
    }

    for (const child of children) {
      const routeURL: string = child.snapshot.url.map(segment => segment.path).join('/');
      if (routeURL !== '') {
        url += `/${routeURL}`;
      }

      const label = child.snapshot.data['breadcrumb'];
      if (label) {
        breadcrumbs.push({ label, url });
      }

      this.buildBreadcrumb(child, url, breadcrumbs);
    }

    return breadcrumbs;
  }

}
```


app.routes.ts :
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


app.component.ts :
```ts
imports: [RouterOutlet, HeaderComponent, FooterComponent, BreadcrumbComponent]
```


app.component.html :
```html
<app-header/>

<div class="container">
  <app-breadcrumb/>

  <router-outlet/>
</div>

<app-footer/>
```