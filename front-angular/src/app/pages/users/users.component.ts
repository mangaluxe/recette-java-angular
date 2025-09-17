// src/app/pages/users/users.component.ts :

// ng generate component pages/users

import { Component, OnInit } from '@angular/core';
import { UsersService } from '../../services/users.service';
import { User } from '../../models/user';
import { CommonModule, DatePipe } from '@angular/common';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-users',
  imports: [RouterLink, CommonModule], // 👈 CommonModule pour éviter d’importer chaque pipe un par un (DatePipe, UpperCasePipe...), RouterLink pour liens
  templateUrl: './users.component.html'
})
export class UsersComponent implements OnInit {

  // ========== Propriétés ==========

  // users: any[] = []; // Sans modele User
  users: User[] = []; // Retourne un tableau d'utilisateurs, par défaut un tableau vide

  // ========== Constructeur ==========

  constructor(private usersService: UsersService) {} // Utilisation du service UsersService

  // ========== Méthodes ==========

  ngOnInit(): void {
    this.usersService.getUsers().subscribe({
      next: (data) => this.users = data,
      error: (err) => console.error('Erreur chargement utilisateurs :', err)
    });
  }

}