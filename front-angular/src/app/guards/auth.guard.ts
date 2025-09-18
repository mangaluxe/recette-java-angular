// src/app/guards/auth.guard.ts :

/*
ng generate guard guards/auth

Cocher "CanActivate" (pour bloquer l’accès à une page si l'utilisateur n'est pas connecté) avec touche Espace du clavier. Puis Entrée.
*/

import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

export const authGuard: CanActivateFn = (route, state) => {
  const router = inject(Router);
  const authService = inject(AuthService);

  const token = authService.getToken();

  if (!token) {
    // alert("Vous n'êtes pas autorisé à accéder à cette route")
    // router.navigate(['/connexion'])
    // return false

    return router.createUrlTree(['/connexion']);
  } 
  return true
};