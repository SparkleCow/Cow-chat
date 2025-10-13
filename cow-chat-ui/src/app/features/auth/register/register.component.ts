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
      username: ['', [Validators.required, Validators.maxLength(15)]],
      email: ['', [Validators.required, Validators.email, Validators.maxLength(40)]],
      password: ['', [Validators.required, Validators.minLength(6), Validators.maxLength(20)]],
      confirmPassword: ['', [Validators.required, Validators.minLength(6), Validators.maxLength(20)]]
    });
  }

  onSubmit(): void {
    if (this.form.invalid) {
      alert("Ingresa toda los datos requeridos")
      this.form.markAllAsTouched();
      return;
    }

    const userRequestDto = {
      username: this.form.get('username')?.value,
      email: this.form.get('email')?.value,
      password: this.form.get('password')?.value,
      confirmPassword: this.form.get('confirmPassword')?.value
    };

    if(userRequestDto.password != userRequestDto.confirmPassword){
      alert("Las contraseñas no coinciden")
      this.form.markAllAsTouched();
      return;
    }

    delete userRequestDto.confirmPassword;

    this.authService.$createAccount(userRequestDto).subscribe({
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
    this.router.navigate(["/login"]);
  }
}
