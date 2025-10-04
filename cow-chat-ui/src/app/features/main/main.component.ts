import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-main',
  imports: [],
  templateUrl: './main.component.html',
  styleUrl: './main.component.css'
})
export class MainComponent {

  constructor(private router:Router){}

  redirectAtLogin(){
  }

  redirectAtRegister(){
    this.router.navigate(["/register"]);
  }
}
