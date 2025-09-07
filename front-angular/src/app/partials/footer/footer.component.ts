// src/app/partials/footer/footer.component.ts

import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-footer',
  imports: [RouterLink], // S'il y a des liens, on importe RouterLink
  templateUrl: './footer.component.html',
  // styleUrl: './footer.component.css'
})
export class FooterComponent {

}
