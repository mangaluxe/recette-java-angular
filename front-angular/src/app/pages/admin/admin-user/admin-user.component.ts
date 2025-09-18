// src/app/pages/admin/admin-user/admin-user.component.ts :

// ng generate component pages/admin/admin-user

import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { User } from '../../../models/user';
import { UsersService } from '../../../services/users.service';

@Component({
  selector: 'app-admin-user',
  imports: [RouterLink, CommonModule],
  templateUrl: './admin-user.component.html'
})
export class AdminUserComponent implements OnInit {

  // ========== Propriétés ==========

  users: User[] = []; // Retourne un tableau d'utilisateurs, par défaut un tableau vide


  // ========== Constructeur ==========

  constructor(private usersService: UsersService) {} // Utilisation du service UsersService


  // ========== Méthodes ==========

  ngOnInit(): void { // Appel automatique au chargement
    this.getUsers();
  }


  // ----- Read -----

  /**
   * Récupérer tous les utilisateurs
   */
  getUsers(): void {
    this.usersService.getUsers().subscribe({
      next: (res) => {
        this.users = res;
      },
      error: (err) => {
        console.error('Erreur chargement utilisateurs :', err);
      }
    });
  }


  // ----- Delete -----

  /**
   * Supprimer un utilisateur
   */
  deleteUser(id: number): void {
    this.usersService.deleteUser(id).subscribe({
      next: () => {
        // console.log('Utilisateur supprimé avec succès');
        this.users = this.users.filter(user => user.id !== id); // Mettre à jour la liste localement
      },
      error: (err) => {
        console.error('Erreur suppression d\'utilisateur :', err);
      }
    });
  }

}