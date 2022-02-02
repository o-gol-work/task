import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Employee} from "../../models/Employee";
import {Observable} from "rxjs";
import {EmployeeLogin} from "../../models/EmployeeLogin";

const AUTH_API="http://localhost:8080/start/"

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private httpClient:HttpClient) { }

  public login(employee:EmployeeLogin):Observable<any>{
    console.log('post login send')
    return this.httpClient.post(AUTH_API+"signin",{

      username:employee.username,
      password:employee.password


    })


  }

}
