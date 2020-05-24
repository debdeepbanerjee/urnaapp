import { Component, OnInit, Input } from '@angular/core';
import { AppointmentService } from 'src/app/services/appointment.service';
import { ToastrService } from 'ngx-toastr';
import { environment } from 'src/environments/environment';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ConsultationViewComponent } from '../consultation-view/consultation-view.component';

@Component({
  selector: 'app-patient-appointment-list',
  templateUrl: './patient-appointment-list.component.html',
  styles: [
  ]
})
export class PatientAppointmentListComponent implements OnInit {

  appointments: any[];
  @Input() completed = false;
  constructor(
    private appointmentService: AppointmentService, 
    private toastr: ToastrService,
    private modalService: NgbModal) { }

  ngOnInit(): void {
    this.load();
  }

  load() {
    if (!this.completed) {
      this.appointmentService.pendingAppointments()
      .subscribe(res => this.appointments = res.body as any[],
        err => {
          console.log(err);
          this.toastr.error('Unable to fetch upcoming appointments.');
        });
    } else {
      this.appointmentService.completedAppointments()
      .subscribe(res => this.appointments = res.body as any[],
        err => {
          console.log(err);
          this.toastr.error('Unable to fetch past appointments.');
        });
    }

  }

  getChatLink(apmt) {
    return environment.CHAT_SERVER_URL + '/chat?room=' + apmt.uniquieKey;
  }

  enableChat(apmt) {
    const scheduledTime = new Date(apmt.scheduledDate).getTime();
    const scheduledTimeUpto = scheduledTime + 15 * 60 * 1000;
    const currentTime = new Date().getTime();
    if(currentTime >= scheduledTime && currentTime <= scheduledTimeUpto) {
      return true;
    } else {
      return false;
    }
  }

  openConsultation(appointment) {
    const modalRef = this.modalService.open(ConsultationViewComponent, { size: 'lg', scrollable: true });
    modalRef.componentInstance.appointment = appointment;
  }

}
