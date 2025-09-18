// src/app/pages/admin/admin-user-edit/admin-user-edit.component.ts :

// ng generate component pages/admin/admin-user-edit

import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { UsersService } from '../../../services/users.service';
import { User } from '../../../models/user';
import { Role } from '../../../models/role';

// export interface Role { // Mis dans : src/app/models/role.ts
//   id: number;
//   roleName: string;
// }

@Component({
  selector: 'app-admin-user-edit',
  imports: [RouterLink, CommonModule, FormsModule, ReactiveFormsModule],
  templateUrl: './admin-user-edit.component.html'
})
export class AdminUserEditComponent implements OnInit {

  // ========== Propriétés ==========

  user_form!: FormGroup; // ! = "Je te promets que ce sera défini plus tard".
  userId!: number; // id utilisateur récupéré dans l’URL
  user!: User; // Utilisateur à modifier

  roles: Role[] = []; 


  // ========== Constructeur ==========

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private usersService: UsersService
  ) {}


  // ========== Méthodes ==========

  ngOnInit(): void {
    // Récupère l'id depuis l'URL
    this.userId = Number(this.route.snapshot.paramMap.get('id'));

    // Crée le formulaire vide
    this.user_form = this.fb.group({
      username: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      roleId: ['', Validators.required]
    });

    // Charger les rôles
    this.usersService.getRoles().subscribe({
      next: (res) => {
        this.roles = res;
      },
      error: (err) => console.error('Erreur chargement rôles :', err)
    });

    // Charge l’utilisateur à modifier
    this.usersService.getUserById(this.userId).subscribe({
      next: (res) => {
        this.user = res;
        // Pré-remplit le formulaire
        this.user_form.patchValue({
          username: res.username,
          email: res.email,
          roleId: res.roleId
        });
      },
      error: (err) => console.error('Erreur chargement utilisateur :', err)
    });
  }


  // ----- Update -----

  /**
   * Modifier un utilisateur
   */
  updateUser(): void {
    if (this.user_form.valid) {
      const updatedUser: User = { ...this.user, ...this.user_form.value };

      this.usersService.updateUser(this.userId, updatedUser).subscribe({
        next: () => {
          this.router.navigate(['/admin/admin-utilisateurs']);
        },
        error: (err) => console.error('Erreur modification utilisateur :', err)
      });
    }
  }

}