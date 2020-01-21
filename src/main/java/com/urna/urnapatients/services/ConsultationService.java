package com.urna.urnapatients.services;

import java.util.Optional;

import com.urna.urnapatients.models.Consultation;

public interface ConsultationService {
	public Iterable<Consultation> findAll();	
	public Optional<Consultation> findById(Long id);
	public Consultation insert(Consultation consultation);
	public Consultation update(Consultation consultation);
	public void delete(Consultation consultation);
	public void deleteById(Long id);
	public Iterable<Consultation> findAllConsultationByPatientId(Integer cratedByPatientId);
	public Iterable<Consultation> findAllConsultationByRespondedDoctorId(Integer lastrespondedByDocId);

}
