import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'cmdca-notification-header-admin',
  templateUrl: './notification-header-admin.component.html',
  styleUrls: ['./notification-header-admin.component.scss']
})
export class NotificationHeaderAdminComponent implements OnInit {

  public openNotification: boolean = false;

  constructor() { }

  ngOnInit() { }

  toggleNotificationMobile() {
    this.openNotification = !this.openNotification;
  }

}
