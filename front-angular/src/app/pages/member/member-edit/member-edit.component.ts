// src/app/pages/member/member-edit/member-edit.component.ts :

// ng generate component pages/member/member-edit

import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { UsersService } from '../../../services/users.service';

@Component({
  selector: 'app-member-edit',
  imports: [CommonModule, RouterLink, FormsModule, ReactiveFormsModule],
  templateUrl: './member-edit.component.html'
})
export class MemberEditComponent implements OnInit {

  // ========== Propriétés ==========

  profile_form!: FormGroup;// ! = "Je te promets que ce sera défini plus tard".
  userId!: number; // id utilisateur actuel
  errorMessage: string = ''; // Message d'erreur affiché dans le template


  // ========== Constructeur ==========

  constructor(
    private fb: FormBuilder,
    private usersService: UsersService,
    private router: Router
  ) { }


  // ========== Méthodes ==========

  ngOnInit(): void {
    this.userId = Number(localStorage.getItem('userId')); // Récupère l'id de l'utilisateur connecté depuis le localStorage

    this.profile_form = this.fb.group({ // Crée le formulaire
      email: ['', [Validators.required, Validators.email]],
      oldPassword: [''],
      newPassword: [''],
      confirmPassword: ['']
    });

    this.usersService.getUserById(this.userId).subscribe({
      next: (user) => {
        this.profile_form.patchValue({ email: user.email }); // Pré-remplit le formulaire avec l'email de l'utilisateur
      }
    });
  }


  // ----- Update -----

  /**
   * Modifier utilisateur lui-même (profil)
   */
  updateProfile(): void {
    this.errorMessage = ''; // Réinitialise le message d'erreur
    if (this.profile_form.invalid) return; // Ne fait rien si le formulaire est invalide

    const { email, oldPassword, newPassword, confirmPassword } = this.profile_form.value;

    // Si l'utilisateur veut changer de mot de passe
    if (newPassword) {
      if (!oldPassword) { // Vérifie que l'ancien mot de passe est fourni
        this.errorMessage = "Saisir l'ancien mot de passe.";
        return;
      }

      if (newPassword !== confirmPassword) { // Vérifie que la confirmation correspond au nouveau mot de passe
        this.errorMessage = "Les nouveaux mots de passe ne correspondent pas !";
        return;
      }
    }

    // Préparer le DTO
    const dto: any = { email };
    if (oldPassword && newPassword) { // Si l’utilisateur veut changer le mot de passe, ajouter old + new
      dto.oldPassword = oldPassword;
      dto.password = newPassword;
    }

    this.usersService.updateUserProfile(this.userId, dto).subscribe({
      next: (res) => {
        this.router.navigate(['/membre']);
      },
      error: (err) => {
        this.errorMessage = err.error?.message || "Ancien mot de passe incorrect";
      }
    });
  }

}