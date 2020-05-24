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
    path: "/dashboard",
    title: "Dashboard",
    icon: "icon-chart-pie-36",
    class: ""
  },
  {
    path: "",
    title: "Profile",
    icon: "icon-single-02",
    class: ""
  },
  {
    path: "",
    title: "Appointments",
    icon: "icon-notes",
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
      this.menuItems[1].path = '/patient-profile';
      this.menuItems[2].path = '/patient-appointment';
    } else if(ut === 'd') {
      this.menuItems[1].path = '/doctor-profile';
      this.menuItems[2].path = '/doctor-appointment';
    }
  }
  isMobileMenu() {
    if (window.innerWidth > 991) {
      return false;
    }
    return true;
  }
}
