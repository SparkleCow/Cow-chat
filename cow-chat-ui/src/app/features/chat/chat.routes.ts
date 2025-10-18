import { Routes } from '@angular/router';
import { ChatComponent } from './chat/chat.component';
import { UserInformationComponent } from '../information/user-information/user-information.component';
import { EmptyChatComponent } from './empty-chat/empty-chat.component';
import { ChatPageComponent } from './chat-page/chat-page.component';

export const chatRoutes: Routes = [
  {
    path: '',
    component: ChatComponent,
    children: [
      { path: '', component: EmptyChatComponent },
      { path: 'information', component: UserInformationComponent },
      { path: 'page', component: ChatPageComponent }
    ]
  }
];
