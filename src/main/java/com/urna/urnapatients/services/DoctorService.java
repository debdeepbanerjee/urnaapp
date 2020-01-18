package com.urna.urnapatients.services;

import java.util.Optional;

import com.urna.urnapatients.models.Doctor;


public interface DoctorService {

	public Iterable<Doctor> findAll();	
	public Optional<Doctor> findById(Integer id);
	public Doctor save(Doctor doctor);
	public void delete(Doctor doctor);
	public void deleteById(Integer id);
	public Optional<Doctor> findDoctorByLoginIds(String mobile,String secretPasscode);
	public Optional<Doctor> findDoctorByLoginIdsEmail(String email,String secretPasscode);

}
