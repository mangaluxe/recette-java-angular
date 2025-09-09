import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { AbstractControl, FormControl, FormGroup, FormsModule, ReactiveFormsModule, ValidationErrors, Validators } from '@angular/forms';
import { UsersService } from '../../services/users.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-create',
  imports: [CommonModule, FormsModule, ReactiveFormsModule],
  templateUrl: './user-create.component.html'
})
export class UserCreateComponent {

  // ========== Propriétés ==========

  register_form: FormGroup; // Formulaire pour inscription d'utilisateur


  // ========== Constructeur ==========

  constructor(private userService: UsersService, private router: Router) {
    this.register_form = new FormGroup({ // Formulaire avec validation
      username: new FormControl('', [Validators.required, Validators.minLength(3)]),
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [Validators.required, Validators.minLength(4)]),
      confirm_password: new FormControl('', [Validators.required])
    }, { validators: UserCreateComponent.passwordMatchValidator });
  }


  // ========== Méthodes ==========

  // ----- Create -----

  addUser(): void {
    if (this.register_form.valid) {
      const newUser = {
        username: this.register_form.value.username,
        email: this.register_form.value.email,
        password: this.register_form.value.password,
        roleId: 1 // 👈 rôle par défaut "Membre"
      };
  
      this.userService.addUser(newUser).subscribe({
        next: () => this.router.navigate(['/bases']),
        error: (err) => console.error('Erreur inscription :', err),
      });
    }
  }
  

  // ----- Vérification -----

  /**
   * Vérifier répétition de mot de passe
   */
  static passwordMatchValidator(control: AbstractControl): ValidationErrors | null {
    const group = control as FormGroup;
    const password = group.get('password')?.value;
    const confirmPassword = group.get('confirm_password')?.value;

    return password === confirmPassword ? null : { mismatch: true };
  }

  /*
  AbstractControl	: Contrôle générique, ici casté en FormGroup.
  ValidationErrors : Objet { [clé: string]: any } représentant des erreurs
  */

}
