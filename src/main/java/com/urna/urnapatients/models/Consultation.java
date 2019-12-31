package com.urna.urnapatients.models;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "consultation")
public class Consultation implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id ;
	
	private String  consultationFor;
	private String 	speciality;
	private String 	healthIssue;
	private String 	durationOfHealthIssue;
	private String  additionalQuery;
	private String  consultationResponse;
	private Integer lastrespondedByDocId;
	private Integer cratedByPatientId;
	
	private String status;
	private Timestamp createdOn;
	private Timestamp lastRespondedOn;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getConsultationFor() {
		return consultationFor;
	}
	public void setConsultationFor(String consultationFor) {
		this.consultationFor = consultationFor;
	}
	public String getSpeciality() {
		return speciality;
	}
	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}
	public String getHealthIssue() {
		return healthIssue;
	}
	public void setHealthIssue(String healthIssue) {
		this.healthIssue = healthIssue;
	}
	public String getDurationOfHealthIssue() {
		return durationOfHealthIssue;
	}
	public void setDurationOfHealthIssue(String durationOfHealthIssue) {
		this.durationOfHealthIssue = durationOfHealthIssue;
	}
	public String getAdditionalQuery() {
		return additionalQuery;
	}
	public void setAdditionalQuery(String additionalQuery) {
		this.additionalQuery = additionalQuery;
	}
	public String getConsultationResponse() {
		return consultationResponse;
	}
	public void setConsultationResponse(String consultationResponse) {
		this.consultationResponse = consultationResponse;
	}
	public Integer getLastrespondedByDocId() {
		return lastrespondedByDocId;
	}
	public void setLastrespondedByDocId(Integer lastrespondedByDocId) {
		this.lastrespondedByDocId = lastrespondedByDocId;
	}
	public Integer getCratedByPatientId() {
		return cratedByPatientId;
	}
	public void setCratedByPatientId(Integer cratedByPatientId) {
		this.cratedByPatientId = cratedByPatientId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Timestamp getCreatedOn() {
		return createdOn;
	}
	public void setCreatedOn(Timestamp createdOn) {
		this.createdOn = createdOn;
	}
	public Timestamp getLastRespondedOn() {
		return lastRespondedOn;
	}
	public void setLastRespondedOn(Timestamp lastRespondedOn) {
		this.lastRespondedOn = lastRespondedOn;
	}
	
}
