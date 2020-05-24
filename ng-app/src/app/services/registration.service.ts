import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class RegistrationService {
  patientRegistrationUrl = '/rest/urna/patients/patient';
  doctorRegistrationUrl = '/rest/urna/doctors/doctor';
  constructor(private http: HttpClient) { }

  registerPatient(patientDetail: any) {
    return this.http.post(this.patientRegistrationUrl, patientDetail, { observe: 'response' });
  }
  registerDoctor(doctorDetail: any) {
    return this.http.post(this.doctorRegistrationUrl, doctorDetail, { observe: 'response' });
  }
}
