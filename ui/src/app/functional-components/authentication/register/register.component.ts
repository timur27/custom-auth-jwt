import {Component, OnInit} from "@angular/core";
import {AuthService} from "../auth.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'register-component',
  templateUrl: './register.component.html'
})
export class RegisterComponent implements OnInit{
  submitted: boolean = false;
  registerForm: FormGroup;
  loading = false;

  constructor(private formBuilder: FormBuilder, private authService: AuthService) {}

  ngOnInit(): void {
    this.registerForm = this.formBuilder.group({
      username: [''],
      password: ['']
    });
  }

  get f() { return this.registerForm.controls; }

  onSubmit() {
    this.submitted = true;
    this.authService.registerUser(this.registerForm.get('username').value, this.registerForm.get('password').value)
      .subscribe(res => {
        console.log(res);
      })
  }
}
