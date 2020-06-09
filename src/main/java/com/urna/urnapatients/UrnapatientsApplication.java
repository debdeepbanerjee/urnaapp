package com.urna.urnapatients;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.urna.urnapatients.services.ConsultationService;
import com.urna.urnapatients.services.ConsultationServiceImpl;
import com.urna.urnapatients.services.OtpService;
import com.urna.urnapatients.services.OtpServiceImpl;
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
	public PatientService getPatientService() {
		return new PatientServiceImpl();
	}
	@Bean
	public OtpService getOtpService() {
		return new OtpServiceImpl();
	}
	
	
}
