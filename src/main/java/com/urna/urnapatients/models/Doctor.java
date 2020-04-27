package com.urna.urnapatients.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "doctor")
public class Doctor implements java.io.Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id ;
	
	@Column(unique = true)
	private String email;
	
	private String firstName;
	
	private String lastName;
	
	private String middleName;
	
	private String fullName;
	
	private String speciality;
	
	
	private String qualifications;
	
	private String practice;
	
	private String specializations;
	
	private String languageSpoken;
	
	private String phone;
	
	@Column(unique = true)
	private String mobile;
	
	private String address;
	
	private String dob;
	
	private String secretPasscode;
	
	private String registrationNumber;
	
	private String gender;
	
	private String consultationFee;
	
	private String bankAcctNo;
	
	private String bankIfscRoutingNo;
	
	private String creditCardNo;
	
	private String cerditCardExpDate;
	
	private String creditcardSecurityCode;
	
	private String debitCardNo;
	
	private String debitCardExpDate;
	
	private String debitcardSecurityCode;
	
	public String getBankAcctNo() {
		return bankAcctNo;
	}

	public void setBankAcctNo(String bankAcctNo) {
		this.bankAcctNo = bankAcctNo;
	}

	public String getBankIfscRoutingNo() {
		return bankIfscRoutingNo;
	}

	public void setBankIfscRoutingNo(String bankIfscRoutingNo) {
		this.bankIfscRoutingNo = bankIfscRoutingNo;
	}

	public String getCreditCardNo() {
		return creditCardNo;
	}

	public void setCreditCardNo(String creditCardNo) {
		this.creditCardNo = creditCardNo;
	}

	public String getCerditCardExpDate() {
		return cerditCardExpDate;
	}

	public void setCerditCardExpDate(String cerditCardExpDate) {
		this.cerditCardExpDate = cerditCardExpDate;
	}

	public String getCreditcardSecurityCode() {
		return creditcardSecurityCode;
	}

	public void setCreditcardSecurityCode(String creditcardSecurityCode) {
		this.creditcardSecurityCode = creditcardSecurityCode;
	}

	public String getDebitCardNo() {
		return debitCardNo;
	}

	public void setDebitCardNo(String debitCardNo) {
		this.debitCardNo = debitCardNo;
	}

	public String getDebitCardExpDate() {
		return debitCardExpDate;
	}

	public void setDebitCardExpDate(String debitCardExpDate) {
		this.debitCardExpDate = debitCardExpDate;
	}

	public String getDebitcardSecurityCode() {
		return debitcardSecurityCode;
	}

	public void setDebitcardSecurityCode(String debitcardSecurityCode) {
		this.debitcardSecurityCode = debitcardSecurityCode;
	}

	
	
	
	


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getSpeciality() {
		return speciality;
	}

	public void setSpeciality(String speciality) {
		this.speciality = speciality;
	}

	public String getQualifications() {
		return qualifications;
	}

	public void setQualifications(String qualifications) {
		this.qualifications = qualifications;
	}

	public String getPractice() {
		return practice;
	}

	public void setPractice(String practice) {
		this.practice = practice;
	}

	public String getSpecializations() {
		return specializations;
	}

	public void setSpecializations(String specializations) {
		this.specializations = specializations;
	}

	public String getLanguageSpoken() {
		return languageSpoken;
	}

	public void setLanguageSpoken(String languageSpoken) {
		this.languageSpoken = languageSpoken;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getSecretPasscode() {
		return secretPasscode;
	}

	public void setSecretPasscode(String secretPin) {
		this.secretPasscode = secretPin;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getConsultationFee() {
		return consultationFee;
	}

	public void setConsultationFee(String consultationFee) {
		this.consultationFee = consultationFee;
	}
	
	
	

}
