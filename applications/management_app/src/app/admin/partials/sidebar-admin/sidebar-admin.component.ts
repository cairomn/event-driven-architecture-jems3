import { Component, HostListener, OnInit } from '@angular/core';
import { NavigationEnd, Router } from '@angular/router';
import { LayoutService } from 'src/app/shared/services/layout.service';
import { Menu, NavService } from 'src/app/shared/services/nav.service';

@Component({
  selector: 'cmdca-sidebar-admin',
  templateUrl: './sidebar-admin.component.html',
  styleUrls: ['./sidebar-admin.component.scss']
})
export class SidebarAdminComponent {

  public iconSidebar;
  public menuItems: Menu[];
  public url: any;
  public fileurl: any;

  // For Horizontal Menu
  public margin: any = 0;
  public width: any = window.innerWidth;
  public leftArrowNone: boolean = true;
  public rightArrowNone: boolean = false;

  constructor(
    private router: Router,
    public navServices: NavService,
    public layout: LayoutService
  ) {
    const user = localStorage.getItem('user');
    const roles: string[] = JSON.parse(user).roles ?? '';

    this.navServices.items.subscribe(menuItems => {
      let filteredMenu: Menu[] = [];

      filteredMenu = menuItems.filter(menu => {
        let isMenuAllowed = false;
        menu.allowedRoles.map(allowedRole => {
          roles.forEach(role => {
            isMenuAllowed = isMenuAllowed || (role === allowedRole);
          });
        })

        if (menu.children) {
          menu.children = menu.children.filter(subMenu => {
            let isSubMenuAllowed = false;
            subMenu.allowedRoles.map(allowedRole => {
              roles.forEach(role => {
                isSubMenuAllowed = isSubMenuAllowed || (role === allowedRole);
              });
            })
            return isSubMenuAllowed;
          });
        }

        return isMenuAllowed;
      });

      this.menuItems = filteredMenu;
      this.router.events.subscribe((event) => {
        if (event instanceof NavigationEnd) {
          menuItems.filter(items => {
            if (items.path === event.url) {
              this.setNavActive(items);
            }
            if (!items.children) { return false; }
            items.children.filter(subItems => {
              if (subItems.path === event.url) {
                this.setNavActive(subItems);
              }
              if (!subItems.children) { return false; }
              subItems.children.filter(subSubItems => {
                if (subSubItems.path === event.url) {
                  this.setNavActive(subSubItems);
                }
              });
            });
          });
        }
      });
    });
  }

  @HostListener('window:resize', ['$event'])
  onResize(event) {
    this.width = event.target.innerWidth - 500;
  }

  sidebarToggle() {
    this.navServices.collapseSidebar = !this.navServices.collapseSidebar;
  }

  // Active Nave state
  setNavActive(item) {
    this.menuItems.filter(menuItem => {
      if (menuItem !== item) {
        menuItem.active = false;
      }
      if (menuItem.children && menuItem.children.includes(item)) {
        menuItem.active = true;
      }
      if (menuItem.children) {
        menuItem.children.filter(submenuItems => {
          if (submenuItems.children && submenuItems.children.includes(item)) {
            menuItem.active = true;
            submenuItems.active = true;
          }
        });
      }
    });
  }

  // Click Toggle menu
  toggletNavActive(item) {
    if (!item.active) {
      this.menuItems.forEach(a => {
        if (this.menuItems.includes(item)) {
          a.active = false;
        }
        if (!a.children) { return false; }
        a.children.forEach(b => {
          if (a.children.includes(item)) {
            b.active = false;
          }
        });
      });
    }
    item.active = !item.active;
  }

  // For Horizontal Menu
  scrollToLeft() {
    if (this.margin >= -this.width) {
      this.margin = 0;
      this.leftArrowNone = true;
      this.rightArrowNone = false;
    } else {
      this.margin += this.width;
      this.rightArrowNone = false;
    }
  }

  scrollToRight() {
    if (this.margin <= -3051) {
      this.margin = -3464;
      this.leftArrowNone = false;
      this.rightArrowNone = true;
    } else {
      this.margin += -this.width;
      this.leftArrowNone = false;
    }
  }
}
