package com.urna.urnapatients.dto;


import java.util.Set;

public class ConsultationDto implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private Long id ;
	
	private String  consultationFor;
	private String 	speciality;
	private String 	healthIssue;
	private String 	durationOfHealthIssue;
	private String  additionalQuery;
	private String  consultationResponse;
	private Long lastrespondedByDocId;
	private Long cratedByPatientId;
	
	private String status;
	private Set<MedicalFileDto> medicalFiles;
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
	public Long getLastrespondedByDocId() {
		return lastrespondedByDocId;
	}
	public void setLastrespondedByDocId(Long lastrespondedByDocId) {
		this.lastrespondedByDocId = lastrespondedByDocId;
	}
	public Long getCratedByPatientId() {
		return cratedByPatientId;
	}
	public void setCratedByPatientId(Long cratedByPatientId) {
		this.cratedByPatientId = cratedByPatientId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public Set<MedicalFileDto> getMedicalFiles() {
		return medicalFiles;
	}

	public void setMedicalFiles(Set<MedicalFileDto> medicalFiles) {
		this.medicalFiles = medicalFiles;
	}
}
