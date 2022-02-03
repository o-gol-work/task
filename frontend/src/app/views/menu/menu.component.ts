import { Component, OnInit } from '@angular/core';
import {DataHandlerService} from "../../services/data-handler.service";
import {CommonModule} from '@angular/common';
import {TokenStorageService} from "../../services/auth/token-storage.service";
import {Router} from "@angular/router";
import {MainComponent} from "../main/main.component";
import {EmployeeFromStorage} from "../../models/EmployeeFromStorage";
import {empty} from "rxjs";
import {Authorities} from "../../models/Authorities";

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {

  menuItems: string[] | undefined ;
  employeeFromStorage!:EmployeeFromStorage;
  isAdmin:boolean=false;
  isUser:boolean=false;
  authorityArray!:Authorities[]




  constructor(private dataHandler:DataHandlerService,
              private mainComponent:MainComponent,
              private tokenStorageService:TokenStorageService,
              private router:Router ) { }

  ngOnInit(): void {
    this.menuItems=this.dataHandler.getMenuItems();
    console.log(this.menuItems)
    this.employeeFromStorage = this.tokenStorageService.getUser()
    this.authorityArray= this.employeeFromStorage.authorities;
    this.authorise(this.authorityArray);
  }

  logout():void{
    this.tokenStorageService.logout()
    this.router.navigate(["/login"])



  }

  registration():void{
    this.mainComponent.registration();
  }

  authorise(arr:Authorities[]):void{
    arr.map(value => {
      switch ( value.authority ) {
        case "ROLE_USER":
          this.isUser=true
          break;
        case "ROLE_ADMIN":
          this.isAdmin=true
          break;
      }
    })
  }







}
