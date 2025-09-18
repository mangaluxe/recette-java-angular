// src/app/services/auth.service.ts :

// ng generate service services/auth

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
          localStorage.setItem("isLogged", "true");
          localStorage.setItem("username", res.username);
          localStorage.setItem("email", res.email);
          localStorage.setItem("roleName", res.roleName);
        }
      })
    );
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
  isLogged(): boolean {
    // return !!this.getToken();
    return localStorage.getItem("isLogged") === "true";
  }


  /**
   * Vérifier si Admin ou Super Admin
   */
  isAdmin(): boolean {
    // return localStorage.getItem("roleName") === "Admin"; // Vérifier si Admin

    const roleName = localStorage.getItem("roleName");
    return roleName === "Admin" || roleName === "Super Admin"; // Vérifier si Admin ou Super Admin
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


  // ----- Afficher -----

  /**
   * Afficher le nom d'utilisateur
   */
  getUsername(): string | null {
    return localStorage.getItem("username");
  }


  /**
   * Afficher le rôle
   */
  getRole(): string | null {
    return localStorage.getItem("roleName");
  }

}