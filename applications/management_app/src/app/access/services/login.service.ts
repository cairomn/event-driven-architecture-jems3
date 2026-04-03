import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Login } from '../models/login';
import { Observable } from "rxjs";
import {environment} from "../../../environments/environment";
import {LoginRequest} from "../models/login-request";

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) { }

  public doLogin(login: Login): Observable<LoginRequest> {
    return this.http.post<LoginRequest>(environment.URL_API_MANAGEMENT_DASHBOARD + 'auth', login);
  }
}
