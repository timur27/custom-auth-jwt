import {AuthService} from "./auth.service";
import {Component, Injectable, OnInit} from "@angular/core";
import {FormGroup} from "@angular/forms";



@Component({
  selector: 'auth-service',
  templateUrl: './auth.component.html'
})
export class AuthComponent implements OnInit{
    authForm: FormGroup



    constructor(private authenticationService: AuthService){
      this.authService = authenticationService;
    }


    public registerUser(email: any, password: any) {
      this.authService.registerUser(email, password);
    }

  ngOnInit(): void {
  }
}
