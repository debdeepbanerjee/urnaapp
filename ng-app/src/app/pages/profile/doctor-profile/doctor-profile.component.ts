import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { FormlyFormOptions, FormlyFieldConfig } from '@ngx-formly/core';
import { DoctorFormModel } from 'src/app/form-models/doctor.formmodel';
import { AuthService } from 'src/app/services/auth.service';
import { ProfileService } from 'src/app/services/profile.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-doctor-profile',
  templateUrl: './doctor-profile.component.html',
  styles: [
  ]
})
export class DoctorProfileComponent implements OnInit {

  form = new FormGroup({});
  model: any = {};
  options: FormlyFormOptions = {};

  fields: FormlyFieldConfig[] = [ ...DoctorFormModel];
  constructor(private authService: AuthService, 
    private profileService: ProfileService,
    private toastr: ToastrService) { }

  ngOnInit(): void {
    this.model = this.authService.getCurrentUser();
  }
  submit() {
    const doctor = Object.assign({}, this.model);
    const password = this.authService.getCurrentUser().secretPasscode;
    doctor.secretPasscode = password;
    this.profileService.updateDoctor(doctor)
    .subscribe(res => {
      if(res.body) {
        this.authService.setCurrentUser(res.body);
        this.toastr.success('Profile updated successfully.');
      } else {
        this.toastr.error('Could not update profile');
      }
    }, err => {
      this.toastr.error('Could not update profile');
    });
  }

}
