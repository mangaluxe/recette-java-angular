// src/app/pages/member/member.component.ts :

// ng generate component pages/member

import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { RouterLink } from '@angular/router';
import { User } from '../../models/user';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-member',
  imports: [CommonModule, RouterLink],
  templateUrl: './member.component.html'
})
export class MemberComponent {

  // ========== Propriétés ==========

  user: User | null = null;


  // ========== Constructeur ==========

  constructor(private authService: AuthService) { }


  // ========== Méthodes ==========

  ngOnInit(): void { // Appel automatique au chargement
    this.getUserProfile();
  }


  // ----- Afficher -----

  /**
   * Récupérer toutes les infos utilisateur
   */
  getUserProfile(): void {
    this.authService.getUserProfile().subscribe({
      next: (res) => this.user = res,
      error: (err) => console.error("Erreur profil:", err)
    });
  }


  // ========== Getters ==========

  /**
   * Afficher le nom d'utilisateur
   */
  get username(): string | null {
    return this.authService.getUsername();
  }


  /**
   * Afficher email de l'utilisateur
   */
  get email(): string | null {
    return this.authService.getEmail();
  }


  /**
   * Afficher le rôle
   */
  get roleName(): string | null {
    return this.authService.getRole();
  }

}