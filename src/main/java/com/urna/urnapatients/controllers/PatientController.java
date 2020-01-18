package com.urna.urnapatients.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.urna.urnapatients.models.Patient;
import com.urna.urnapatients.services.PatientService;

@CrossOrigin
@RestController
@RequestMapping("/rest/urna/patients")
public class PatientController {

	PatientService patientService;
	
	@Autowired
	public void setPatientService(PatientService patientService) {
		this.patientService = patientService;
	}

	@GetMapping("/patients")
	public @ResponseBody Iterable<Patient> getAllPatients() {
	    return patientService.findAll();
	  }
	
	@GetMapping("/patient")
	public @ResponseBody Optional<Patient> getPatientById(@Valid @RequestBody Patient patient) {
	    return patientService.findById(patient.getId());
	  }
	
	
	
	@PostMapping("/patient")
	public Patient createPatient(@Valid @RequestBody Patient patient) {
	    return patientService.save(patient);
	}
	
	@PutMapping("/patient")
	public Patient updatePatient(@Valid @RequestBody Patient patient) {
	    return patientService.save(patient);
	}
	
	@DeleteMapping("/patient")
	public void deletePatient(@Valid @RequestBody Patient patient) {
		patientService.delete(patient);
	}
	
	@DeleteMapping("/patient/id")
	public void deletePatientById(@Valid @RequestBody Patient patient) {
		patientService.deleteById(patient.getId());;
	}
}
