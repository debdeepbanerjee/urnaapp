import { Routes } from '@angular/router';

import { LoginComponent } from 'src/app/pages/login/login.component';
import { LandingComponent } from 'src/app/pages/landing/landing.component';
import { PatientRegistrationComponent } from 'src/app/pages/registration/patient-registration/patient-registration.component';
import { DoctorRegistrationComponent } from 'src/app/pages/registration/doctor-registration/doctor-registration.component';


export const AuthLayoutRoutes: Routes = [
    { path: 'landing', component: LandingComponent},
    { path: 'login', component: LoginComponent },
    { path: 'patient-registration', component: PatientRegistrationComponent },
    { path: 'doctor-registration', component: DoctorRegistrationComponent },
];
