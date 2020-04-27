package com.urna.urnapatients.services;

import java.util.Optional;


import com.urna.urnapatients.models.Patient;

public interface PatientService {
	public Iterable<Patient> findAll();	
	public Optional<Patient> findById(Long id);
	public Patient save(Patient patient);
	public void delete(Patient patient);
	public void deleteById(Long id);
	public Optional<Patient> findPatientByLoginIds(String mobile,String secretPasscode);
	public Optional<Patient> findPatientByLoginIdsEmail(String email,String secretPasscode);
	public Optional<Patient> findPatientByEmail(String email);	
	public Optional<Patient> findPatientByMoble(String mobile);
}
