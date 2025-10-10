import { Routes } from '@angular/router';
import { RegisterComponent } from './features/auth/register/register.component';
import { MainComponent } from './features/main/main.component';
import { LoginComponent } from './features/auth/login/login.component';

export const routes: Routes = [
  { path: "", component: MainComponent },
  { path: "register", component: RegisterComponent },
  { path: "login", component: LoginComponent},
  { path: "chat", loadChildren: () => import('./features/chat/chat.routes')
      .then(m => m.chatRoutes)
  }
];
