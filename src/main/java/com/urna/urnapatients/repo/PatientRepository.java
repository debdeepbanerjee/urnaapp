package com.urna.urnapatients.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.urna.urnapatients.models.Patient;

public interface PatientRepository extends CrudRepository<Patient, Integer>  {
	@Query("SELECT u FROM Patient u where u.mobile=?1 and u.secretPasscode=?2")
	public Optional<Patient> findPatientByLoginIds(String mobile,String secretPasscode);
}
