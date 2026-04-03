import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {environment} from "../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  public isTokenValid(): Observable<Object> {
    const token = localStorage.getItem('token');
    return this.http.get(environment.URL_API_LAB_STREAM_CONTROL + 'auth/valid?=' + token);
  }
}
