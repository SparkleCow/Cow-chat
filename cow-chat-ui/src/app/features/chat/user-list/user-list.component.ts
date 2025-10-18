import { Component, EventEmitter, Input, Output } from '@angular/core';
import { UserResponseDto } from '../../../models/user-response-dto';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent {

  @Input() users: UserResponseDto[] = [];
  @Input() loggedUser!: UserResponseDto;
  @Output() userId = new EventEmitter<string>();

  constructor(
    private router: Router,
    private route: ActivatedRoute
  ) {}

  openChat(user: UserResponseDto): void {
    this.userId.emit(user.id);
    this.redirectAtChat();
  }

  redirectAtUserInformation(): void {
    this.router.navigate(['information'], { relativeTo: this.route });
  }

  redirectAtChat(){
    this.router.navigate(['page'], { relativeTo: this.route });
  }
}
