package com.urna.urnapatients.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.urna.urnapatients.models.Doctor;
import com.urna.urnapatients.repo.DoctorRepository;

public class DoctorServiceImpl implements DoctorService {
	DoctorRepository doctorRepository;
	
	@Autowired
	public void setDoctorRepository(DoctorRepository doctorRepository) {
		this.doctorRepository = doctorRepository;
	}
	@Override
	public Iterable<Doctor> findAll() {
		return doctorRepository.findAll();
	}

	@Override
	public Optional<Doctor> findById(Long id) {
		return doctorRepository.findById(id);
	}

	@Override
	public Doctor save(Doctor doctor) {
		return doctorRepository.save(doctor);
	}

	@Override
	public void delete(Doctor doctor) {
		doctorRepository.delete(doctor);		
	}

	@Override
	public void deleteById(Long id) {
		doctorRepository.deleteById(id);
		
	}
	
	@Override
	public Optional<Doctor> findDoctorByLoginIds(String mobile, String secretPasscode) {
		return doctorRepository.findDoctorByLoginIds(mobile, secretPasscode);
	}
	@Override
	public Optional<Doctor> findDoctorByLoginIdsEmail(String email, String secretPasscode) {
		return doctorRepository.findDoctorByLoginIdsEmail(email, secretPasscode);
	}
	@Override
	public Optional<Doctor> findDoctorByEmail(String email) {
		return doctorRepository.findDoctorByEmail(email);
	}
	@Override
	public Optional<Doctor> findDoctorByMobile(String mobile) {
		return doctorRepository.findDoctorByMobile(mobile);
	}

}
