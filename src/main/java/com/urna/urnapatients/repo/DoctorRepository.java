package com.urna.urnapatients.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.urna.urnapatients.models.Doctor;

public interface  DoctorRepository extends CrudRepository<Doctor, Integer> {
	@Query("SELECT u FROM Doctor u where u.mobile=?1 and u.secretPasscode=?2")
	public Optional<Doctor> findDoctorByLoginIds(String mobile,String secretPasscode);
}
