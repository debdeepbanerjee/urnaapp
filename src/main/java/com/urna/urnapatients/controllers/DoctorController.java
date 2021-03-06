package com.urna.urnapatients.controllers;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import com.urna.urnapatients.dto.SearchDoctorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.urna.urnapatients.models.Doctor;
import com.urna.urnapatients.services.DoctorService;

@CrossOrigin
@RestController
@RequestMapping("/rest/urna/doctors")
public class DoctorController {
 
	private final DoctorService doctorService;

	public DoctorController(DoctorService doctorService) {
		this.doctorService = doctorService;
	}

	@GetMapping("/doctors")
	public @ResponseBody Iterable<Doctor> getAllDoctors() {
	    return doctorService.findAll();
	  }
	@GetMapping("/speciality/doctors/{speciality}")
	public @ResponseBody List<SearchDoctorDto> getAllDoctorsBySpecialization(@PathVariable String speciality) {
	    return doctorService.findAllDoctorBySpecialization(speciality);
	  }
	
	@GetMapping("/doctor")
	public @ResponseBody Optional<Doctor> getDoctorById(@Valid @RequestBody Doctor doctor) {
	    return doctorService.findById(doctor.getId());
	  }
	
	@GetMapping("/doctor/{id}")
	public @ResponseBody Optional<Doctor> getDoctorByIdStr(@PathVariable String id) {
	    return doctorService.findById(Long.parseLong(id));
	  }
	
	@GetMapping("/loggedin/doctor")
	public @ResponseBody Doctor getDoctorLoginInfo(HttpSession sess) {
		Doctor d=(Doctor) sess.getAttribute("doctor");
		return d;
	  }
	
	
	
	@PostMapping("/doctor")
	public Doctor createDoctor(@Valid @RequestBody Doctor doctor) {
		Optional<Doctor> doctorr = doctorService.findDoctorByEmail(doctor.getEmail());
	    if(doctorr.isPresent()) {    	
	    	return null;    	
	    }
		doctorr = doctorService.findDoctorByMobile(doctor.getMobile());
		if(doctorr.isPresent()) {    	
			Exception exc = new Exception("Duplicate record exception");
		    	  throw new ResponseStatusException(
		    	           HttpStatus.BAD_REQUEST, "Duplicate record", exc);   	
		    }
		return doctorService.save(doctor);
	}
	
	@PutMapping("/doctor")
	public Doctor updateDoctor(@Valid @RequestBody Doctor doctor) {
	    return doctorService.save(doctor);
	}
	
	@DeleteMapping("/doctor")
	public void deleteDoctor(@Valid @RequestBody Doctor doctor) {
		doctorService.delete(doctor);
	}
	
	@DeleteMapping("/doctor/id")
	public void deleteDoctorById(@Valid @RequestBody Doctor doctor) {
		doctorService.deleteById(doctor.getId());;
	}
}
