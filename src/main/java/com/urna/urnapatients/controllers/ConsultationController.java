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

import com.urna.urnapatients.models.Consultation;
import com.urna.urnapatients.models.Patient;
import com.urna.urnapatients.repo.ConsultationRepository;

@CrossOrigin
@RestController
@RequestMapping("/rest/urna/consultation")
public class ConsultationController {
	ConsultationRepository consultationRepository;
	
	@Autowired
	public void setConsultationRepository(ConsultationRepository consultationRepository) {
		this.consultationRepository = consultationRepository;
	}
	
	@GetMapping("/consultations")
	public @ResponseBody Iterable<Consultation> getAllConsultations() {
	    return consultationRepository.findAll();
	  }
	
	@GetMapping("/consultation")
	public @ResponseBody Optional<Consultation> getConsultationById(@Valid @RequestBody Consultation consultation) {
	    return consultationRepository.findById(consultation.getId());
	  }
	
	
	
	@PostMapping("/consultation")
	public Consultation createConsultation(@Valid @RequestBody Consultation consultation) {
	    return consultationRepository.save(consultation);
	}
	
	@PutMapping("/patient")
	public Consultation updateConsultation(@Valid @RequestBody Consultation consultation) {
	    return consultationRepository.save(consultation);
	}
	
	@DeleteMapping("/patient")
	public void deleteConsultation(@Valid @RequestBody Consultation consultation) {
		consultationRepository.delete(consultation);
	}
	
	@DeleteMapping("/patient/id")
	public void deleteConsultationById(@Valid @RequestBody Consultation consultation) {
		consultationRepository.deleteById(consultation.getId());;
	}
}
