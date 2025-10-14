import { Routes } from '@angular/router';
import { ChatComponent } from './chat/chat.component';
import { UserInformationComponent } from '../information/user-information/user-information.component';

export const chatRoutes: Routes = [
  { path: "", component: ChatComponent },
  { path: "information", component: UserInformationComponent}
];

