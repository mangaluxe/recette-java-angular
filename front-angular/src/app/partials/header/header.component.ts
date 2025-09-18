// src/app/partials/header/header.component.ts

// ng generate component partials/header
// ng generate component partials/footer

import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-header',
  imports: [RouterLink], // S'il y a des liens, on importe RouterLink
  templateUrl: './header.component.html',
  // styleUrl: './header.component.css'
})
export class HeaderComponent {

  // ========== Propriétés ==========

  // ========== Constructeur ==========

  constructor(private authService: AuthService) { }


  // ========== Méthodes ==========

  /**
   * Fermer le menu burger sur mobile au changement de page (car sur Angular c'est one-page, le menu reste comme si on n'a pas changé de page)
   */
  closeMenu() {
    const checkbox = document.querySelector('.nav-icon input') as HTMLInputElement;
    if (checkbox) {
      checkbox.checked = false;
    }
  }

  // ----- Authentification -----
  
  /**
   * Vérifier si l'utilisateur est connecté
   */
  isLogged(): boolean {
    return this.authService.isLogged();
  }


  /**
   * Vérifier si Admin
   */
  isAdmin(): boolean {
    return this.authService.isAdmin();
  }


  /**
   * Déconnexion
   */
  logout() {
    this.closeMenu();
    this.authService.logout();
  }


  // ----- Afficher -----

  /**
   * Afficher le nom d'utilisateur
   */
  get username(): string | null {
    return this.authService.getUsername();
  }


  /**
   * Afficher le rôle
   */
  get roleName(): string | null {
    return this.authService.getRole();
  }

}