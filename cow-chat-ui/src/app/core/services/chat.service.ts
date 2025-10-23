import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { ChatResponseDto } from '../../models/chat-response-dto';

@Injectable({
  providedIn: 'root'
})
export class ChatService {

  // private url:string = "http://localhost:8080"
  private url:string = "http://192.168.1.2:8080"

  private chatSubject = new BehaviorSubject<ChatResponseDto | null>(null);
  public chat$: Observable<ChatResponseDto | null> = this.chatSubject.asObservable();

  constructor(private http:HttpClient){}

  findChat(receiverUserId: string): void {
    const userId = {id: receiverUserId}
    this.http.post<ChatResponseDto>(`${this.url}/chat`, userId).subscribe({
      next: (chat) => this.chatSubject.next(chat),
      error: (err) => {
          console.error('Error al obtener usuario logueado:', err);
          this.chatSubject.next(null);
        }
    });
  }
}
