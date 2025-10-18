import { CommonModule } from '@angular/common';
import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ChatResponseDto } from '../../../models/chat-response-dto';
import { UserService } from '../../../core/services/user.service';
import { ChatService } from '../../../core/services/chat.service';
import { ChatSocketService } from '../../../core/services/chat-socket.service';
import { MessageRequestDto, MessageType } from '../../../models/message-request-dto';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-chat-page',
  imports: [CommonModule, FormsModule],
  templateUrl: './chat-page.component.html',
  styleUrl: './chat-page.component.css'
})
export class ChatPageComponent implements OnInit, OnDestroy{

  chat!: ChatResponseDto;

  newMessage: string = '';

  receiverUserId!: string;
  currentUserId: string = '';

  currentChatSubscription: any = null;

  private chatSub?: Subscription;
  private loggedUserSub?: Subscription;

  constructor(
      private userService: UserService,
      private chatService: ChatService,
      private chatSocketService: ChatSocketService,
  ){}

  ngOnInit(): void {

    this.loggedUserSub = this.userService.user$.subscribe({
      next: (user) => {
        this.currentUserId = user ? user.id : '';
      }
    });
    this.userService.findUserLogged();

    this.chatSub = this.chatService.chat$.subscribe({
      next: (chat) => {
        if (chat && (!this.chat || this.chat.id !== chat.id)) {

          this.chat = chat;

          if (this.currentChatSubscription) {
            this.currentChatSubscription.unsubscribe();
          }

          this.currentChatSubscription = this.chatSocketService.subscribeToChat(this.chat.id, (message) => {
            console.log("📨 Mensaje recibido en chat actual:", message);
            if (this.chat && this.chat.id === message.chatId) {
              this.chat.messages.push(message);
            }
          });
        }
      }
    });
  }

  ngOnDestroy(): void {
    if (this.currentChatSubscription) {
      this.currentChatSubscription.unsubscribe();
      this.currentChatSubscription = null;
    }
    this.chatSub?.unsubscribe();
    this.chatSocketService.disconnect();
  }

  sendMessage(): void {
    console.log("holaaaaaaaaaaa", this.chat)
    if (!this.newMessage || !this.chat) return;

    const dto: MessageRequestDto = {
      chatId: this.chat.id,
      senderId: this.currentUserId,
      content: this.newMessage,
      messageType: MessageType.TEXT,
      filePath: '',
      recipientIds: this.chat.participantsId.filter(id => id !== this.currentUserId)
    };

    this.chatSocketService.sendMessage(this.chat.id, dto);
    this.newMessage = '';
  }
}
