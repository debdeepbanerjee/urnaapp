import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {
  doctorUpdateUrl = '/rest/urna/doctors/doctor';
  patientUpdateUrl = '/rest/urna/patients/patient';
  constructor(private http: HttpClient) { }

  updatePatient(patient) {
    return this.http.put(this.patientUpdateUrl, patient, { observe: 'response' });
  }

  updateDoctor(doctor) {
    return this.http.put(this.doctorUpdateUrl, doctor, { observe: 'response' });
  }
}
