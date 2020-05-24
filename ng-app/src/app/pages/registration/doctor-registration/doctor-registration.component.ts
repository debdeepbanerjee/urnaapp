import { Component, OnInit } from '@angular/core';
import { RegistrationService } from 'src/app/services/registration.service';
import { FormGroup } from '@angular/forms';
import { FormlyFormOptions, FormlyFieldConfig } from '@ngx-formly/core';
import { DoctorFormModel } from 'src/app/form-models/doctor.formmodel';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';

@Component({
  selector: 'app-doctor-registration',
  templateUrl: './doctor-registration.component.html',
  styles: [
  ]
})
export class DoctorRegistrationComponent implements OnInit {
  form = new FormGroup({});
  model: any = {};
  options: FormlyFormOptions = {};

  fields: FormlyFieldConfig[] = [ ...DoctorFormModel,
    { template: '<hr />' },
    {
        fieldGroupClassName: 'row',
        key: 'secretPasscode',
        validators: {
          fieldMatch: {
            expression: (control) => {
              const value = control.value;
    
              return value.confirmPassword === value.secretPasscode
                // avoid displaying the message error when values are empty
                || (!value.confirmPassword || !value.secretPasscode);
            },
            message: 'Password Not Matching',
            errorPath: 'confirmPassword',
          },
        },
        fieldGroup: [
            {
                className: 'col-md-6',
                type: 'input',
                key: 'secretPasscode',
                templateOptions: {
                    label: 'Password',
                    type: 'password',
                    required: true
                },
            },
            {
                className: 'col-md-6',
                type: 'input',
                key: 'confirmPassword',
                templateOptions: {
                    label: 'Confirm Password',
                    type: 'password',
                    required: true
                },
            }
        ],
    }
  ];
  constructor(private registrationSevice: RegistrationService, private toastr: ToastrService, private router: Router) { }

  ngOnInit(): void {
  }

  submit() {
    console.log(this.model);
    const doctor = Object.assign({}, this.model);
    const password = doctor.secretPasscode.secretPasscode;
    delete doctor.secretPasscode;
    doctor.secretPasscode = password;
    console.log(doctor);
    this.registrationSevice.registerDoctor(doctor)
    .subscribe(res => {
      console.log(res);
      if(!res.body) {
        this.toastr.error('Failed to register. Email already exist.');
      } else {
      this.toastr.success('Registration Successful. Please Login.');
      this.router.navigate(['public','login'], { queryParams: { ut: 'd' }});
      }
    }, err => {
      console.log(err);
      this.toastr.error('Failed to register');
    })
  }
}
