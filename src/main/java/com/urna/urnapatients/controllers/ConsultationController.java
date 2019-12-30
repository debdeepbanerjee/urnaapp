package com.urna.urnapatients.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.urna.urnapatients.repo.ConsultationRepository;

@RestController
@RequestMapping("/rest/urna/consultation")
public class ConsultationController {
	ConsultationRepository consultationRepository;
	
	@Autowired
	public void setConsultationRepository(ConsultationRepository consultationRepository) {
		this.consultationRepository = consultationRepository;
	}
}
