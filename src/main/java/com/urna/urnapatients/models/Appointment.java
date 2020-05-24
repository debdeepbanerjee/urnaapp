package com.urna.urnapatients.models;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
@Table(name = "appointment")
public class Appointment {
	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id ;
	
	private Long consultationId;
	
	private String uniquieKey;

	@ManyToOne
	@JoinColumn(name="patient_id", nullable=false)
	private Patient patient;

	@ManyToOne
	@JoinColumn(name="doctor_id", nullable=false)
	private Doctor doctor;
	
	private Timestamp apptEndTime;
	
	private Long apptEndTimeN;

	private Long appointmentRequestId;

	@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime scheduledDate;



	
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

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Long getAppointmentRequestId() {
		return appointmentRequestId;
	}

	public void setAppointmentRequestId(Long appointmentRequestId) {
		this.appointmentRequestId = appointmentRequestId;
	}

	public LocalDateTime getScheduledDate() {
		return scheduledDate;
	}

	public void setScheduledDate(LocalDateTime scheduledDate) {
		this.scheduledDate = scheduledDate;
	}
}
