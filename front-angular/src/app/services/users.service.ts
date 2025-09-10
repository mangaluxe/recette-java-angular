// src/app/services/users.service.ts :

// ng generate service services/users

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../models/user';

// export interface User { // On peut mettre le model directement ici ou dans un fichier séparé : src/app/models/user.ts
//   id: number;
//   username: string;
//   email: string;
//   createdAt: string; // LocalDateTime → string JSON
//   roleName: string;
// }

@Injectable({ // @Injectable : transforme la classe en service Angular injectable partout
  providedIn: 'root' // Veut dire que ce service est singleton et disponible partout dans l’app sans avoir besoin de le déclarer ailleurs
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

  // addUser(user: any): Observable<any> {
  //   return this.http.post<any>(this.apiUrl, user);
  // }

  /**
   * Créer un utilisateur
   */
  // addUser(user: User): Observable<User> {
  addUser(user: Partial<User>): Observable<User> { // Partial<User> : envoyer un objet partiel (ex. sans id, sans createdAt qui seront générés par le backend)
    return this.http.post<User>(this.apiUrl, user);
  }

}
