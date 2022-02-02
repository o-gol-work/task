import { Component, OnInit } from '@angular/core';
import {TokenStorageService} from "../../services/auth/token-storage.service";
import {Router} from "@angular/router";
import {state} from "@angular/animations";

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.css']
})
export class MainComponent implements OnInit {

  isRegistrationEnable=false;
  isTableEnable=true;

  constructor(private tokenStorageService:TokenStorageService,
              private router:Router) { }

  ngOnInit(): void {



    if(!!this.tokenStorageService.getToken()){
      this.router.navigate(["/main"])
    }else {
      this.router.navigate(["/login"])
    }
  }

  registration():void{
    this.isRegistrationEnable=true;
    this.isTableEnable=false;
  }

}
