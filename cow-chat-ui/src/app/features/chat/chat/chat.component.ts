import { Component, OnInit, OnDestroy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserListComponent } from "../user-list/user-list.component";
import { UserService } from '../../../core/services/user.service';
import { ChatService } from '../../../core/services/chat.service';
import { ChatSocketService } from '../../../core/services/chat-socket.service';
import { UserResponseDto } from '../../../models/user-response-dto';
import { ChatResponseDto } from '../../../models/chat-response-dto';
import { FormsModule } from '@angular/forms';
import { Subscription } from 'rxjs';
import { RouterOutlet } from '@angular/router';

@Component({
  selector: 'app-chat',
  standalone: true,
  imports: [UserListComponent, CommonModule, FormsModule, RouterOutlet],
  templateUrl: './chat.component.html',
  styleUrl: './chat.component.css'
})
export class ChatComponent implements OnInit, OnDestroy {

  users: UserResponseDto[] = [];
  receiverUserId!: string;
  chat!: ChatResponseDto;

  newMessage: string = '';
  user!: UserResponseDto;

  //This is the observable from all users. This will receive all active users
  private userSub?: Subscription;

  constructor(
    private userService: UserService,
    private chatService: ChatService,
    private chatSocketService: ChatSocketService,
  ) {}

  ngOnInit(): void {

    this.chatSocketService.connect(() => {
      console.log("WebSocket listo para suscripciones de chat.");
    });

    this.userService.loadAllUsers();

    this.userSub = this.userService.users$.subscribe({
      next: (users) => this.users = users
    });


    this.userService.user$.subscribe({
      next: (user) => {
        if (user) {
          this.user = user;
        }
      }
    });
    this.userService.findUserLogged();
  }

  ngOnDestroy(): void {
    this.userSub?.unsubscribe();
    this.chatSocketService.disconnect();
  }
}
