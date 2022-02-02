import { Component, OnInit } from '@angular/core';
import {NotificationService} from "../../services/notification.service";
import {Router} from "@angular/router";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {RegistrationService} from "../../services/registration.service";
import {SignupRequest} from "../../models/SignupRequest";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  public registrationForm!:FormGroup;
  constructor(
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
    )).subscribe({
        next: data => {
          this.notification.showSnackbar(data.message);
          console.log(data.message)
          //this.router.navigate(["main"]);
         // window.location.reload();

        },
        error: err => {
          console.log(err);
          this.notification.showSnackbar(err.message);

        }
      }
    )
  }

}
