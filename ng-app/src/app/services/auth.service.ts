import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  authUrl = '/rest/urna/login';
  logoutUrl = '/rest/urna/logout/logout';

  constructor(private http: HttpClient, private router: Router) { }

  login(credentials): Observable<any> {
    const data = {
      email: credentials.email,
      password: credentials.password,
      userType: credentials.userType
    };
    /*
    return this.http.post(this.authUrl, data, { observe: 'response' })
      .pipe(map(authenticateSuccess.bind(this)));

    function authenticateSuccess(resp) {
      this.userDetails = resp.body;
      localStorage.setItem('urna-user', resp.body);
    }*/
    return this.http.post(this.authUrl, data, {observe: 'response'})
    .pipe(map(resp => {
      localStorage.setItem('uc-ut', data.userType);
      this.setCurrentUser(resp.body);
    }));
  }

  getUserType() {
    return localStorage.getItem('uc-ut');
  }

  getCurrentUser() {
    return JSON.parse(localStorage.getItem('uc-ud'));
  }

  setCurrentUser(userDetails) {
    localStorage.setItem('uc-ud', JSON.stringify(userDetails));
  }

  logout() {
    this.http.get(this.logoutUrl, { observe: 'response'})
    .subscribe(res => {
      const ut = this.getUserType();
      localStorage.clear();
      this.router.navigateByUrl('/login?ut=' + ut );
    }, err => {
      const ut = this.getUserType();
      localStorage.clear();
      this.router.navigateByUrl('/login?ut=' + ut );
    })

  }
}
