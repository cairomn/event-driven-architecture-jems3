import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Role} from "../models/role";
import {environment} from "../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class RoleService {

  constructor(private http: HttpClient) { }

  public getRoles(): Observable<Array<Role>>{
    return this.http.get<Array<Role>>(environment.URL_API_MANAGEMENT_DASHBOARD + 'roles');
  }
}
