// src/app/pages/register/register.component.ts :

// ng generate component pages/register

import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { AbstractControl, FormControl, FormGroup, FormsModule, ReactiveFormsModule, ValidationErrors, Validators } from '@angular/forms';
import { UsersService } from '../../services/users.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  imports: [CommonModule, FormsModule, ReactiveFormsModule],
  templateUrl: './register.component.html'
})
export class RegisterComponent {

  // ========== Propriétés ==========

  register_form: FormGroup; // Formulaire pour inscription d'utilisateur
  errorMessage: string | null = null; // 💡 Pour afficher les erreurs serveur


  // ========== Constructeur ==========

  constructor(private userService: UsersService, private router: Router) {
    this.register_form = new FormGroup({ // Formulaire avec validation
      username: new FormControl('', [Validators.required, Validators.minLength(3)]),
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [Validators.required, Validators.minLength(4)]),
      confirm_password: new FormControl('', [Validators.required, Validators.minLength(4)])});
  }


  // ========== Méthodes ==========

  // ----- Create -----

  /**
   * Créer utilisateur
   */
  addUser(): void {
    if (this.register_form.valid) {
      
      if (this.register_form.value.password !== this.register_form.value.confirm_password) { // Vérification des mots de passe
        console.error("Les mots de passe ne correspondent pas !");
        return;
      }

      const newUser = {
        username: this.register_form.value.username,
        email: this.register_form.value.email,
        password: this.register_form.value.password
      };
  
      this.userService.addUser(newUser).subscribe({
        next: () => this.router.navigate(['/connexion']),
        error: (err) => {
          console.error('Erreur inscription :', err);
          this.errorMessage = err.error?.message || 'Erreur inconnue'; // 💡 Récupère le message envoyé par Spring
        }
      });
    }
  }

}