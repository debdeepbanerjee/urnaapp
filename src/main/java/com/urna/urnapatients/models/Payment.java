package com.urna.urnapatients.models;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "payment")
public class Payment implements java.io.Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id ;
	
	private Integer doctorId ;
	
	private Integer patientId ;
	
	private BigDecimal paymentAmount;
	
	private String bankAcctNo;
	
	private String bankIfscRoutingNo;
	
	private String creditCardNo;
	
	private String cerditCardExpDate;
	
	private String creditcardSecurityCode;
	
	private String debitCardNo;
	
	private String debitCardExpDate;
	
	private String debitcardSecurityCode;
	
	private String debitPin;
	
	private Boolean paymentCompleted=false;
	
	private Boolean paymentActive=true;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	public Integer getPatientId() {
		return patientId;
	}

	public void setPatientId(Integer patientId) {
		this.patientId = patientId;
	}

	

	public BigDecimal getPaymentAmount() {
		return paymentAmount;
	}

	public void setPaymentAmount(BigDecimal paymentAmount) {
		this.paymentAmount = paymentAmount;
	}

	public Boolean getPaymentActive() {
		return paymentActive;
	}

	public void setPaymentActive(Boolean paymentUsed) {
		this.paymentActive = paymentUsed;
	}

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

	public String getDebitPin() {
		return debitPin;
	}

	public void setDebitPin(String debitPin) {
		this.debitPin = debitPin;
	}

	public Boolean getPaymentCompleted() {
		return paymentCompleted;
	}

	public void setPaymentCompleted(Boolean paymentCompleted) {
		this.paymentCompleted = paymentCompleted;
	}
	
}
