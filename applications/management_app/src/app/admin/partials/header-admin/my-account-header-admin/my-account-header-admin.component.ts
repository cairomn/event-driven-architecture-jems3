import { Component, OnInit } from '@angular/core';
import {Route, Router} from "@angular/router";

@Component({
  selector: 'cmdca-my-account-header-admin',
  templateUrl: './my-account-header-admin.component.html',
  styleUrls: ['./my-account-header-admin.component.scss']
})
export class MyAccountHeaderAdminComponent implements OnInit {

  public username: string = 'User';
  public role: string = 'USER_ROLE';

  constructor(private router: Router) { }

  ngOnInit(): void {
    const user = JSON.parse(localStorage.getItem('user'));
    this.username = user.username;
    this.role = user.roles[0];
  }

  logout(): void {
    localStorage.setItem('user', '');
    localStorage.setItem('token', '');
    this.router.navigate(['/login']);
  }

}
