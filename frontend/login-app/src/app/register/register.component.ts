import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  registerData = {
    email: '',
    username: '',
    password: '',
    role: ''
  };

  roles = ['user', 'admin', 'organizer'];

  constructor(
    private http: HttpClient,
    private router: Router
  ) {}

  onSubmit() {
    this.http.post('http://localhost:8081/register', this.registerData)
      .subscribe({
        next: (response: any) => {
          alert('Registration successful! Please login with your credentials.');
          this.router.navigate(['/authenticate']); // Navigate back to login page
        },
        error: (error) => {
          alert('Registration failed. Please try again.');
        }
      });
  }
}