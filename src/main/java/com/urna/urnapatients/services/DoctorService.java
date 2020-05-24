package com.urna.urnapatients.services;

import java.util.List;
import java.util.Optional;

import com.urna.urnapatients.dto.SearchDoctorDto;
import com.urna.urnapatients.mapper.SearchDoctorMapper;

import com.urna.urnapatients.models.Doctor;
import com.urna.urnapatients.repo.DoctorRepository;
import org.springframework.stereotype.Service;

@Service
public class DoctorService {
	private final DoctorRepository doctorRepository;
	private final SearchDoctorMapper searchDoctorMapper;

	public DoctorService(DoctorRepository doctorRepository, SearchDoctorMapper searchDoctorMapper) {
		this.doctorRepository = doctorRepository;
		this.searchDoctorMapper = searchDoctorMapper;
	}

	
	public Iterable<Doctor> findAll() {
		return doctorRepository.findAll();
	}

	
	public Optional<Doctor> findById(Long id) {
		return doctorRepository.findById(id);
	}

	
	public Doctor save(Doctor doctor) {
		return doctorRepository.save(doctor);
	}

	
	public void delete(Doctor doctor) {
		doctorRepository.delete(doctor);		
	}

	
	public void deleteById(Long id) {
		doctorRepository.deleteById(id);
		
	}
	
	
	public Optional<Doctor> findDoctorByLoginIds(String mobile, String secretPasscode) {
		return doctorRepository.findDoctorByLoginIds(mobile, secretPasscode);
	}
	
	public Optional<Doctor> findDoctorByLoginIdsEmail(String email, String secretPasscode) {
		return doctorRepository.findDoctorByLoginIdsEmail(email, secretPasscode);
	}
	
	public Optional<Doctor> findDoctorByEmail(String email) {
		return doctorRepository.findDoctorByEmail(email);
	}
	
	public Optional<Doctor> findDoctorByMobile(String mobile) {
		return doctorRepository.findDoctorByMobile(mobile);
	}
	
	public List<SearchDoctorDto> findAllDoctorBySpecialization(String speciality) {
		return this.searchDoctorMapper.toDto(doctorRepository.findBySpeciality(speciality));
	}

}
