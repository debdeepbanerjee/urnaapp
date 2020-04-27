package com.urna.urnapatients.models;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "appointment")
public class Appointment {
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id ;
	
	private Long consultationId;
	
	private String uniquieKey;
	
	private Long patientId;
	
	private Long doctorId;
	
	private Timestamp apptEndTime;
	
	private Long apptEndTimeN;
	
	
	public Timestamp getApptEndTime() {
		return apptEndTime;
	}

	public void setApptEndTime(Timestamp apptEndTime) {
		this.apptEndTime = apptEndTime;
	}

	public Long getApptEndTimeN() {
		return apptEndTimeN;
	}

	public void setApptEndTimeN(Long apptEndTimeN) {
		this.apptEndTimeN = apptEndTimeN;
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getConsultationId() {
		return consultationId;
	}

	public void setConsultationId(Long consultationId) {
		this.consultationId = consultationId;
	}

	public String getUniquieKey() {
		return uniquieKey;
	}

	public void setUniquieKey(String uniquieKey) {
		this.uniquieKey = uniquieKey;
	}

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}

	public Long getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}

	
}
