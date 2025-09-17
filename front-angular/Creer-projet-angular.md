## 0️⃣ ----- Créer nouveau projet -----

### Nouveau projet :
```bash
ng new nom_projet
```

No
css
N


### Si récupération d'un projet existant depuis Git :
```bash
npm install
```


### Vérifier version :
```bash
ng version
```
On peut aussi regarder dans le fichier package.json à la ligne : "@angular/core": "^19.1.0",


### Mises à Jour version : 
```bash
ng update @angular/cli --force
```


### Compiler :
```bash
ng build
```
Récupérer ensuite le contenu du dossier "dist/nom-projet/browser/" créé et le placer à la racine d'un serveur.


### Démarrer serveur et ouvrir automatiquement dans navigateur :
```bash
ng serve --open
```


## 1️⃣ ----- Création de nouveaux composants : -----

```bash
ng generate component pages/home
ng generate component pages/test
ng generate component partials/header
ng generate component partials/footer
```

src/app/partials/header/header.component.html :
```html
<header>
    <nav>
        <ul>
          <li><a routerLink="/">Accueil</a></li>
          <li><a routerLink="test">Test</a></li>
        </ul>
      </nav>
</header>
```

src/app/partials/header/header.component.ts :
```ts
imports: [RouterLink], // S'il y a des liens, on importe RouterLink
```

src/app/partials/footer/footer.component.html :
```html
<footer>
  <p>Mon Footer</p>
</footer>
```


## 2️⃣ ----- Mettre en place le layout global (app.component.html ) : -----

src/app/app.component.html :
```
<app-header/>

<div class="container">
    <!-- <app-breadcrumb/> -->
    <router-outlet/>
</div>

<app-footer/>
```

## 3️⃣ ----- Page HTML principale (src/index.html) : -----

src/index.html :
```html
<!doctype html>
<html lang="fr">
<head>
  <meta charset="utf-8">
  <title>Mon Site</title>
  <base href="/">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="icon" href="/assets/img/favicon.svg" type="image/svg+xml"> <!-- Dans public/assets/img/favicon.svg -->
</head>
<body>

  <app-root></app-root>

</body>
</html>
```


## 4️⃣ ----- Configurer le routing (app.routes.ts) : -----

src/app/app.routes.ts (Pour indiquer le chemin) :
```ts
export const routes: Routes = [
  {path: '', component: HomeComponent}, // http://localhost:4200/
  {path: 'test', component: TestComponent}, // http://localhost:4200/test
];
```


## 5️⃣ ----- Configuration du composant racine (app.component.ts) : -----

src/app/app.component.ts :
```ts
imports: [RouterOutlet, HeaderComponent, FooterComponent], /* Ici on importe les composants et directives nécessaires pour le template (<app-header/>, <router-outlet/>, <app-footer/>) */
```

> RouterOutlet : c’est la directive spéciale qui dit à Angular où afficher les pages selon la route (<router-outlet/>).

> HeaderComponent : pour pouvoir écrire <app-header/> dans app.component.html.

> FooterComponent : pour pouvoir écrire <app-footer/> dans app.component.html.