package com.urna.urnapatients.controllers;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.urna.urnapatients.dto.AppointmentDto;
import com.urna.urnapatients.exceptions.AccessDeniedException;
import com.urna.urnapatients.models.Consultation;
import com.urna.urnapatients.models.Doctor;
import com.urna.urnapatients.models.Patient;
import com.urna.urnapatients.services.AppointmentService;
import org.springframework.web.bind.annotation.*;

import com.urna.urnapatients.controllers.utils.RandomPasswordOtpUtil;
import com.urna.urnapatients.models.Appointment;
import com.urna.urnapatients.repo.AppointmentRepository;
import springfox.documentation.annotations.ApiIgnore;

@CrossOrigin
@RestController
@RequestMapping("/rest/urna/appointments")
public class AppointmentController {
	private final AppointmentRepository appointmentRepository;
	private final AppointmentService appointmentService;

	public AppointmentController(AppointmentRepository appointmentRepository, AppointmentService appointmentService) {
		this.appointmentRepository = appointmentRepository;
		this.appointmentService = appointmentService;
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
	
	
	@PostMapping("/inquire/appointment")
	public Iterable<Appointment> getValidateAppointment(@Valid @RequestBody Appointment appointment) {
		
		Iterable<Appointment> appointmentByUniquieKey = appointmentRepository.getAppointmentByUniquieKey(appointment.getUniquieKey());
		
		Calendar cal= Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.HOUR,24);
		Timestamp timeStampNow= new Timestamp(cal.getTimeInMillis());
		appointmentByUniquieKey.forEach((app)->{
			Timestamp timeStampApp = app.getApptEndTime();
			long timems=timeStampNow.getTime()-timeStampApp.getTime();
			int hours=(int)timems/3600000;
			if (hours>24) {
				throw new RuntimeException("Appointment not valid !");
			}
		});
		return appointmentByUniquieKey;
	}

	@GetMapping("/pending")
	public List<AppointmentDto> pendingAppointments(@ApiIgnore HttpSession session) {
		if(session.getAttribute("patient") != null) {
			Patient patient = (Patient) session.getAttribute("patient");
			return this.appointmentService.pendingAppointmentsForPatient(patient.getId());
		}
		if(session.getAttribute("doctor") != null) {
			Doctor doctor = (Doctor) session.getAttribute("doctor");
			return this.appointmentService.pendingAppointmentsForDoctor(doctor.getId());
		}
		throw new AccessDeniedException();
	}

	@GetMapping("/completed")
	public List<AppointmentDto> completedAppointments(@ApiIgnore HttpSession session) {
		if(session.getAttribute("patient") != null) {
			Patient patient = (Patient) session.getAttribute("patient");
			return this.appointmentService.completedAppointmentsForPatient(patient.getId());
		}
		if(session.getAttribute("doctor") != null) {
			Doctor doctor = (Doctor) session.getAttribute("doctor");
			return this.appointmentService.completedAppointmentsForDoctor(doctor.getId());
		}
		throw new AccessDeniedException();
	}

	@PutMapping("/{id}/consultations")
	public void createConsultation(@PathVariable Long id, @Valid @RequestBody Consultation consultation, @ApiIgnore HttpSession session) {
		Doctor doctor = (Doctor) session.getAttribute("doctor");
		if(doctor == null) {
			throw new AccessDeniedException();
		}
		this.appointmentService.createConsultation(id, consultation);
	}

	@GetMapping("/{id}/consultations")
	public Consultation getConsultationByAppointmentId(@PathVariable Long id, @ApiIgnore HttpSession session) {
		Patient patient = (Patient) session.getAttribute("patient");
		Doctor doctor = (Doctor) session.getAttribute("doctor");
		if(patient == null && doctor == null) {
			throw new AccessDeniedException();
		}
		return this.appointmentService.getConsultation(id, patient, doctor).get();
	}
}
