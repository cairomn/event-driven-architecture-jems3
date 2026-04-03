import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'cmdca-message-box-header-admin',
  templateUrl: './message-box-header-admin.component.html',
  styleUrls: ['./message-box-header-admin.component.scss']
})
export class MessageBoxHeaderAdminComponent implements OnInit {

  public openMessageBox: boolean = false;

  constructor() { }

  ngOnInit() {
  }

  toggleMessageBox() {
    this.openMessageBox = !this.openMessageBox;
  }
  
}
