package com.urna.urnapatients.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.urna.urnapatients.models.Doctor;
import com.urna.urnapatients.repo.DoctorRepository;

@RestController
@RequestMapping("/rest/urna/doctors")
public class DoctorController {
 
	DoctorRepository doctorRepository;
	
	@Autowired
	public void setDoctorRepository(DoctorRepository doctorRepository) {
		this.doctorRepository = doctorRepository;
	}

	@GetMapping("/doctors")
	public @ResponseBody Iterable<Doctor> getAllDoctors() {
	    return doctorRepository.findAll();
	  }
	
	@GetMapping("/doctor")
	public @ResponseBody Optional<Doctor> getDoctorById(@Valid @RequestBody Doctor doctor) {
	    return doctorRepository.findById(doctor.getId());
	  }
	
	
	
	@PostMapping("/doctor")
	public Doctor createDoctor(@Valid @RequestBody Doctor doctor) {
	    return doctorRepository.save(doctor);
	}
	
	@PutMapping("/doctor")
	public Doctor updateDoctor(@Valid @RequestBody Doctor doctor) {
	    return doctorRepository.save(doctor);
	}
	
	@DeleteMapping("/doctor")
	public void deleteDoctor(@Valid @RequestBody Doctor doctor) {
	     doctorRepository.delete(doctor);
	}
	
	@DeleteMapping("/doctor")
	public void deleteDoctorById(@Valid @RequestBody Doctor doctor) {
	     doctorRepository.deleteById(doctor.getId());;
	}
}
