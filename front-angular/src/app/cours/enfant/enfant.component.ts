// src/app/cours/enfant/enfant.component.ts :

import { Component, EventEmitter, Input, Output } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-enfant',
  imports: [RouterLink],
  templateUrl: './enfant.component.html',
  styleUrl: './enfant.component.css'
})
export class EnfantComponent {

  @Input() message: string = ''; // Reçoit du parent
  @Output() childEvent = new EventEmitter<string>(); // Émet vers le parent

  sendEvent(): void {
    this.childEvent.emit('Salut du child !');
  }

}