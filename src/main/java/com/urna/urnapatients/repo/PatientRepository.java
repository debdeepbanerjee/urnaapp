package com.urna.urnapatients.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.urna.urnapatients.models.Patient;

public interface PatientRepository extends CrudRepository<Patient, Long>  {
	@Query("SELECT u FROM Patient u where u.mobile=?1 and u.secretPasscode=?2")
	public Optional<Patient> findPatientByLoginIds(String mobile,String secretPasscode);
	
	@Query("SELECT u FROM Patient u where u.email=?1 and u.secretPasscode=?2")
	public Optional<Patient> findPatientByLoginIdsEmail(String email,String secretPasscode);

	@Query("SELECT u FROM Patient u where u.email=?1")
	public Optional<Patient> findPatientByEmail(String email);
	
	@Query("SELECT u FROM Patient u where u.mobile=?1")
	public Optional<Patient> findPatientByMoble(String mobile);

}
