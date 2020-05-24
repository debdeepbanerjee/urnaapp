import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormGroup } from '@angular/forms';
import { FormlyFormOptions, FormlyFieldConfig } from '@ngx-formly/core';
import { AuthService } from 'src/app/services/auth.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styles: [
  ]
})
export class LoginComponent implements OnInit {
  userType = '';
  title = '';
  registrationUrl = '';
  form = new FormGroup({});
  model: any = {};
  options: FormlyFormOptions = {};
  fields: FormlyFieldConfig[] = [
    {
      key: 'email',
      type: 'input',
      templateOptions: {
        label: 'Email',
        type: 'email',
        placeholder: 'Email',
        required: true,
        pattern: /^\S+@\S+$/
      },
    },
    {
      key: 'password',
      type: 'input',
      templateOptions: {
        label: 'Password',
        type: 'password',
        placeholder: 'Password',
        required: true,
      },
    },
  ];
  constructor(private route: ActivatedRoute, 
    private router: Router, 
    private authService: AuthService,
    private toastr: ToastrService) { }

  ngOnInit(): void {
    this.route.queryParamMap.subscribe(pm => {
      this.userType =  pm.get('ut');
      if(!this.userType) {
        this.router.navigateByUrl('/');
      } else {
        if(this.userType === 'p') {
          this.title = 'Patient';
          this.registrationUrl = '/patient-registration';
        } else  if(this.userType === 'd') {
          this.title = "Doctor";
          this.registrationUrl = '/doctor-registration';
        } else {
          this.router.navigateByUrl('/');
        }
      }
    });
  }

  submit() {
    const credentials = Object.assign({}, this.model);
    credentials.userType = this.userType;
    this.authService.login(credentials)
    .subscribe(res => {
      this.router.navigateByUrl('/dashboard');
    }, err => {
      console.log(err);
      this.toastr.error('Unable to login');
    });
  }
}
