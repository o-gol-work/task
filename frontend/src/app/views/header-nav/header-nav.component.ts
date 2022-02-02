import { Component, OnInit } from '@angular/core';
import {TokenStorageService} from "../../services/auth/token-storage.service";
import {Employee} from "../../models/Employee";
import {UserService} from "../../services/user.service";

@Component({
  selector: 'app-header-nav',
  templateUrl: './header-nav.component.html',
  styleUrls: ['./header-nav.component.css']
})
export class HeaderNavComponent implements OnInit {

  isLoggedIn! :  boolean | false;
  isDataLoading! : boolean | false;
  employee!: Employee ;

  constructor(private tokenStorageService:TokenStorageService,
              private userServices:UserService
              ) { this.isLoggedIn=false;
                  this.isDataLoading=false;}

  ngOnInit(): void {
    this.isLoggedIn=!!this.tokenStorageService.getToken();
    if (this.isLoggedIn){
      this.employee=this.tokenStorageService.getUser();
      // this.userServices.getCurrentUserByPrincipal().subscribe(data=>this.employee=data)
      console.log(this.employee)
      this.isDataLoading=true;
      }


  }

}
