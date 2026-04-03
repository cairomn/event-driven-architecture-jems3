import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor, HttpClient
} from '@angular/common/http';
import { Observable } from 'rxjs';
import {Router} from "@angular/router";
import {environment} from "../../environments/environment";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private router: Router, private http: HttpClient) {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let jwtToken: string = localStorage.getItem('token');

    if (jwtToken == null || jwtToken == '') {
      this.router.navigate(['/login']).then(r => r);
    }

    return next.handle(request.clone({
      setHeaders: {
        Authorization: `Bearer ${jwtToken}`
      }
    }));
  }
}
