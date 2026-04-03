import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'cmdca-cart-header-admin',
  templateUrl: './cart-header-admin.component.html',
  styleUrls: ['./cart-header-admin.component.scss']
})
export class CartHeaderAdminComponent implements OnInit {

  public openCart: boolean = false;

  constructor() { }

  ngOnInit() { }

  // For Mobile Device
  toggleCart() {
    this.openCart = !this.openCart;
  }
}
