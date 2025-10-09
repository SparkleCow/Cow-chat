import { AuthResponseDto } from './../../models/auth-response-dto';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserRequestDto } from '../../models/user-request-dto';
import { Observable } from 'rxjs';
import { AuthRequestDto } from '../../models/auth-request-dto';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private url:string = "http://localhost:8080"
  private tokenKey = 'auth_token';

  constructor(private http:HttpClient) {}

  $createAccount(userRequestDto:UserRequestDto): Observable<void>{
    return this.http.post<void>(`${this.url}/auth/register`, userRequestDto);
  }

  $login(authRequestDto:AuthRequestDto):Observable<AuthResponseDto>{
    return this.http.post<AuthResponseDto>(`${this.url}/auth/login`, authRequestDto);
  }

  saveToken(token: string): void {
    localStorage.setItem(this.tokenKey, token);
  }

  getToken(): string | null {
    return localStorage.getItem(this.tokenKey);
  }

  logout(): void {
    localStorage.removeItem(this.tokenKey);
  }

  isAuthenticated(): boolean {
    return !!this.getToken();
  }
}
