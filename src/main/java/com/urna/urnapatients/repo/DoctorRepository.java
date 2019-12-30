package com.urna.urnapatients.repo;

import org.springframework.data.repository.CrudRepository;

import com.urna.urnapatients.models.Doctor;

public interface  DoctorRepository extends CrudRepository<Doctor, Integer> {

}
