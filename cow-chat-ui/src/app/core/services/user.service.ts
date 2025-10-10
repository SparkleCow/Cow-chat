import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserResponseDto } from '../../models/user-response-dto';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private url:string = "http://localhost:8080"

  constructor(private http:HttpClient) { }

  $findAllUsers():Observable<[UserResponseDto]>{
    return this.http.get<[UserResponseDto]>(`${this.url}/user`);
  }

}
