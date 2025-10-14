// src/app/pages/login/login.component.ts :

// ng generate component pages/login

import { CommonModule } from '@angular/common';
import { Component, inject } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './login.component.html'
})
export class LoginComponent {

  // ========== Propriétés ==========

  login_form: FormGroup; // Formulaire pour inscription d'utilisateur
  errorMessage: string | null = null;

  private readonly authService = inject(AuthService); // Nouvelle façon d'injecter le service


  // ========== Constructeur ==========
  
  constructor(
    private fb: FormBuilder  ,
    // private authService: AuthService,
    private router: Router
  ) {
    this.login_form = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required],
    });
  }


  // ========== Méthodes ==========

  onSubmit(): void {
    if (this.login_form.valid) {
      this.authService.login(this.login_form.value).subscribe({
        next: () => {
          this.errorMessage = null;
          this.router.navigate(['/']); // Redirection après connexion
        },
        error: (err) => {
          console.error('Erreur de connexion :', err);
          this.errorMessage = 'Erreur pseudo ou mot de passe';
        }
      });
    }
  }

}