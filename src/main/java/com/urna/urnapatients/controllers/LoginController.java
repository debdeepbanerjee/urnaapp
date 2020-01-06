package com.urna.urnapatients.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.urna.urnapatients.models.Doctor;
import com.urna.urnapatients.models.Patient;
import com.urna.urnapatients.repo.DoctorRepository;
import com.urna.urnapatients.repo.PatientRepository;

@RestController
@RequestMapping("/rest/urna/login")
public class LoginController {
	
	DoctorRepository doctorRepository;
	
	@Autowired
	public void setDoctorRepository(DoctorRepository doctorRepository) {
		this.doctorRepository = doctorRepository;
	}
	PatientRepository patientRepository;

	@Autowired
	public void setPatientRepository(PatientRepository patientRepository) {
		this.patientRepository = patientRepository;
	}
	@PostMapping("/doctor")
	public @ResponseBody Optional<Doctor> getDoctorByLoginId(@Valid @RequestBody Doctor doctor) {
	    return doctorRepository.findDoctorByLoginIds(doctor.getMobile(), doctor.getSecretPasscode());
	  }
	
	@PostMapping("/doctor/email")
	public @ResponseBody Optional<Doctor> getDoctorByLoginIdEmail(@Valid @RequestBody Doctor doctor) {
	    return doctorRepository.findDoctorByLoginIdsEmail(doctor.getEmail(), doctor.getSecretPasscode());
	  }
	
	@PostMapping("/patient")
	public @ResponseBody Optional<Patient> getPatientByLoginId(@Valid @RequestBody Patient patient) {
	    return patientRepository.findPatientByLoginIds(patient.getMobile(), patient.getSecretPasscode());
	  }
	
	@PostMapping("/patient/emial")
	public @ResponseBody Optional<Patient> getPatientByLoginIdEmail(@Valid @RequestBody Patient patient) {
	    return patientRepository.findPatientByLoginIdsEmail(patient.getEmail(), patient.getSecretPasscode());
	  }
}
