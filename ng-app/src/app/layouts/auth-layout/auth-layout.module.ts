import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AuthLayoutRoutes } from './auth-layout.routing';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { HttpClientModule } from "@angular/common/http";
import { LoginComponent } from 'src/app/pages/login/login.component';
import { LandingComponent } from 'src/app/pages/landing/landing.component';
import { FormlyModule } from '@ngx-formly/core';
import { DoctorRegistrationComponent } from 'src/app/pages/registration/doctor-registration/doctor-registration.component';
import { PatientRegistrationComponent } from 'src/app/pages/registration/patient-registration/patient-registration.component';
@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(AuthLayoutRoutes),
    FormsModule,
    HttpClientModule,
    NgbModule,
    ReactiveFormsModule,
    FormlyModule
  ],
  declarations: [
    LoginComponent,
    LandingComponent,
    DoctorRegistrationComponent,
    PatientRegistrationComponent
  ]
})
export class AuthLayoutModule { }
