package com.urna.urnapatients.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.urna.urnapatients.repo.PatientRepository;

@RestController
@RequestMapping("/rest/urna/patients")
public class PatientController {

	PatientRepository patientRepository;

	@Autowired
	public void setPatientRepository(PatientRepository patientRepository) {
		this.patientRepository = patientRepository;
	}
}
