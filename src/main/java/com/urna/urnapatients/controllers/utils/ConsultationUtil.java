package com.urna.urnapatients.controllers.utils;

import javax.servlet.http.HttpSession;

import com.urna.urnapatients.dto.ConsultationDto;
import com.urna.urnapatients.models.Consultation;
import com.urna.urnapatients.models.Doctor;
import com.urna.urnapatients.models.Patient;

public class ConsultationUtil {
	public static  Consultation transformFromDto(ConsultationDto consultationDto) {
		Consultation consultation = new Consultation();
		consultation.setAdditionalQuery(consultationDto.getAdditionalQuery());
		consultation.setConsultationFor(consultationDto.getConsultationFor());
		consultation.setConsultationResponse(consultationDto.getConsultationResponse());
		consultation.setCratedByPatientId(consultationDto.getCratedByPatientId());
		consultation.setHealthIssue(consultationDto.getHealthIssue());
		consultation.setId(consultationDto.getId());
		consultation.setLastrespondedByDocId(consultationDto.getLastrespondedByDocId());
		consultation.setDurationOfHealthIssue(consultationDto.getDurationOfHealthIssue());
		consultation.setSpeciality(consultationDto.getSpeciality());
		consultation.setStatus(consultationDto.getStatus());
		return consultation;
	}
	public static void setPatientIdFromSession(HttpSession session, Consultation consultation) {
		Patient p=(Patient) session.getAttribute("patient");
		Long cratedByPatientId=p.getId();
		consultation.setCratedByPatientId(cratedByPatientId);
	}
	public static void setDoctorIdFromSession(HttpSession session, Consultation consultation) {
		Doctor d=(Doctor) session.getAttribute("doctor");
		Long respondedByDoctorId=d.getId();
		consultation.setLastrespondedByDocId(respondedByDoctorId);
	}
}
