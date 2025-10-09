import { Component } from '@angular/core';
import { UserListComponent } from "../user-list/user-list.component";

@Component({
  selector: 'app-chat',
  imports: [UserListComponent],
  templateUrl: './chat.component.html',
  styleUrl: './chat.component.css'
})
export class ChatComponent {

}
