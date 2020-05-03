package com.urna.urnapatients.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.urna.urnapatients.models.Consultation;
import com.urna.urnapatients.models.Doctor;

public interface  DoctorRepository extends CrudRepository<Doctor, Long> {
	@Query("SELECT u FROM Doctor u where u.mobile=?1 and u.secretPasscode=?2")
	public Optional<Doctor> findDoctorByLoginIds(String mobile,String secretPasscode);
	
	@Query("SELECT u FROM Doctor u where u.email=?1 and u.secretPasscode=?2")
	public Optional<Doctor> findDoctorByLoginIdsEmail(String email,String secretPasscode);
	
	@Query("SELECT u FROM Doctor u where u.email=?1")
	public Optional<Doctor> findDoctorByEmail(String email);
	
	@Query("SELECT u FROM Doctor u where u.mobile=?1")
	public Optional<Doctor> findDoctorByMobile(String mobile);
	
	@Query("SELECT u FROM Doctor u where u.speciality like %?1%")
	public Iterable<Doctor> findAllDoctorBySpecialization(String speciality);

}
