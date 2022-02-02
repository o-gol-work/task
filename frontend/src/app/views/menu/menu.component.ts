import { Component, OnInit } from '@angular/core';
import {DataHandlerService} from "../../services/data-handler.service";
import {CommonModule} from '@angular/common';
import {TokenStorageService} from "../../services/auth/token-storage.service";
import {Router} from "@angular/router";
import {MainComponent} from "../main/main.component";

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  menuItems: string[] | undefined ;



  constructor(private dataHandler:DataHandlerService,
              private mainComponent:MainComponent,
              private tokenStorageService:TokenStorageService,
              private router:Router ) { }

  ngOnInit(): void {
    this.menuItems=this.dataHandler.getMenuItems();
    console.log(this.menuItems)
  }

  logout():void{
    this.tokenStorageService.logout()
    this.router.navigate(["/login"])

  }

  registration():void{
    this.mainComponent.registration();
  }



}
