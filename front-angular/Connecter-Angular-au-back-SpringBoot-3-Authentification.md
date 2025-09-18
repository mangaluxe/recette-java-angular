# ========== Authentification ==========

## 1️⃣ ----- Créer un auth.service.ts -----

```bash
ng generate service services/auth
```

src/app/services/auth.service.ts :

```ts
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';

export interface AuthResponse {
  token: string;
  username: string;
  email: string;
  roleName: string;
}

@Injectable({ // @Injectable = Service
  providedIn: 'root'
})
export class AuthService {

  // ========== Propriétés ==========

  private apiUrl = 'http://localhost:8080/api/auth'; // URL à adapter selon l'API backend
  private tokenKey = 'auth_token';


  // ========== Constructeur ==========

  constructor(private http: HttpClient) {}


  // ========== Méthodes ==========

  /**
   * Connexion utilisateur
   */
  login(credentials: { username: string; password: string }): Observable<AuthResponse> {
    return this.http.post<AuthResponse>(`${this.apiUrl}/connexion`, credentials).pipe(
      tap((res) => {
        if (res.token) {
          localStorage.setItem(this.tokenKey, res.token);
          localStorage.setItem("isLogged", 'true');
          localStorage.setItem("username", res.username);
          localStorage.setItem("email", res.email);
          localStorage.setItem("roleName", res.roleName);
        }
      })
    );
  }


  /**
   * Déconnexion
   */
  logout(): void {
    localStorage.removeItem(this.tokenKey);
    localStorage.removeItem("isLogged");
    localStorage.removeItem("username");
    localStorage.removeItem("email");
    localStorage.removeItem("roleName");
  }


  /**
   * Récupérer le token
   */
  getToken(): string | null {
    return localStorage.getItem(this.tokenKey);
  }


  /**
   * Vérifier si l'utilisateur est connecté
   */
  isAuthenticated(): boolean {
    return !!this.getToken();
  }


  /**
   * Vérifier si l'utilisateur est connecté
   */
  isLogged(): boolean {
    return localStorage.getItem('isLogged') === 'true';
  }


  /**
   * Vérifier si l'utilisateur est admin
   */
  isAdmin(): boolean {
    return localStorage.getItem('role') === 'admin';
  }

}
```


## 2️⃣ ----- Créer un auth.guard.ts -----

```bash
ng generate guard guards/auth

# Cocher "CanActivate" (pour bloquer l’accès à une page si l'utilisateur n'est pas connecté) avec touche Espace du clavier. Puis Entrée.
```

src/app/guards/auth.guard.ts :

```ts
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
```

app.routes.ts :
```ts
  { path: 'inscription', component: RegisterComponent, data: { title: "Inscription", breadcrumb: "Inscription" } }, // http://localhost:4200/inscription
  { path: 'connexion', component: LoginComponent, data: { title: "Connexion", breadcrumb: "Connexion" } }, // http://localhost:4200/connexion
  { path: 'admin', component: DashboardComponent, canActivate: [authGuard], data: { title: "Espace admin", breadcrumb: "Admin" } }, // http://localhost:4200/admin
```


## 3️⃣ ----- Créer un auth.interceptor.ts -----

```bash
ng generate interceptor interceptors/auth
```

src/app/interceptors/auth.interceptor.ts :

```ts
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
```

src/app/app.config.ts :

```ts
export const appConfig: ApplicationConfig = {
  providers: [
    provideZoneChangeDetection({ eventCoalescing: true }),
    provideRouter(routes),
    provideHttpClient(
      withInterceptors([authInterceptor]) // 💡 Intercepteur ajouté ici
    )
  ]
};
```


## 4️⃣ ----- Créer un composant Login -----

```bash
ng generate component pages/login
```

src/app/pages/login/login.component.ts :

```ts
@Component({
  selector: 'app-login',
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './login.component.html'
})
export class LoginComponent {

  // ========== Propriétés ==========

  login_form: FormGroup; // Formulaire pour inscription d'utilisateur
  errorMessage: string | null = null;


  // ========== Constructeur ==========
  
  constructor(
    private fb: FormBuilder  ,
    private authService: AuthService,
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
```

src/app/pages/login/login.component.html :

```html
<div class="container">
    <main>

        <h1 class="center">Connexion</h1>

        <form [formGroup]="login_form" (ngSubmit)="onSubmit()" class="form-box mt-5">

            <div>
                <label for="username">Pseudo :</label>
                <input id="username" type="text" formControlName="username">
            </div>
        
            <div>
                <label for="password">Mot de passe :</label>
                <input id="password" type="password" formControlName="password">
            </div>

            @if (errorMessage) {
                <div class="invalid">{{ errorMessage }}</div>
            }

            <div class="mt-2">
                <button type="submit" class="btn btn-success" [disabled]="login_form.invalid">Se connecter</button>
            </div>
        </form>

    </main>
</div>
```

src/app/partials/header/header.component.ts :

```ts
@Component({
  selector: 'app-header',
  imports: [RouterLink], // S'il y a des liens, on importe RouterLink
  templateUrl: './header.component.html',
  // styleUrl: './header.component.css'
})
export class HeaderComponent {

  // ========== Propriétés ==========

  // ========== Constructeur ==========

  constructor(private authService: AuthService) { }


  // ========== Méthodes ==========

  /**
   * Fermer le menu burger sur mobile au changement de page (car sur Angular c'est one-page, le menu reste comme si on n'a pas changé de page)
   */
  closeMenu() {
    const checkbox = document.querySelector('.nav-icon input') as HTMLInputElement;
    if (checkbox) {
      checkbox.checked = false;
    }
  }

  // ----- Authentification -----
  
  isLogged(): boolean {
    return this.authService.isLogged();
  }

  isAdmin(): boolean {
    return this.authService.isAdmin();
  }

  logout() {
    this.closeMenu();
    this.authService.logout();
    window.location.reload();
  }

}
```



```html
<ul>
    @if (!isLogged()) {
        <li><a routerLink="/connexion" (click)="closeMenu()">Connexion</a></li>
        <li><a routerLink="/inscription" (click)="closeMenu()">Inscription</a></li>
    }
    @else {
        @if (isAdmin()) {
            <li><a routerLink="/admin" (click)="closeMenu()">Admin</a></li>
        }
        <li><a (click)="logout()" class="cursor">Deconnexion</a></li>
    }
</ul>
```