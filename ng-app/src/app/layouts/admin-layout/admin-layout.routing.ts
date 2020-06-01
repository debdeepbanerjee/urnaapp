import { Routes } from "@angular/router";

import { DashboardComponent } from "../../pages/dashboard/dashboard.component";
import { PatientProfileComponent } from 'src/app/pages/profile/patient-profile/patient-profile.component';
import { DoctorProfileComponent } from 'src/app/pages/profile/doctor-profile/doctor-profile.component';
import { PatientAppointmentComponent } from 'src/app/pages/appointment/patient-appointment.component';
import { DoctorAppointmentComponent } from 'src/app/pages/appointment/doctor-appointment.component';

export const AdminLayoutRoutes: Routes = [
  { path: "patient-profile", component: PatientProfileComponent },
  { path: "doctor-profile", component: DoctorProfileComponent },
  { path: "patient-appointment", component: PatientAppointmentComponent },
  { path: "doctor-appointment", component: DoctorAppointmentComponent },
];
