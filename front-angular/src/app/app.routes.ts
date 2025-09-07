// src/app/app.routes.ts

import { Routes } from '@angular/router';
import { HomeComponent } from './pages/home/home.component';
import { BasesComponent } from './pages/bases/bases.component';
import { TestComponent } from './pages/test/test.component';
import { UsersComponent } from './pages/users/users.component';
import { UserComponent } from './pages/user/user.component';

export const routes: Routes = [
    {path: '', component: HomeComponent}, // http://localhost:4200/
    {path: 'bases', component: BasesComponent}, // http://localhost:4200/bases
    {path: 'test', component: TestComponent}, // http://localhost:4200/test
    {path: 'utilisateurs', component: UsersComponent}, // http://localhost:4200/utilisateurs
    { path: 'utilisateur/:id', component: UserComponent } // http://localhost:4200/utilisateur/1
];