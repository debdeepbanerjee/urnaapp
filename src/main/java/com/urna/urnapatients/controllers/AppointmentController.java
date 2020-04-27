package com.urna.urnapatients.controllers;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.urna.urnapatients.controllers.utils.RandomPasswordOtpUtil;
import com.urna.urnapatients.models.Appointment;
import com.urna.urnapatients.repo.AppointmentRepository;

@CrossOrigin
@RestController
@RequestMapping("/rest/urna/appointments")
public class AppointmentController {
	AppointmentRepository appointmentRepository;
	public AppointmentRepository getAppointmentRepository() {
		return appointmentRepository;
	}
	@Autowired
	public void setAppointmentRepository(AppointmentRepository appointmentRepository) {
		this.appointmentRepository = appointmentRepository;
	}
	@PostMapping("/appointment")
	public Appointment getUpdateAppointment(@Valid @RequestBody Appointment appointment) {
		Calendar cal= Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.HOUR,24);
		
		Timestamp apptEndTime= new Timestamp(cal.getTimeInMillis());
		appointment.setApptEndTime(apptEndTime);
		appointment.setApptEndTimeN(cal.getTimeInMillis());
		appointment.setUniquieKey(RandomPasswordOtpUtil.getGeneratedPassword(10).toString());
		appointmentRepository.save(appointment);
		return appointment;
		
	}
	
}
