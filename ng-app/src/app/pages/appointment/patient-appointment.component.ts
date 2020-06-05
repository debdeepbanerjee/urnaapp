import { Component, OnInit, OnDestroy } from '@angular/core';
import { AppointmentService } from 'src/app/services/appointment.service';
import { timer, Subscription } from 'rxjs';

@Component({
  selector: 'app-patient-appointment',
  templateUrl: './patient-appointment.component.html',
  styles: [
  ]
})
export class PatientAppointmentComponent implements OnInit, OnDestroy {
  private timerSource = timer(2 * 60 * 1000, 2 * 60 * 1000); /// every 2 mins
  private timerSubscription: Subscription;
  constructor(private appointmentService: AppointmentService) { }

  ngOnDestroy(): void {
    if(!!this.timerSubscription) {
      this.timerSubscription.unsubscribe();
    }
  }

  ngOnInit(): void {
    this.timerSubscription = this.timerSource.subscribe(v => {
      this.appointmentService.refreshListEmitter.emit();
    });
  }

}
