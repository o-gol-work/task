import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {LoginComponent} from "./views/login/login.component";
import {MainComponent} from "./views/main/main.component";
import {AppComponent} from "./app.component";
import {AuthGuardService} from "./services/auth/auth-guard.service";
import {RegistrationComponent} from "./views/registration/registration.component";


const routes: Routes = [
  {path:'login', component: LoginComponent},
  {path:'main', component: MainComponent},
  {path:'reg', component: RegistrationComponent},
  { path: '', component: AppComponent, canActivate: [AuthGuardService] },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
