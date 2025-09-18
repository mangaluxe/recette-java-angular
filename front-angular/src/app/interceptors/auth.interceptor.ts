// src/app/interceptors/auth.interceptor.ts :

// ng generate interceptor interceptors/auth

import { HttpInterceptorFn } from '@angular/common/http';
import { AuthService } from '../services/auth.service';
import { inject } from '@angular/core';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  const authService = inject(AuthService);
  const token = authService.getToken();

  if (token && !req.url.includes('/connexion') && !req.url.includes('/inscription')) {
    const cloned = req.clone({
      setHeaders: {
        Authorization: `Bearer ${token}`,
      }
    });
    return next(cloned);
  }

  return next(req);
};

/*
Dans src/app/app.config.ts, ajouter :

export const appConfig: ApplicationConfig = {
  providers: [
    provideHttpClient(
      withInterceptors([authInterceptor]) // Intercepteur ajouté ici
    )
  ]
};

*/