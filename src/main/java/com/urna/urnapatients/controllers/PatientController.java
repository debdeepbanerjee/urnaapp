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
import com.urna.urnapatients.repo.PatientRepository;

@CrossOrigin
@RestController
@RequestMapping("/rest/urna/patients")
public class PatientController {

	PatientRepository patientRepository;

	@Autowired
	public void setPatientRepository(PatientRepository patientRepository) {
		this.patientRepository = patientRepository;
	}
	
	@GetMapping("/patients")
	public @ResponseBody Iterable<Patient> getAllPatients() {
	    return patientRepository.findAll();
	  }
	
	@GetMapping("/patient")
	public @ResponseBody Optional<Patient> getPatientById(@Valid @RequestBody Patient patient) {
	    return patientRepository.findById(patient.getId());
	  }
	
	
	
	@PostMapping("/patient")
	public Patient createPatient(@Valid @RequestBody Patient patient) {
	    return patientRepository.save(patient);
	}
	
	@PutMapping("/patient")
	public Patient updatePatient(@Valid @RequestBody Patient patient) {
	    return patientRepository.save(patient);
	}
	
	@DeleteMapping("/patient")
	public void deletePatient(@Valid @RequestBody Patient patient) {
		patientRepository.delete(patient);
	}
	
	@DeleteMapping("/patient/id")
	public void deletePatientById(@Valid @RequestBody Patient patient) {
		patientRepository.deleteById(patient.getId());;
	}
}
