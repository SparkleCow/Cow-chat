import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { UserResponseDto } from '../../models/user-response-dto';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  //private url: string = "http://localhost:8080";
  private url: string = "http://192.168.1.2:8080";

  private userSubject = new BehaviorSubject<UserResponseDto | null>(null);
  public user$: Observable<UserResponseDto | null> = this.userSubject.asObservable();

  constructor(private http: HttpClient) {}

  $findAllUsers(): Observable<UserResponseDto[]> {
    return this.http.get<UserResponseDto[]>(`${this.url}/user`);
  }

  findUserLogged(): void {
    this.http.get<UserResponseDto>(`${this.url}/user/self`).subscribe({
      next: (user) => this.userSubject.next(user),
      error: (err) => {
        console.error('Error al obtener usuario logueado:', err);
        this.userSubject.next(null);
      }
    });
  }

  uploadUserImage(formData: FormData): Observable<UserResponseDto> {
    return this.http.post<UserResponseDto>(`${this.url}/user/upload-image`, formData);
  }

  updateUsername(newUsername: string): Observable<UserResponseDto> {
    return this.http.put<UserResponseDto>(`${this.url}/user/update-username`, { username: newUsername });
  }

  clearUser(): void {
    this.userSubject.next(null);
  }
}
