import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-auth-layout',
  templateUrl: './auth-layout.component.html',
  styleUrls: ['./auth-layout.component.scss']
})
export class AuthLayoutComponent implements OnInit {

  constructor(private router: Router, private authService: AuthService) { }
  ngOnInit(): void {
  }

  onPortalClick(ut: string) {
    const utLoggedIn = this.authService.getUserType();
    if('p' === utLoggedIn || 'd' === utLoggedIn) {
      this.router.navigateByUrl('/dashboard');
    } else {
      this.router.navigateByUrl('/login?ut=' + ut);
    }
  }

}
