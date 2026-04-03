import { Component, OnInit } from "@angular/core";
import { FormBuilder, Validators, FormGroup } from "@angular/forms";
import { Router } from "@angular/router";
import { LoginService } from "../../services/login.service";
import { Login } from "../../models/login";

@Component({
  selector: "app-login",
  templateUrl: "./login.component.html",
  styleUrls: ["./login.component.scss"],
})
export class LoginComponent implements OnInit {
  public loginForm: FormGroup;
  public show: boolean = false
  public errorMessage: any;

  constructor(
    private fb: FormBuilder,
    public router: Router,
    private loginService: LoginService
  ) {
    this.loginForm = this.fb.group({
      email: ["", [Validators.required, Validators.email]],
      password: ["", Validators.required],
    });
  }

  ngOnInit() {}

  login() {
    if (this.loginForm.valid) {
      let login: Login = {
        email: this.loginForm.value["email"],
        senha: this.loginForm.value["password"]
      };

      this.loginService.doLogin(login).toPromise().then(resp => {
        localStorage.setItem('user', JSON.stringify(resp));
        localStorage.setItem('token', resp.token);
        this.router.navigate(["/admin"]).then(r => true);
      }, error => {

      });
    }
  }

  showPassword(){
    this.show = !this.show
  }
}
