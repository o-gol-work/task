import { Component, OnInit } from '@angular/core';
import {NotificationService} from "../../services/notification.service";
import {Router} from "@angular/router";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {RegistrationService} from "../../services/registration.service";
import {SignupRequest} from "../../models/SignupRequest";
import {MatDialog} from "@angular/material/dialog";
import {PopUpComponent} from "../pop-up/pop-up.component";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  public registrationForm!:FormGroup;
  public errorMessage!:string;

  constructor(
    private refDialog:MatDialog,
    private registrationService:RegistrationService,
    private notification:NotificationService,
    private router:Router,
    private formBuilder:FormBuilder
  ) { }

  ngOnInit(): void {
    this.registrationForm=this.createRegistrationForm();
  }



  createRegistrationForm():FormGroup{
    return this.formBuilder.group({
      tabelNumber:['',Validators.compose([Validators.required])],
      name:['',Validators.compose([Validators.required,])],
      surname:['',Validators.compose([Validators.required,])],
      telephoneNumber:['',Validators.compose([Validators.required,])],
      postHasDepartmentId:['',Validators.compose([Validators.required,])],
      password:['',Validators.compose([Validators.required,])],
      confirmPassword:['',Validators.compose([Validators.required,])]
    })
  }



  submit():void {
    this.registrationService.signup(new SignupRequest(
      this.registrationForm.value.tabelNumber,
      this.registrationForm.value.name,
      this.registrationForm.value.surname,
      this.registrationForm.value.telephoneNumber,
      this.registrationForm.value.postHasDepartmentId,
      this.registrationForm.value.password,
      this.registrationForm.value.confirmPassword
    )).
    subscribe({
        next: data => {
          // this.notification.showSnackbar(data.username+data.password+'ffffffffffffffffffffffffffffffffffff');
          // this.notification.showSnackbar(data.message);
          // this.openDialog('FFFFFFFFFFFFFFFFFFFFFFFFFFFFFF');

          this.notification.showSnackbar(data.message);
          console.log(data.message)
          this.router.navigate(["main"]);
         window.location.reload();

        },
        error: err => {
          // this.errorMessage=err;
          // console.log(this.errorMessage);
          // this.notification.showSnackbar(err.data.username+'uuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu');
          this.notification.showSnackbar(err.message);
          // this.openDialog('UUUUUUUUUUUUUUUUUUUUUUUUUUUUUU');

        }
      }
    )
      /*subscribe(value => {
        this.notification.showSnackbar(value.message);
      },error => {
        this.notification.showSnackbar(error.message)
      })*/

  }

  openDialog(message:string):void{
    this.refDialog.open(PopUpComponent,{
      data:{
        errr:message
      }
    });
  }

}
