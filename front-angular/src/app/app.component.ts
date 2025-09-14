// src/app/app.component.ts (Composant principal) :

import { Component, OnInit } from '@angular/core'; // Importe le décorateur @Component, nécessaire pour déclarer une classe Angular comme un composant
import { ActivatedRoute, NavigationEnd, Router, RouterOutlet } from '@angular/router'; // Importe la directive <router-outlet/>, qui est utilisée pour afficher dynamiquement les pages selon la route active
import { HeaderComponent } from './partials/header/header.component'; // Importe le composant Header pour pouvoir utiliser <app-header/> dans le template
import { FooterComponent } from './partials/footer/footer.component';
import { Title } from '@angular/platform-browser';
import { filter, map, mergeMap } from 'rxjs';
import { BreadcrumbComponent } from './components/breadcrumb/breadcrumb.component';

@Component({
  selector: 'app-root', // Le nom de la balise HTML personnalisée associée à ce composant. Ici, <app-root> sera placé dans index.html → c’est le point d'entrée de l’application Angular.
  imports: [RouterOutlet, HeaderComponent, FooterComponent, BreadcrumbComponent], // Liste des composants/directives que l'on peut utiliser dans le template de AppComponent
  templateUrl: './app.component.html'
})
export class AppComponent implements OnInit {
  // title = 'front-angular'; // Propriété publique du composant. Elle peut être utilisée dans le template avec {{ title }}

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

/*
Résumé des imports dans imports[] :
- RouterOutlet : directive spéciale pour le routing → <router-outlet/>
- HeaderComponent : permet d'afficher le header → <app-header/>
- FooterComponent : permet d'afficher le footer → <app-footer/>
*/