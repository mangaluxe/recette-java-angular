// src/app/services/users.service.ts :

// ng generate service services/users

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../models/user';
import { Role } from '../models/role';

// export interface User { // On peut mettre le model directement ici ou dans un fichier séparé : src/app/models/user.ts
//   id: number;
//   username: string;
//   email: string;
//   createdAt: string; // LocalDateTime → string JSON
//   roleName: string;
// }

@Injectable({ // @Injectable = Service. Utilisation de ce service dans un composant : constructor(private usersService: UsersService) {}
  providedIn: 'root' // Veut dire que ce service est singleton et disponible partout dans l’app sans avoir besoin de le déclarer ailleurs
})
export class UsersService {

  // ========== Propriétés ==========

  private apiUrl = 'http://localhost:8080/api/user'; // URL à adapter selon l'API backend


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


  /**
   * Récupérer tous les rôles
   */
  getRoles(): Observable<Role[]> {
    return this.http.get<Role[]>('http://localhost:8080/api/role');
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


  // ----- Update -----

  /**
   * Modifier un utilisateur (par un admin)
   */
  updateUser(id: number, user: Partial<User>): Observable<User> {
    return this.http.put<User>(`${this.apiUrl}/${id}`, user);
  }


  /**
   * Modifier utilisateur par lui-même : email et/ou mot de passe
   */
  updateUserProfile(userId: number, dto: { email?: string; password?: string }) {
    return this.http.put<User>(`${this.apiUrl}/profile/${userId}`, dto);
  }


  // ----- Delete -----

  /**
   * Supprimer un utilisateur
   */
  deleteUser(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

}
