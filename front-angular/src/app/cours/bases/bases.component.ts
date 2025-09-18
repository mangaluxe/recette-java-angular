// src/app/cours/bases/bases.component.ts :

// ng generate component cours/bases

import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-bases',
  imports: [CommonModule, RouterLink, FormsModule, ReactiveFormsModule], // 👈 CommonModule pour éviter d’importer chaque pipe un par un (DatePipe, UpperCasePipe...), FormsModule pour formulaire
  templateUrl: './bases.component.html',
  styleUrl: './bases.component.css'
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

  text: string = "Bonjour";

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


  // ----- Menu déroulant (Dropdown) -----

  isMenuOpen = false;

  toggleMenu() {
    this.isMenuOpen = !this.isMenuOpen;
  }

  closeMenu() {
    this.isMenuOpen = false;
  }


  // ----- Gestion des événements -----

  pseudo: string = '';
  message: string = '';
  inputStatus: string = '';
  
  onClick(): void { // Quand on clique
    this.message = `Bouton cliqué ! Pseudo actuel: ${this.pseudo}`;
    console.log('[click] message:', this.message);
  }

  onInputChange(event: Event): void { // Dès qu'on tape dans le champ (même sans valider)
    const inputElement = event.target as HTMLInputElement;
    this.pseudo = inputElement.value;
    console.log('[input] pseudo:', this.pseudo);
  }

  onChange(event: Event): void { // Quand la valeur change et qu'on sort du champ
    const inputElement = event.target as HTMLInputElement;
    this.message = `Valeur changée: ${inputElement.value}`;
    console.log('[change] message:', this.message);
  }

  onBlur(): void { // Quand on quitte le champ
    this.inputStatus = 'Champ quitté';
    console.log('[blur] Champ quitté');
  }

  onFocus(): void { // Quand on clique ou tab dans le champ
    this.inputStatus = 'Champ sélectionné';
    console.log('[focus] Champ sélectionné');
  }

  onKeyUp(event: KeyboardEvent): void { // À chaque touche pressée
    const inputElement = event.target as HTMLInputElement;
    this.pseudo = inputElement.value;
    console.log('[keyup] pseudo:', this.pseudo);
  }


  // ----- Liaison bidirectionnelle (Two-Way Data Binding) -----
  
  // Il faut faire l'import : imports: [FormsModule]

  nom: string = '';


  // ----- Formulaires réactifs (Reactive Forms) -----

  // Il faut faire l'import : imports: [ReactiveFormsModule]

  form = new FormGroup({
    pseudo: new FormControl(''),
    email: new FormControl('')
  });

}
