package com.urna.urnapatients.services;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

import com.urna.urnapatients.models.Doctor;


public interface DoctorService {

	public Iterable<Doctor> findAll();	
	public Optional<Doctor> findById(Long id);
	public Doctor save(Doctor doctor);
	public void delete(Doctor doctor);
	public void deleteById(Long id);
	public Optional<Doctor> findDoctorByLoginIds(String mobile,String secretPasscode);
	public Optional<Doctor> findDoctorByLoginIdsEmail(String email,String secretPasscode);
	public Optional<Doctor> findDoctorByEmail(String email);
	public Optional<Doctor> findDoctorByMobile(String mobile);
	public Iterable<Doctor> findAllDoctorBySpecialization(String speciality);
}
