// src/app/pages/home/home.component.ts :

// ng generate component pages/home

import { CommonModule } from '@angular/common';
import { Component, inject, OnInit, signal } from '@angular/core';
import { RouterLink } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-home',
  imports: [RouterLink, CommonModule],
  templateUrl: './home.component.html',
  // styleUrl: './home.component.css'
})
export class HomeComponent implements OnInit {

  // ========== Propriétés ==========

  // username = '';
  username = signal<string>(''); // 💥 Avec signal

  ngOnInit(): void {
    // this.username = localStorage.getItem('username') || '';
    this.username.set(localStorage.getItem('username') || ''); // 💥 Avec signal
  }

  private readonly authService = inject(AuthService); // Nouvelle façon d'injecter le service


  // ========== Constructeur ==========

  // constructor(private authService: AuthService) {} // Ancienne façon d'injecter le service



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


  // getUsername(): string | null {
  //   return this.authService.getUsername(); // C'est mieux de le stocker dans une propriété username dans ngOnInit()
  // }

}