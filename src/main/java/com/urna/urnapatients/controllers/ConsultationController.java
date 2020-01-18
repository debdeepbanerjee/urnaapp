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
import com.urna.urnapatients.services.ConsultationService;

@CrossOrigin
@RestController
@RequestMapping("/rest/urna/consultation")
public class ConsultationController {
	ConsultationService consultationService;
	
	@Autowired
	public void setConsultationService(ConsultationService consultationService) {
		this.consultationService = consultationService;
	}

	@GetMapping("/consultations")
	public @ResponseBody Iterable<Consultation> getAllConsultations() {
	    return consultationService.findAll();
	  }
	
	@GetMapping("/consultation")
	public @ResponseBody Optional<Consultation> getConsultationById(@Valid @RequestBody Consultation consultation) {
	    return consultationService.findById(consultation.getId());
	  }
	
	
	
	@PostMapping("/consultation")
	public Consultation createConsultation(@Valid @RequestBody Consultation consultation) {
		return consultationService.insert(consultation);
	}
	
	
	@PutMapping("/consultation")
	public Consultation updateConsultation(@Valid @RequestBody Consultation consultation) {
		return consultationService.update(consultation);
	}
	
	

	@DeleteMapping("/consultation")
	public void deleteConsultation(@Valid @RequestBody Consultation consultation) {
		consultationService.delete(consultation);
	}
	
	@DeleteMapping("/consultation/id")
	public void deleteConsultationById(@Valid @RequestBody Consultation consultation) {
		consultationService.deleteById(consultation.getId());
	}
}
