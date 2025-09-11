import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CategoriesService {

  // ========== Propriétés ==========

  private apiUrl = 'http://localhost:8080/api/category'; // URL à remplacer en fonction de l'API backend

  // ========== Constructeur ==========

  constructor(private http: HttpClient) {}

  // ========== Méthodes ==========

  // ----- Read -----

  getCategories(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }

}
