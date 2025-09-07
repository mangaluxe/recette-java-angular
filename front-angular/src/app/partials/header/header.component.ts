// src/app/partials/header/header.component.ts

// ng generate component partials/header
// ng generate component partials/footer

import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-header',
  imports: [RouterLink], // S'il y a des liens, on importe RouterLink
  templateUrl: './header.component.html',
  // styleUrl: './header.component.css'
})
export class HeaderComponent {

}
