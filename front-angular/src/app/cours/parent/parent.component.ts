// src/app/cours/parent/parent.component.ts :

import { Component } from '@angular/core';
import { EnfantComponent } from '../enfant/enfant.component'; // 💡 Importer
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-parent',
  imports: [EnfantComponent, RouterLink], // 💡 Importer le composant enfant pour pouvoir communiquer
  templateUrl: './parent.component.html',
  styleUrl: './parent.component.css'
})
export class ParentComponent {

  message: string = 'Hello depuis le parent !'; // message vers l’enfant
  responseFromChild: string = ''; // message reçu depuis enfant

  handleEvent(event: string): void {
    console.log('Reçu de l’enfant :', event);
    this.responseFromChild = event;
  }

}