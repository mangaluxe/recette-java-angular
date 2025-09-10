## 1️⃣ ----- Activer CORS dans Spring Boot : -----

Ajouter ce fichier config/WebConfig.java :

```java
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) { // Autoriser les requêtes CORS
        registry.addMapping("/**")  // Applique la configuration à toutes les API (toutes les routes)
                .allowedOrigins("http://localhost:3000", "http://localhost:4200", "http://localhost") // Autorise React, Angular et noms de domaine
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Les méthodes autorisées
                .allowedHeaders("*") // Autorise tous les headers
                .allowCredentials(true); // Autorise les cookies, si nécessaire
    }

}
```


## 2️⃣ ----- Créer un modèle dans Angular -----

src/app/models/user.ts :

```ts
export interface User {
  id: number;
  username: string;
  email: string;
  createdAt: string; // LocalDateTime → string JSON
  roleName: string;
}
```


## 3️⃣ ----- Créer un service Angular pour les appels HTTP -----

```bash
ng generate service services/users
```

src/app/services/users.service.ts :

```ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from '../models/user';

// export interface User { // On peut mettre le model directement ici ou dans un fichier séparé : src/app/models/user.ts
//   id: number;
//   username: string;
//   email: string;
//   createdAt: string; // LocalDateTime → string JSON
//   roleName: string;
// }

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  // ========== Propriétés ==========

  private apiUrl = 'http://localhost:8080/api/user'; // URL à remplacer en fonction de l'API backend

  // ========== Constructeur ==========

  constructor(private http: HttpClient) {}

  // ========== Méthodes ==========

  // ----- Read -----

  // getUsers(): Observable<any[]> { // Sans modele User
  //   return this.http.get<any[]>(this.apiUrl);
  // }

  /**
   * Récupérer tous les utilisateurs
   */
  getUsers(): Observable<User[]> { // Observable : représente un résultat asynchrone (comme une requête HTTP)
    return this.http.get<User[]>(this.apiUrl);
  }

  /**
   * Récupérer un utilisateur par id
   */
  getUserById(id: number): Observable<User> {
    return this.http.get<User>(`${this.apiUrl}/${id}`);
  }
  
  // ----- Create -----

  /**
   * Créer un utilisateur
   */
  createUser(user: User): Observable<User> {
    return this.http.post<User>(this.apiUrl, user);
  }

}
```


## 4️⃣ ----- Importer HttpClient dans Angular -----

Pour qu'Angular puisse appeler un API Spring Boot.

src/app/app.config.ts :

```ts
import { provideHttpClient } from '@angular/common/http';

export const appConfig: ApplicationConfig = {
  providers: [
    provideHttpClient()
  ]
};
```


## 5️⃣ ----- Utiliser le service Angular dans un composant -----

```bash
ng generate component pages/users

ng generate component pages/user

ng generate component pages/register
```

Exemple dans UsersComponent : src/app/pages/users/users.component.ts :

```ts
@Component({
  selector: 'app-users',
  // imports: [DatePipe], // 👈 DatePipe pour formater les dates
  imports: [CommonModule], // 👈 CommonModule pour éviter d’importer chaque pipe un par un (date, majuscule)
  templateUrl: './users.component.html'
})
export class UsersComponent implements OnInit {

  // ========== Propriétés ==========

  // users: any[] = []; // Sans modele User
  users: User[] = [];

  // ========== Constructeur ==========

  constructor(private usersService: UsersService) {}

  // ========== Méthodes ==========

  ngOnInit(): void {
    this.usersService.getUsers().subscribe({
      next: (data) => this.users = data,
      error: (err) => console.error('Erreur chargement utilisateurs:', err)
    });
  }

}
```

src/app/pages/users/users.component.html :

```html
<div class="container">
    <main>

        <h1 class="center">Liste d'utilisateurs</h1>

        <div class="table-list-head">
            <div class="e">Utilisateur</div>
            <div class="e">Email</div>
            <div class="e">Date de création</div>
            <div class="e">Rôle</div>
        </div>
        <ul class="table-list">

            @for (user of users; track user) {
                <li>
                    <div class="e"><a [routerLink]="['/utilisateur', user.id]">{{ user.username }}</a></div>
                    <div class="e">{{ user.email }}</div>
                    <div class="e">{{ user.createdAt | date:'dd/MM/yyyy HH:mm' }}</div>
                    <div class="e">{{ user.roleName }}</div>
                </li>
            }
            @empty {
                <li>Aucun livre (ou vous n'avez pas lancé l'application back-end)</li>
            }

        </ul>

    </main>
</div>
```

Exemple dans UserComponent : src/app/pages/user/user.component.ts :

```ts
@Component({
  selector: 'app-user',
  imports: [RouterLink, CommonModule], // 👈 CommonModule pour éviter d’importer chaque pipe un par un (DatePipe, UpperCasePipe...), RouterLink pour liens
  templateUrl: './user.component.html'
})
export class UserComponent {
  
  // ========== Propriétés ==========

  user?: User; // Retourne utilisateur ou undefined
  // user: User | undefined; // Equivalent à ci-dessus

  // ========== Constructeur ==========

  constructor(
    private route: ActivatedRoute, // 👈 Récupérer infos sur la route actuelle (les paramètres de l’URL, les query params...).
    private usersService: UsersService // 👈 Injection de dépendances pour créer une instance de UsersService pour utiliser sans 
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
```

src/app/pages/user/user.component.html :
```html
<div class="container">
    <main>
        @if (user) {
            <h1 class="text-center">{{ user.username }}</h1>
            <p>Email : {{ user.email }}</p>
            <p>Date de création : {{ user.createdAt | date:'dd/MM/yyyy HH:mm'  }}</p>
            <p>Rôle : {{ user.roleName }}</p>
        }
        @else {
            <p>Utilisateur introuvable</p>
        }
    </main>
</div>
```

src/app/app.routes.ts :

```ts
export const routes: Routes = [
    {path: '', component: HomeComponent}, // http://localhost:4200/
    {path: 'utilisateurs', component: UsersComponent}, // http://localhost:4200/utilisateurs
    { path: 'utilisateur/:id', component: UserComponent } // http://localhost:4200/utilisateur/1
];
```

src/app/partials/header/header.component.html :

Ajouter lien :

```html
<li><a routerLink="/utilisateurs">Utilisateurs</a></li>
```