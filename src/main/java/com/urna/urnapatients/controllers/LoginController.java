package com.urna.urnapatients.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.urna.urnapatients.models.Doctor;
import com.urna.urnapatients.models.Patient;

import com.urna.urnapatients.services.DoctorService;
import com.urna.urnapatients.services.PatientService;

@CrossOrigin
@RestController
@RequestMapping("/rest/urna/login")
public class LoginController {
	DoctorService doctorService;

	@Autowired
	public void setDoctorService(DoctorService doctorService) {
		this.doctorService = doctorService;
	}

	PatientService patientService;
	
	@Autowired
	public void setPatientService(PatientService patientService) {
		this.patientService = patientService;
	}

	@PostMapping("/doctor")
	public @ResponseBody Optional<Doctor> getDoctorByLoginId(@Valid @RequestBody Doctor doctor) {
	    return doctorService.findDoctorByLoginIds(doctor.getMobile(), doctor.getSecretPasscode());
	  }
	
	@PostMapping("/doctor/email")
	public @ResponseBody Optional<Doctor> getDoctorByLoginIdEmail(@Valid @RequestBody Doctor doctor) {
	    return doctorService.findDoctorByLoginIdsEmail(doctor.getEmail(), doctor.getSecretPasscode());
	  }
	
	@PostMapping("/patient")
	public @ResponseBody Optional<Patient> getPatientByLoginId(@Valid @RequestBody Patient patient) {
	    return patientService.findPatientByLoginIds(patient.getMobile(), patient.getSecretPasscode());
	  }
	
	@PostMapping("/patient/emial")
	public @ResponseBody Optional<Patient> getPatientByLoginIdEmail(@Valid @RequestBody Patient patient) {
	    return patientService.findPatientByLoginIdsEmail(patient.getEmail(), patient.getSecretPasscode());
	  }
}
