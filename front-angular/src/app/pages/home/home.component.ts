// src/app/pages/home/home.component.ts :

// ng generate component pages/home

import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-home',
  imports: [RouterLink, CommonModule],
  templateUrl: './home.component.html',
  // styleUrl: './home.component.css'
})
export class HomeComponent {

  // ========== Propriétés ==========


  // ========== Constructeur ==========

  constructor(private authService: AuthService) { }


  // ========== Méthodes ==========

  /**
   * Vérifier si l'utilisateur est connecté
   */
  isLogged(): boolean {
    return this.authService.isLogged();
  }


  /**
   * Vérifier si Admin ou Super Admin
   */
  isAdmin(): boolean {
    return this.authService.isAdmin();
  }

}