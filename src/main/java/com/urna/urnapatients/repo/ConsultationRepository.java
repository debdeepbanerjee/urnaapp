package com.urna.urnapatients.repo;

import org.springframework.data.repository.CrudRepository;
import com.urna.urnapatients.models.Consultation;

public interface ConsultationRepository extends CrudRepository<Consultation, Long> {

}
