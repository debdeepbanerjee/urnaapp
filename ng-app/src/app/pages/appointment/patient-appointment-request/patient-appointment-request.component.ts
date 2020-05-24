import { Component, OnInit } from '@angular/core';
import { AppointmentService } from 'src/app/services/appointment.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-patient-appointment-request',
  templateUrl: './patient-appointment-request.component.html',
  styles: [
  ]
})
export class PatientAppointmentRequestComponent implements OnInit {

  appointmentRequests: any[];
  constructor(private appointmentService: AppointmentService, private toastr: ToastrService) { }

  ngOnInit(): void {
    this.appointmentService.appointmnetRequestCreatedEmitter.subscribe(val => {
      if('Y' === val) {
        this.load();
      }
    });
    this.load();
  }

  load() {
    this.appointmentService.getAppointmentRequestsForPatient()
    .subscribe(res => {
      this.appointmentRequests = (res.body as any[]);
    }, err => {
      console.log(err);
      this.toastr.error('Could not pull appointment requests');
    });
  }

}
