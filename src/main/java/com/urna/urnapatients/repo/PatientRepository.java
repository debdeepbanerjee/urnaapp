package com.urna.urnapatients.repo;

import org.springframework.data.repository.CrudRepository;

import com.urna.urnapatients.models.Patient;

public interface PatientRepository extends CrudRepository<Patient, Integer>  {

}
