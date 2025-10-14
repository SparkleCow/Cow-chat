import { Component, EventEmitter, Input, Output } from '@angular/core';
import { UserResponseDto } from '../../../models/user-response-dto';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-list',
  imports: [],
  templateUrl: './user-list.component.html',
  styleUrl: './user-list.component.css'
})
export class UserListComponent {

    @Input() users: UserResponseDto[] = [];
    @Input() loggedUser!: UserResponseDto;
    @Output() userId = new EventEmitter<string>();

    constructor(private router:Router){}

    openChat(user: UserResponseDto): void {
      this.userId.emit(user.id);
    }

    redirectAtUserInformation(){
      this.router.navigate(["/information"]);
    }
}
