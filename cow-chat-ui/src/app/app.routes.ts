import { Routes } from '@angular/router';
import { RegisterComponent } from './features/auth/register/register.component';
import { MainComponent } from './features/main/main.component';

export const routes: Routes = [
  { path: "", component: MainComponent },
  { path: "register", component: RegisterComponent }
];
