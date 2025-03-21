import { Component } from '@angular/core';
import { RouterModule } from '@angular/router';
import { NavbarComponent } from './navbar/navbar.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterModule, NavbarComponent], // Import RouterModule and NavbarComponent
  template: `
    <app-navbar></app-navbar> <!-- Use the NavbarComponent here -->
    <router-outlet></router-outlet>  <!-- This is the placeholder for routing -->
  `,
  styleUrls: ['./app.component.css']
})
export class AppComponent {}


// import { Component } from '@angular/core';
// import { RouterModule } from '@angular/router';
// import { NavbarComponent } from './navbar/navbar.component';

// @Component({
//   selector: 'app-root',
//   standalone: true,
//   imports: [RouterModule,NavbarComponent],  // Ensure RouterModule is imported here
//   template: `
//     <nav>
//       <a routerLink="/login">Logsssin</a> |
//       <a routerLink="/pics">View Pictures</a> |
//       <a routerLink="/upload">Upload Picture</a>
//     </nav>
//     <router-outlet></router-outlet>  <!-- This is the placeholder for routing -->
//   `,
//   styleUrls: ['./app.component.css']
// })
// export class AppComponent {}

// import { Component } from '@angular/core';
// import { RouterModule } from '@angular/router';

// @Component({
//   selector: 'app-root',
//   standalone: true,
//   imports: [RouterModule],
//   template: `
//     <nav>
//       <a routerLink="/login">Login</a> |
//       <a routerLink="/pics">View Pictures</a> |
//       <a routerLink="/upload">Upload Picture</a>
//     </nav>
//     <router-outlet></router-outlet>
//   `,
//   styleUrls: ['./app.component.css']
// })
// export class AppComponent {}

// import { Component } from '@angular/core';
// import { RouterOutlet } from '@angular/router';

// @Component({
//   selector: 'app-root',
//   standalone: true,
//   imports: [RouterOutlet,RouterOutlet],
//   templateUrl: './app.component.html',
//   styleUrl: './app.component.css'
// })
// export class AppComponent {
//   title = 'angprj';
// }
