// src/app/pages/user/user.component.ts :

import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { UsersService } from '../../services/users.service';
import { User } from '../../models/user';

@Component({
  selector: 'app-user',
  imports: [RouterLink, CommonModule], // 👈 CommonModule pour éviter d’importer chaque pipe un par un (DatePipe, UpperCasePipe...), RouterLink pour liens
  templateUrl: './user.component.html'
})
export class UserComponent {

  // ========== Propriétés ==========

  user?: User;

  // ========== Constructeur ==========

  constructor(
    private route: ActivatedRoute, // 👈 Récupérer infos sur la route actuelle (les paramètres de l’URL, les query params...).
    private usersService: UsersService // 👈 Injection de dépendances pour créer une instance de UsersService pour utiliser sans instancier
  ) {}

  // ========== Méthodes ==========

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const id = Number(params.get('id'));
      if (id) {
        this.getUser(id);
      }
    });
  }

  getUser(id: number): void {
    this.usersService.getUserById(id).subscribe((data: User) => {
      this.user = data;
    });
  }

}