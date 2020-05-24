import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { FormlyFormOptions, FormlyFieldConfig } from '@ngx-formly/core';
import { PatientFormModel } from 'src/app/form-models/patient.formodel';
import { RegistrationService } from 'src/app/services/registration.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';

@Component({
  selector: 'app-patient-registration',
  templateUrl: './patient-registration.component.html',
  styles: [
  ]
})
export class PatientRegistrationComponent implements OnInit {
  form = new FormGroup({});
  model: any = {};
  options: FormlyFormOptions = {};

  fields: FormlyFieldConfig[] = [ ...PatientFormModel,
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
    const patient = Object.assign({}, this.model);
    const password = patient.secretPasscode.secretPasscode;
    delete patient.secretPasscode;
    patient.secretPasscode = password;
    console.log(patient);
    this.registrationSevice.registerPatient(patient)
    .subscribe(res => {
      console.log(res);
      if(!res.body) {
        this.toastr.error('Failed to register. Email already exist.');
      } else {
      this.toastr.success('Registration Successful. Please Login.');
      this.router.navigate(['login'], { queryParams: { ut: 'p' }});
      }
    }, err => {
      console.log(err);
      this.toastr.error('Failed to register');
    })
  }

  // form: FormGroup;

}

