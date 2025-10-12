import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ChatResponseDto } from '../../models/chat-response-dto';

@Injectable({
  providedIn: 'root'
})
export class ChatService {

  private url:string = "http://localhost:8080"

  constructor(private http:HttpClient){}

  $findChat(receiverUserId: string): Observable<ChatResponseDto> {
    const userId = {id: receiverUserId}
    return this.http.post<ChatResponseDto>(`${this.url}/chat`, userId);
  }
}
