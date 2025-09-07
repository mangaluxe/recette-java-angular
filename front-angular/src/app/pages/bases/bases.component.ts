// src/app/pages/bases/bases.component.ts :

// ng generate component pages/bases

import { CommonModule, DatePipe, DecimalPipe, LowerCasePipe, UpperCasePipe } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-bases',
  // imports: [UpperCasePipe, LowerCasePipe, DatePipe, DecimalPipe, FormsModule],
  imports: [CommonModule, FormsModule], // 👈 CommonModule pour éviter d’importer chaque pipe un par un (DatePipe, UpperCasePipe...), FormsModule pour formulaire
  templateUrl: './bases.component.html',
  // styleUrl: './bases.component.css'
})
export class BasesComponent {

  // ----- Conditions -----

  a: number = 40;
  b: number = 50;

  today: string = "Mardi";


  // ----- Tableaux et Boucles -----

  utilisateurs: string[] = ['Terry', 'Andy', 'Joe']; // Tableau de chaînes de caractères

  fruits = [ // Tableau d'objets
    { nom: 'Pomme' },
    { nom: 'Poire' },
    { nom: 'Pêche' }
  ];


  // ----- Interpolation de données -----

  name: string = "Tom"; // Pour les propriétés d'un composant Angular, on ne met pas let ou const


  // ----- UpperCasePipe, LowerCasePipe, DatePipe, DecimalPipe -----

  message: string = "Bonjour";

  today2: Date = new Date();

  pi: number = Math.PI;


  // ----- Liaison de données (Data Binding) -----

  monImage: string = "https://picsum.photos/200/300";
  color: object = {
    color: "green",
    backgroundColor: "pink"
  }

  maClassA: string = 'green'; // C'est utile que si on veut changer dynamiquement la valeur de maClassA dans le TypeScript. Sinon, on peut mettre class="maClassA" directement dans le HTML
  maClassB: string = 'red';


  // ----- Méthodes et interactions utilisateur -----

  isLogged: boolean = true;

  toggleLogged() : void {
    this.isLogged = !this.isLogged;
  }

  nb: number = 0;

  incrementCount(): void {
    this.nb++;
  }


  // ----- Liaison bidirectionnelle (Two-Way Data Binding) -----
  
  // Il faut faire l'import : imports: [FormsModule]

  pseudo: string = '';

}
