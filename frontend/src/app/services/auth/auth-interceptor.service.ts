import { Injectable } from '@angular/core';
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {TokenStorageService} from "./token-storage.service";
import {Observable} from "rxjs";
const TOKEN_HEADER_KEY='Authorisation'

@Injectable({
  providedIn: 'root'
})
export class AuthInterceptorService implements HttpInterceptor{

  constructor(public tokenStorage: TokenStorageService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let authRequest = req;
    const token=this.tokenStorage.getToken();
    if(token!=null){
      authRequest=req.clone({headers: req.headers.set(TOKEN_HEADER_KEY,token)});
    }
    return next.handle(authRequest);
  }



}
