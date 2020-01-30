package com.urna.urnapatients.repo;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.urna.urnapatients.models.Consultation;

public interface ConsultationRepository extends CrudRepository<Consultation, Long> {
	
	@Query("SELECT c FROM Consultation c where c.cratedByPatientId=?1")
	public Iterable<Consultation> findAllConsultationByPatientId(Integer cratedByPatientId);
	
	@Query("SELECT c FROM Consultation c where c.lastrespondedByDocId=?1")
	public Iterable<Consultation> findAllConsultationByRespondedDoctorId(Integer lastrespondedByDocId);

}
