import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MenuComponent } from './views/menu/menu.component';

import {CommonModule} from '@angular/common';
import { TableComponent } from './views/table/table.component';
import { LoginComponent } from './views/login/login.component';
import { MainComponent } from './views/main/main.component';
import {MaterialModule} from "./material-module";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {AuthInterceptorService} from "./services/auth/auth-interceptor.service";
import {ErrorInterceptorService} from "./services/error/error-interceptor.service";
import { HeaderNavComponent } from './views/header-nav/header-nav.component';
import { RegistrationComponent } from './views/registration/registration.component';
import {MatDialogModule} from "@angular/material/dialog";
import { PopUpComponent } from './views/pop-up/pop-up.component';
import { TableForMeComponent } from './views/table-for-me/table-for-me.component';
import { AddtaskComponent } from './views/addtask/addtask.component';
import {StompService} from "./services/stomp.service";


@NgModule({
  declarations: [
    AppComponent,
    MenuComponent,
    TableComponent,
    LoginComponent,
    MainComponent,
    HeaderNavComponent,
    RegistrationComponent,
    PopUpComponent,
    TableForMeComponent,
    AddtaskComponent
  ],
  imports: [
    CommonModule,
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialModule,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule,
    MatDialogModule

  ],
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: AuthInterceptorService, multi: true},
    {provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptorService, multi: true},
    StompService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
