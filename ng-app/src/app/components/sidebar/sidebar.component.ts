import { Component, OnInit } from "@angular/core";
import { AuthService } from 'src/app/services/auth.service';

declare interface RouteInfo {
  path: string;
  title: string;
  icon: string;
  class: string;
}
export const ROUTES: RouteInfo[] = [
  {
    path: "",
    title: "Appointments",
    icon: "icon-notes",
    class: ""
  },
  {
    path: "",
    title: "Profile",
    icon: "icon-single-02",
    class: ""
  }
];

@Component({
  selector: "app-sidebar",
  templateUrl: "./sidebar.component.html",
  styleUrls: ["./sidebar.component.css"]
})
export class SidebarComponent implements OnInit {
  menuItems: any[];

  constructor(private authService: AuthService) {}

  ngOnInit() {
    this.menuItems = ROUTES.filter(menuItem => menuItem);
    const ut = this.authService.getUserType();
    if(ut === 'p') {
      this.menuItems[0].path = '/patient-appointment';
      this.menuItems[1].path = '/patient-profile';

    } else if(ut === 'd') {
      this.menuItems[0].path = '/doctor-appointment';
      this.menuItems[1].path = '/doctor-profile';

    }
  }
  isMobileMenu() {
    if (window.innerWidth > 991) {
      return false;
    }
    return true;
  }
}
