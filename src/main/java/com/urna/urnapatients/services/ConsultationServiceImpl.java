package com.urna.urnapatients.services;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;


import com.urna.urnapatients.models.Consultation;
import com.urna.urnapatients.repo.ConsultationRepository;

public class ConsultationServiceImpl implements ConsultationService{

    ConsultationRepository consultationRepository;
	
	@Autowired
	public void setConsultationRepository(ConsultationRepository consultationRepository) {
		this.consultationRepository = consultationRepository;
	}
	
	@Override
	public Iterable<Consultation> findAll() {
		return consultationRepository.findAll();
	}

	@Override
	public Optional<Consultation> findById(Long id) {
		return consultationRepository.findById(id);
	}

	@Override
	public Consultation insert(Consultation consultation) {
		updateCreateTimeStamp(consultation);
		return consultationRepository.save(consultation);
	}
	
	@Override
	public Consultation update(Consultation consultation) {
		updateRespondedTimeStamp(consultation);
		return consultationRepository.save(consultation);
	}

	@Override
	public void delete(Consultation consultation) {
		consultationRepository.delete(consultation);	
	}

	@Override
	public void deleteById(Long id) {
		consultationRepository.deleteById(id);
		
	}
	
	private void updateCreateTimeStamp(@Valid Consultation consultation) {
		Calendar cal= Calendar.getInstance();
		Date now = cal.getTime();
		Timestamp createdOn= new Timestamp(now.getTime());
		consultation.setCreatedOn(createdOn);
	}

	
	
	private void updateRespondedTimeStamp(@Valid Consultation consultation) {
		Calendar cal= Calendar.getInstance();
		Date now = cal.getTime();
		Timestamp lastRespondedOn= new Timestamp(now.getTime());
		consultation.setLastRespondedOn(lastRespondedOn);
	}

}
