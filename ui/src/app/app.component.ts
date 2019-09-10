import { Component } from '@angular/core';
import {User} from "./functional-components/authentication/user";
import {Router} from "@angular/router";
import {AuthService} from "./functional-components/authentication/auth.service";

@Component({ selector: 'app', templateUrl: 'app.component.html' })
export class AppComponent {
  currentUser: User;

  constructor(
    private router: Router,
    private authenticationService: AuthService
  ) {
    // this.authenticationService.currentUser.subscribe(x => this.currentUser = x);
  }

  logout() {
    // this.authenticationService.logout();
    // this.router.navigate(['/login']);
  }
}
