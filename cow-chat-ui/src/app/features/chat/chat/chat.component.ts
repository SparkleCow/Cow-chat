import { Component, OnInit } from '@angular/core';
import { UserListComponent } from "../user-list/user-list.component";
import { UserService } from '../../../core/services/user.service';
import { UserResponseDto } from '../../../models/user-response-dto';

@Component({
  selector: 'app-chat',
  imports: [UserListComponent],
  templateUrl: './chat.component.html',
  styleUrl: './chat.component.css'
})
export class ChatComponent implements OnInit{

  users: UserResponseDto[] = [];

  constructor(private userService:UserService){}

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
}
