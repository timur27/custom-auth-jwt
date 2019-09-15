import {Component, OnInit} from "@angular/core";
import {AuthService} from "../auth.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
    selector: 'login-component',
    templateUrl: './login.component.html'
})
export class LoginComponent implements OnInit{
    submitted: boolean = false;
    loginForm: FormGroup;
    loading = false;

    constructor(private formBuilder: FormBuilder, private authService: AuthService) {}

    ngOnInit(): void {
        this.loginForm = this.formBuilder.group({
            username: [''],
            password: ['']
        });
    }

    get f() { return this.loginForm.controls; }

    onSubmit() {
      this.submitted = true;
      this.authService.loginUser(this.loginForm.get('username').value, this.loginForm.get('password').value)
          .subscribe(res => {
              console.log(res);
          })
    }
}
