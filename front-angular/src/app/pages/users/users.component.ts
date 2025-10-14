// src/app/pages/users/users.component.ts :

// ng generate component pages/users

import { Component, inject, OnInit, signal } from '@angular/core';
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
  // users: User[] = []; // Retourne un tableau d'utilisateurs, par défaut un tableau vide
  users = signal<User[]>([]); // 💥 Déclaration d’un signal

  private readonly usersService = inject(UsersService); // Nouvelle façon d'injecter le service


  // ========== Constructeur ==========

  // constructor(private usersService: UsersService) {} // Utilisation du service UsersService


  // ========== Méthodes ==========

  ngOnInit(): void {
    this.usersService.getUsers().subscribe({
      // next: (data) => this.users = data,
      next: (data) => this.users.set(data), // 💥 .set() met à jour le signal
      error: (err) => console.error('Erreur chargement utilisateurs :', err)
    });
  }

}