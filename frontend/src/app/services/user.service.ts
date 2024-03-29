import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";

const USER_API="http://localhost:8080/employees/";


@Injectable({
  providedIn: 'root'
})



export class UserService {


  constructor(private httpClient:HttpClient) { }

  public getCurrentUser():Observable<any>{
    return this.httpClient.get(USER_API+"employee")
  }

  public getUserById(id:number):Observable<any>{
    return this.httpClient.get(USER_API+"employee/"+id)
  }

  public getCurrentUserByPrincipal():Observable<any>{
    return this.httpClient.get(USER_API+"employeePrincipal")
  }


}
