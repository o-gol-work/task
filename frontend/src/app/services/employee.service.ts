import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

const USER_API="http://localhost:8080/employees/";

@Injectable({
  providedIn: 'root'
})


export class EmployeeService {

  constructor(private httpClient:HttpClient) { }

  public getCurrentEmployee():Observable<any>{
    return this.httpClient.get(USER_API+"employee")
  }

  public getEmployeeById(id:number):Observable<any>{
    return this.httpClient.get(USER_API+"employee/"+id)
  }

  public getCurrentEmployeeByPrincipal():Observable<any>{
    return this.httpClient.get(USER_API+"employeePrincipal")
  }
}
