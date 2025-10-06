import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { CardComponent } from "../../shared/card/card.component";

@Component({
  selector: 'app-main',
  imports: [CardComponent],
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
