import { Component } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-authenticate',
  templateUrl: './authenticate.component.html',
  styleUrls: ['./authenticate.component.css']
})
export class AuthenticateComponent {
  authenticateData = {
    username: '',
    password: '',
    userType: 'user'
  };

  constructor(
    private http: HttpClient,
    private router: Router
  ) {}

  onSubmit() {
    this.http.post('http://localhost:8081/authenticate', this.authenticateData)
      .subscribe({
        next: (response: any) => {
          alert('Login successful!');
          this.router.navigate(['/home']); // Navigate to home page after successful login
        },
        error: (error) => {
          alert('Incorrect credentials. Please try again.');
        }
      });
  }

  goToSignup() {
    this.router.navigate(['/register']);
  }

  goToResetPassword() {
    alert('Reset password functionality will be implemented later');
  }
}