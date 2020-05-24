import { Injectable, EventEmitter } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AppointmentService {
  private doctorAppointmentRequestUrl = '/rest/urna/appointment-requests/doctor';
  private patientAppointmentRequestUrl = '/rest/urna/appointment-requests/patient';
  private searchDoctorBySpeciality = '/rest/urna/doctors/speciality/doctors/';
  private createAppointmentRequestUrl = '/rest/urna/appointment-requests';
  private appointmentRequestApproveUrl = '/rest/urna/appointment-requests/approve/';
  private appointmentRequestRejectUrl = '/rest/urna/appointment-requests/reject/';
  private pendingAppointmentsUrl = '/rest/urna/appointments/pending';
  private completedAppointmentsUrl = '/rest/urna/appointments/completed';

  appointmnetRequestCreatedEmitter = new EventEmitter<string>();
  consultationCreatedEmitter = new EventEmitter<string>();
  constructor(private http: HttpClient) { }

  getAppointmentRequestsForPatient() {
    return this.http.get(this.patientAppointmentRequestUrl, { observe: 'response'});
  }

  getAppointmentRequestsForDoctor() {
    return this.http.get(this.doctorAppointmentRequestUrl, { observe: 'response'});
  }

  searchDoctor(speciality: string) {
    return this.http.get(this.searchDoctorBySpeciality + speciality, { observe: 'response'});
  }

  createAppointmentRequest(ar) {
    return this.http.post(this.createAppointmentRequestUrl, ar, { observe: 'response'});
  }

  approveAppointmentRequest(id, scheduleDateTime) {
    return this.http.post(this.appointmentRequestApproveUrl + id , {
      requestTime: scheduleDateTime
    }, { observe: 'response'});
  }

  rejectAppointmentRequest(id, reason) {
    return this.http.post(this.appointmentRequestRejectUrl + id , reason, { observe: 'response'});
  }

  pendingAppointments() {
    return this.http.get(this.pendingAppointmentsUrl, { observe: 'response'});
  }

  completedAppointments() {
    return this.http.get(this.completedAppointmentsUrl, { observe: 'response'});
  }

  addConsultation(appointmentId, consultation) {
    return this.http.put('/rest/urna/appointments/' + appointmentId + '/consultations', consultation);
  }

  getConsulationByAppointmentId(appointmentId) {
    return this.http.get('/rest/urna/appointments/' + appointmentId + '/consultations',{ observe: 'response'});
  }
}
