package com.urna.urnapatients.services;

import java.util.Optional;

import com.urna.urnapatients.models.Patient;

public interface PatientService {
	public Iterable<Patient> findAll();	
	public Optional<Patient> findById(Integer id);
	public Patient save(Patient patient);
	public void delete(Patient patient);
	public void deleteById(Integer id);
	public Optional<Patient> findPatientByLoginIds(String mobile,String secretPasscode);
	public Optional<Patient> findPatientByLoginIdsEmail(String email,String secretPasscode);

}
