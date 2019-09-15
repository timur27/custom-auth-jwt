import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {LoginComponent} from "./functional-components/authentication/login/login.component";
import {ReactiveFormsModule} from "@angular/forms";
import {AuthService} from "./functional-components/authentication/auth.service";
import {HttpClientModule} from "@angular/common/http";
import {RegisterComponent} from "./functional-components/authentication/register/register.component";

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        ReactiveFormsModule
    ],
  providers: [AuthService],
  bootstrap: [AppComponent]
})
export class AppModule { }
