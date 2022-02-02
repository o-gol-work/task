import { Injectable } from '@angular/core';
import {EmployeeLogin} from "../models/EmployeeLogin";
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {SignupRequest} from "../models/SignupRequest";


const REG_API='http://localhost:8080/start/';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {


  constructor(private httpClient: HttpClient
              ) {
  }


  public signup(signupRequest: SignupRequest): Observable<any> {
    console.log('post registration send')
    return this.httpClient.post(REG_API + "signup", signupRequest)

  }
}
