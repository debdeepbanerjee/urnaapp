package com.urna.urnapatients.controllers;

import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.urna.urnapatients.dto.LoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	public @ResponseBody Optional<Doctor> getDoctorByLoginId(@Valid @RequestBody Doctor doctor, HttpSession sess) {
	    Optional<Doctor> doctorByLoginIds = doctorService.findDoctorByLoginIds(doctor.getMobile(), doctor.getSecretPasscode());
		sess.setAttribute("doctor", doctorByLoginIds);
	    return doctorByLoginIds;
	  }
	
	@PostMapping("/doctor/email")
	public @ResponseBody Optional<Doctor> getDoctorByLoginIdEmail(@Valid @RequestBody Doctor doctor, HttpSession sess) {
	    Optional<Doctor> doctorByLoginIdsEmail = doctorService.findDoctorByLoginIdsEmail(doctor.getEmail(), doctor.getSecretPasscode());
	    sess.setAttribute("doctor", doctorByLoginIdsEmail);
	    return doctorByLoginIdsEmail;
	  }
	
	@PostMapping("/patient")
	public @ResponseBody Optional<Patient> getPatientByLoginId(@Valid @RequestBody Patient patient, HttpSession sess ) {
	    Optional<Patient> patientByLoginIds = patientService.findPatientByLoginIds(patient.getMobile(), patient.getSecretPasscode());
		sess.setAttribute("patient", patientByLoginIds);
	    return patientByLoginIds;
	  }
	
	@PostMapping("/patient/email")
	public @ResponseBody Optional<Patient> getPatientByLoginIdEmail(@Valid @RequestBody Patient patient, HttpSession sess) {
	    Optional<Patient> patientByLoginIdsEmail = patientService.findPatientByLoginIdsEmail(patient.getEmail(), patient.getSecretPasscode());
		sess.setAttribute("patient", patientByLoginIdsEmail);
	    return patientByLoginIdsEmail;
	  }

	@PostMapping()
	public ResponseEntity authenticate(@Valid @RequestBody LoginDto loginDto, HttpSession sess) {
		if("p".equalsIgnoreCase(loginDto.getUserType())) {
			Optional<Patient> patientOptional = patientService.findPatientByLoginIdsEmail(loginDto.getEmail(), loginDto.getPassword());
			if(patientOptional.isPresent()) {
				Patient patient = patientOptional.get();
				sess.setAttribute("patient", patient);
				return ResponseEntity.ok(patient);
			}
		} else if("d".equalsIgnoreCase(loginDto.getUserType())) {
			Optional<Doctor> doctorOptional = doctorService.findDoctorByLoginIdsEmail(loginDto.getEmail(), loginDto.getPassword());
			if(doctorOptional.isPresent()) {
				Doctor doctor = doctorOptional.get();
				sess.setAttribute("doctor", doctor);
				return ResponseEntity.ok(doctor);
			}
		}

		return ResponseEntity.badRequest().body("Login Failed");
	}
}
