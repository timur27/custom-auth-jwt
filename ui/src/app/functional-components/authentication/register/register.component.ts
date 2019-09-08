import {Component, OnInit} from "@angular/core";
import {AuthService} from "../auth.service";
import {FormGroup} from "@angular/forms";

@Component({
    selector: 'login-component',
    templateUrl: './login.component.html'
})
export class RegisterComponent implements OnInit{
    submitted: boolean = false;
    registerForm: FormGroup;

    constructor(private authService: AuthService) {}

    ngOnInit(): void {
    }

    onSubmit() {
        this.submitted = true;

        this.authService.registerUser(this.registerForm.controls.email.value, this.registerForm.controls.password.value)
            .subscribe(res => {
                console.log(res);
            })
    }
}