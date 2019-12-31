package com.urna.urnapatients.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.urna.urnapatients.models.Doctor;

public interface  DoctorRepository extends CrudRepository<Doctor, Integer> {
	@Query("SELECT u FROM doctor where u.mobile=?1 and u.secret_passcode=?2")
	public Optional<Doctor> findDoctorByLoginIds(String mobile,String secretPasscode);
}
