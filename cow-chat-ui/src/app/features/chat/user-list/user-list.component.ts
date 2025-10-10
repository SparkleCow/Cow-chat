import { Component, Input } from '@angular/core';
import { UserResponseDto } from '../../../models/user-response-dto';

@Component({
  selector: 'app-user-list',
  imports: [],
  templateUrl: './user-list.component.html',
  styleUrl: './user-list.component.css'
})
export class UserListComponent {

    @Input() users: UserResponseDto[] = [];
}
