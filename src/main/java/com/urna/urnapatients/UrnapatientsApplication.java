package com.urna.urnapatients;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.urna.urnapatients.services.ConsultationService;
import com.urna.urnapatients.services.ConsultationServiceImpl;
import com.urna.urnapatients.services.DoctorService;
import com.urna.urnapatients.services.DoctorServiceImpl;
import com.urna.urnapatients.services.PatientService;
import com.urna.urnapatients.services.PatientServiceImpl;

@SpringBootApplication
public class UrnapatientsApplication {

	public static void main(String[] args) {
		SpringApplication.run(UrnapatientsApplication.class, args);
	}
	
	@Bean
	public ConsultationService getConsultationService() {
		return new ConsultationServiceImpl();
	}
	
	@Bean
	public  DoctorService getDoctorService() {
		return new DoctorServiceImpl();
	}

	@Bean
	public PatientService getPatientService() {
		return new PatientServiceImpl();
	}
}
