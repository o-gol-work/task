import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../services/auth/auth.service";
import {TokenStorageService} from "../../services/auth/token-storage.service";
import {NotificationService} from "../../services/notification.service";
import {Router} from "@angular/router";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../../services/user.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public loginForm!:FormGroup;

  constructor(
    private authService:AuthService,
    private tokenStorageService:TokenStorageService,
    private notification:NotificationService,
    private router:Router,
    private formBuilder:FormBuilder,
    private userServices:UserService


  ) {
    if (this.tokenStorageService.getUser()){
      this.router.navigate(['main'])
    }
  }

  ngOnInit(): void {
    this.loginForm=this.createLoginForm();
  }

  createLoginForm():FormGroup{
    return this.formBuilder.group({
      username:['',Validators.compose([Validators.required])],
      password:['',Validators.compose([Validators.required,])]
    })
  }

  submit():void{
    this.authService.login({
      username:this.loginForm?.value.username,
      password:this.loginForm?.value.password
    }).subscribe({next:data=>{
        this.tokenStorageService.saveToken(data.token);
        this.tokenStorageService.saveUser(data.employee)
        this.notification.showSnackbar("Successfully login");
        console.log(data)
        this.router.navigate(["main"]);
        window.location.reload();

      },
      error:err => {
        console.log(err);
        this.notification.showSnackbar(err.message);

      }}
    )

  }


  /*submit():void{
    this.authService.login({
      username:this.loginForm.value.username,
      password:this.loginForm.value.password
    }).subscribe(data=>{
      this.tokenStorageService.saveToken(data.token);
      this.tokenStorageService.saveUser(data.employee)
      this.notification.showSnackbar("Successfully login");
      console.log(data)
      this.router.navigate(["main"]);
      window.location.reload();
    }, error => {
      console.log(error);
      this.notification.showSnackbar(error.message);
    })

  }*/


}
