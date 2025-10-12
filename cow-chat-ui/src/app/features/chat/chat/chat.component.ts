import { Component, OnInit } from '@angular/core';
import { UserListComponent } from "../user-list/user-list.component";
import { UserService } from '../../../core/services/user.service';
import { UserResponseDto } from '../../../models/user-response-dto';
import { CommonModule } from '@angular/common';
import { Router, RouterOutlet } from '@angular/router';
import { ChatService } from '../../../core/services/chat.service';
import { ChatResponseDto } from '../../../models/chat-response-dto';

@Component({
  selector: 'app-chat',
  imports: [UserListComponent, CommonModule],
  templateUrl: './chat.component.html',
  styleUrl: './chat.component.css'
})
export class ChatComponent implements OnInit{

  users: UserResponseDto[] = [];
  receiverUserId!:string;

  chat!:ChatResponseDto;

  constructor(private userService:UserService,
              private chatService:ChatService,
              private router:Router
  ){}

  ngOnInit(): void {
    this.userService.$findAllUsers().subscribe({
      next: (users:UserResponseDto[]) => {
        this.users = users;
      },
      error: (err) => {
        console.error('Error al crear cuenta:', err);
      }
    });
  }

  handleUserId(userId: string): void {
    this.chatService.$findChat(userId).subscribe({
      next:(chat:ChatResponseDto) => {
        this.chat = chat;
        console.log(chat)
      },
      error:()=>{
        //TODO We need to handle this error later
      }
    });
  }
}
