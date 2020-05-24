package com.urna.urnapatients.controllers;

import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.urna.urnapatients.controllers.utils.ConsultationUtil;
import com.urna.urnapatients.dto.ConsultationDto;
import com.urna.urnapatients.models.Consultation;
import com.urna.urnapatients.models.Doctor;
import com.urna.urnapatients.models.Patient;
import com.urna.urnapatients.services.ConsultationService;
import springfox.documentation.annotations.ApiIgnore;

@CrossOrigin
@RestController
@RequestMapping("/rest/urna/consultations")
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
	
	@GetMapping("/consultations/patient")
	public @ResponseBody Iterable<Consultation> getAllConsultationsForPatient(@Valid @RequestBody Long patientId) {
	    return consultationService.findAllConsultationByPatientId(patientId);
	  }
	
	@GetMapping("/consultations/doctor")
	public @ResponseBody Iterable<Consultation> getAllConsultationsForDoctor(@Valid @RequestBody Long doctorId) {
	    return consultationService.findAllConsultationByRespondedDoctorId(doctorId);
	  }
	
	@GetMapping("/consultations/secure/patient")
	public @ResponseBody Iterable<Consultation> getAllConsultationsForPatientSecure(HttpSession session) {
		Long patientId=0l;
		Patient p = (Patient) session.getAttribute("patient");
		if(p != null) {
		patientId = p.getId(); 
		}
	    return consultationService.findAllConsultationByPatientId(patientId);
	  }
	
	@GetMapping("/consultations/secure/patient/id/{id}")
	public @ResponseBody Iterable<Consultation> getAllConsultationsForPatientSecureById(@PathVariable String id) {	
	    return consultationService.findAllConsultationByPatientId(Long.parseLong(id));
	  }
	
	@GetMapping("/consultations/secure/doctor")
	public @ResponseBody Iterable<Consultation> getAllConsultationsForDoctorSecure(HttpSession session) {
		Doctor d = (Doctor) session.getAttribute("doctor");
		Long doctorId = d.getId(); 
		return consultationService.findAllConsultationByRespondedDoctorId(doctorId);
	  }
	
	
	@GetMapping("/consultation")
	public @ResponseBody Optional<Consultation> getConsultationById(@Valid @RequestBody Consultation consultation) {
	    return consultationService.findById(consultation.getId());
	  }
	
	
	
	@PostMapping("/consultation")
	public Consultation createConsultation(@Valid @RequestBody ConsultationDto consultationDto,HttpSession session) {
		Consultation consultation = ConsultationUtil.transformFromDto(consultationDto);
		ConsultationUtil.setPatientIdFromSession(session, consultation);
		return consultationService.insert(consultation);
	}
	
	@PostMapping("/nosession/consultation/{id}")
	public Consultation createConsultationNoSession(@Valid @RequestBody ConsultationDto consultationDto,@PathVariable String id) {
		Consultation consultation = ConsultationUtil.transformFromDto(consultationDto);
		consultation.setCratedByPatientId(Long.parseLong(id));
		return consultationService.insert(consultation);
	}

	
	
	
	@PutMapping("/consultation")
	public Consultation updateConsultation(@Valid @RequestBody ConsultationDto consultationDto,HttpSession session) {
		Consultation consultation = ConsultationUtil.transformFromDto(consultationDto);
		ConsultationUtil.setDoctorIdFromSession(session, consultation);
		return consultationService.update(consultation);
	}
	

	@PutMapping("/nosession/consultation/{id}")
	public Consultation updateConsultationNoSession(@Valid @RequestBody ConsultationDto consultationDto,@PathVariable String id) {
		Consultation consultation = ConsultationUtil.transformFromDto(consultationDto);
		consultation.setLastrespondedByDocId(Long.parseLong(id));
		return consultationService.update(consultation);
	}

	
	
	

	@DeleteMapping("/consultation")
	public void deleteConsultation(@Valid @RequestBody ConsultationDto consultationDto) {
		Consultation consultation = ConsultationUtil.transformFromDto(consultationDto);
		consultationService.delete(consultation);
	}
	
	@DeleteMapping("/consultation/id")
	public void deleteConsultationById(@Valid @RequestBody Consultation consultation) {
		consultationService.deleteById(consultation.getId());
	}
}
