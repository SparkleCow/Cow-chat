import { Component, OnInit, OnDestroy } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserListComponent } from "../user-list/user-list.component";
import { UserService } from '../../../core/services/user.service';
import { ChatService } from '../../../core/services/chat.service';
import { ChatSocketService } from '../../../core/services/chat-socket.service';
import { UserResponseDto } from '../../../models/user-response-dto';
import { ChatResponseDto } from '../../../models/chat-response-dto';
import { MessageResponseDto } from '../../../models/message-response-dto';
import { AuthService } from '../../../core/services/auth.service';
import { MessageRequestDto, MessageType } from '../../../models/message-request-dto';
import { FormsModule } from '@angular/forms';
import { Subscription, Observable } from 'rxjs';

@Component({
  selector: 'app-chat',
  standalone: true,
  imports: [UserListComponent, CommonModule, FormsModule],
  templateUrl: './chat.component.html',
  styleUrl: './chat.component.css'
})
export class ChatComponent implements OnInit, OnDestroy {

  users: UserResponseDto[] = [];
  receiverUserId!: string;
  chat!: ChatResponseDto;

  newMessage: string = '';
  currentUserId: string = '';
  user!: UserResponseDto;

  private currentChatSubscription: any = null;

  //This is the observable from all users. This will receive all active users
  private userSub?: Subscription;
  private loggedUserSub?: Subscription;

  constructor(
    private userService: UserService,
    private chatService: ChatService,
    private chatSocketService: ChatSocketService,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    this.currentUserId = this.authService.getUserId() ?? '';

    this.chatSocketService.connect(() => {
      console.log("✅ WebSocket listo para suscripciones de chat.");
    });

    this.userSub = this.userService.$findAllUsers().subscribe({
      next: (users: UserResponseDto[]) => this.users = users,
      error: (err) => console.error('Error al cargar usuarios:', err)
    });

    this.loggedUserSub = this.userService.user$.subscribe({
      next: (user) => {
        if (user) {
          this.currentUserId = user.id;
          this.user = user;
        } else {
          this.currentUserId = '';
        }
      }
    });

    this.userService.findUserLogged();
  }

  ngOnDestroy(): void {
    if (this.currentChatSubscription) {
      this.currentChatSubscription.unsubscribe();
      this.currentChatSubscription = null;
    }
    this.userSub?.unsubscribe();
    this.chatSocketService.disconnect();
  }

  handleUserId(userId: string): void {
    this.receiverUserId = userId;

    this.chatService.$findChat(userId).subscribe({
      next: (chat: ChatResponseDto) => {
        this.chat = chat;
        console.log("💬 Chat cargado:", chat);

       if (this.currentChatSubscription) {
        this.currentChatSubscription.unsubscribe();
      }

      this.currentChatSubscription = this.chatSocketService.subscribeToChat(chat.id, (message) => {
        console.log("📨 Mensaje recibido en chat actual:", message);
        if (this.chat.id === message.chatId) {
          this.chat.messages.push(message);
        }
      });
      },
      error: (err) => {
        console.error("Error al obtener chat:", err);
      }
    });
  }

  sendMessage(): void {
    if (!this.newMessage || !this.chat) return;

    const dto: MessageRequestDto = {
      chatId: this.chat.id,
      senderId: this.currentUserId,
      content: this.newMessage,
      messageType: MessageType.TEXT,
      filePath: '',
      recipientIds: [this.receiverUserId]
    };

    this.chatSocketService.sendMessage(this.chat.id, dto);
    this.newMessage = '';
  }
}
