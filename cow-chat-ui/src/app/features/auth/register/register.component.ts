import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../../core/services/auth.service';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  imports: [ReactiveFormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent implements OnInit{

  form!: FormGroup;

  constructor(private authService:AuthService,
              private fb:FormBuilder,
              private router:Router
  ){}

  ngOnInit(): void {
    this.form = this.fb.group({
      username: ['', [Validators.required]],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]]
    });
  }

  onSubmit(): void {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    this.authService.$createAccount(this.form.value).subscribe({
      next: () => {
        alert("Cuenta creada con éxito")
        this.redirectAtLogin();
      },
      error: (err) => {
        console.error('Error al crear cuenta:', err);
      }
    });
  }

  redirectAtLogin(){
    this.router.navigate(["/"]);
  }
}
