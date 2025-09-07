// src/app/app.component.ts :

import { Component } from '@angular/core'; // Importe le décorateur @Component, nécessaire pour déclarer une classe Angular comme un composant
import { RouterOutlet } from '@angular/router'; // Importe la directive <router-outlet/>, qui est utilisée pour afficher dynamiquement les pages selon la route active
import { HeaderComponent } from './partials/header/header.component'; // Importe le composant Header pour pouvoir utiliser <app-header/> dans le template
import { FooterComponent } from './partials/footer/footer.component';

@Component({
  selector: 'app-root', // Le nom de la balise HTML personnalisée associée à ce composant. Ici, <app-root> sera placé dans index.html → c’est le point d’entrée de l’application Angular.
  imports: [RouterOutlet, HeaderComponent, FooterComponent], // Liste des composants/directives que l’on peut utiliser dans le template de AppComponent
  templateUrl: './app.component.html',
  // styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'front-angular'; // Propriété publique du composant. Elle peut être utilisée dans le template avec {{ title }}
}

/*
Résumé des imports dans imports[] :
- RouterOutlet : directive spéciale pour le routing → <router-outlet/>
- HeaderComponent : permet d'afficher le header → <app-header/>
- FooterComponent : permet d'afficher le footer → <app-footer/>
*/