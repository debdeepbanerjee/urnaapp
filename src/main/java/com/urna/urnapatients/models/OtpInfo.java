package com.urna.urnapatients.models;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "otpinfo")
public class OtpInfo implements java.io.Serializable {
	
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id ;

private String otp;

private Integer patientId;

private Integer doctorId;

private String mobile;

private Timestamp sentOn;


public String getOtp() {
	return otp;
}

public void setOtp(String otp) {
	this.otp = otp;
}

public Integer getPatientId() {
	return patientId;
}

public void setPatientId(Integer patientId) {
	this.patientId = patientId;
}

public Integer getDoctorId() {
	return doctorId;
}

public void setDoctorId(Integer doctorId) {
	this.doctorId = doctorId;
}

public Timestamp getSentOn() {
	return sentOn;
}

public void setSentOn(Timestamp sentOn) {
	this.sentOn = sentOn;
}

public Integer getId() {
	return id;
}

public void setId(Integer id) {
	this.id = id;
}

public String getMobile() {
	return mobile;
}

public void setMobile(String mobile) {
	this.mobile = mobile;
}



}
