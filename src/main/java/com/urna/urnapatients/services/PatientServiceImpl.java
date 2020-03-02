package com.urna.urnapatients.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.urna.urnapatients.models.Patient;
import com.urna.urnapatients.repo.PatientRepository;

public class PatientServiceImpl implements PatientService {

	PatientRepository patientRepository;

	@Autowired
	public void setPatientRepository(PatientRepository patientRepository) {
		this.patientRepository = patientRepository;
	}
	
	@Override
	public Iterable<Patient> findAll() {
		return patientRepository.findAll();
	}

	@Override
	public Optional<Patient> findById(Integer id) {
		return patientRepository.findById(id);
	}

	@Override
	public Patient save(Patient patient) {
		return patientRepository.save(patient);
	}

	@Override
	public void delete(Patient patient) {
		patientRepository.delete(patient);
		
	}

	@Override
	public void deleteById(Integer id) {
		patientRepository.deleteById(id);
		
	}

	@Override
	public Optional<Patient> findPatientByLoginIds(String mobile, String secretPasscode) {
		return patientRepository.findPatientByLoginIds(mobile, secretPasscode);
	}

	@Override
	public Optional<Patient> findPatientByLoginIdsEmail(String email, String secretPasscode) {
		return patientRepository.findPatientByLoginIdsEmail(email, secretPasscode);
	}

	@Override
	public Optional<Patient> findPatientByEmail(String email) {
		return patientRepository.findPatientByEmail(email);
	}

	@Override
	public Optional<Patient> findPatientByMoble(String mobile) {
		return patientRepository.findPatientByMoble(mobile);
	}

}
