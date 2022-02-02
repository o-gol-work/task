import { Injectable } from '@angular/core';
import {TokenStorageService} from "../auth/token-storage.service";
import {NotificationService} from "../notification.service";
import {HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {catchError, Observable, throwError} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ErrorInterceptorService implements HttpInterceptor{

  constructor(private tokenStorageServices:TokenStorageService,
              private notificationServices:NotificationService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    return next.handle(req).pipe(catchError(err =>{
      if(err.status===401){
        this.tokenStorageServices.logout()
        window.location.reload()
      }

      const error=err.error.message || err.statusText
      this.notificationServices.showSnackbar(error)
      return throwError(error)

    }))
  }



}
