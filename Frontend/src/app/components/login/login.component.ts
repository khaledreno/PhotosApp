



import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  credentials: any = {};

  constructor(private authService: AuthService, private router: Router) { }

  login() {
    this.authService.login(this.credentials).subscribe(() => {
      this.router.navigate(['/pics']);
    }, error => {
      console.error('Login error: ', error);
    });
  }
}