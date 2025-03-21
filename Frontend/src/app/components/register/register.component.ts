import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  user = {
    username: '',
    password: '',
    roleId: '' 

  };

  constructor(private authService: AuthService, private router: Router) { }

  register() {
    this.authService.register(this.user).subscribe({
      next: () => {
        console.log('Registration successful');
        this.router.navigate(['/login']);
      },
      error: (err) => {
        console.error('Registration error:', err);
      }
    });
  }
}
// import { Component } from '@angular/core';
// import { CommonModule } from '@angular/common';
// import { FormsModule } from '@angular/forms';
// import { AuthService } from '../../services/auth.service';
// import { Router } from '@angular/router';

// @Component({
//   selector: 'app-register',
//   standalone: true,
//   imports: [CommonModule, FormsModule], // Add necessary modules
//   templateUrl: './register.component.html',
//   styleUrls: ['./register.component.css']
// })
// export class RegisterComponent {
//   user: any = {};

//   constructor(private authService: AuthService, private router: Router) { }

//   register() {
//     this.authService.register(this.user).subscribe(() => {
//       this.router.navigate(['/login']);
//     }, error => {
//       console.error('Registration error: ', error);
//     });
//   }
// }

// import { Component } from '@angular/core';

// @Component({
//   selector: 'app-register',
//   standalone: true,
//   imports: [],
//   templateUrl: './register.component.html',
//   styleUrl: './register.component.css'
// })
// export class RegisterComponent {

// }
