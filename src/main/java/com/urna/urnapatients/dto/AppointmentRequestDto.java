package com.urna.urnapatients.dto;

import com.urna.urnapatients.models.AppointmentRequestStatus;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class AppointmentRequestDto implements Serializable {
    private Long id;
    private String requestId;
    private Long patientId;
    private String patientFirstName;
    private String patientMiddleName;
    private String patientLastName;
    private Long doctorId;
    private String doctorFirstName;
    private String doctorMiddleName;
    private String doctorLastName;
    private String doctorQualifications;
    private String doctorSpeciality;
    private String healthIssueDescription;
    private LocalDate desiredDate;
    private Byte desiredStartHours;
    private Byte desiredEndHours;
    private Boolean dateFlexible;
    private Boolean hoursFlexible;
    private AppointmentRequestStatus requestStatus;
    private LocalDateTime createdAt;
    private String rejectReason;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public String getPatientFirstName() {
        return patientFirstName;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    public String getPatientMiddleName() {
        return patientMiddleName;
    }

    public void setPatientMiddleName(String patientMiddleName) {
        this.patientMiddleName = patientMiddleName;
    }

    public String getPatientLastName() {
        return patientLastName;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public String getDoctorFirstName() {
        return doctorFirstName;
    }

    public void setDoctorFirstName(String doctorFirstName) {
        this.doctorFirstName = doctorFirstName;
    }

    public String getDoctorMiddleName() {
        return doctorMiddleName;
    }

    public void setDoctorMiddleName(String doctorMiddleName) {
        this.doctorMiddleName = doctorMiddleName;
    }

    public String getDoctorLastName() {
        return doctorLastName;
    }

    public void setDoctorLastName(String doctorLastName) {
        this.doctorLastName = doctorLastName;
    }

    public String getDoctorQualifications() {
        return doctorQualifications;
    }

    public void setDoctorQualifications(String doctorQualifications) {
        this.doctorQualifications = doctorQualifications;
    }

    public String getDoctorSpeciality() {
        return doctorSpeciality;
    }

    public void setDoctorSpeciality(String doctorSpeciality) {
        this.doctorSpeciality = doctorSpeciality;
    }

    public String getHealthIssueDescription() {
        return healthIssueDescription;
    }

    public void setHealthIssueDescription(String healthIssueDescription) {
        this.healthIssueDescription = healthIssueDescription;
    }

    public LocalDate getDesiredDate() {
        return desiredDate;
    }

    public void setDesiredDate(LocalDate desiredDate) {
        this.desiredDate = desiredDate;
    }

    public Byte getDesiredStartHours() {
        return desiredStartHours;
    }

    public void setDesiredStartHours(Byte desiredStartHours) {
        this.desiredStartHours = desiredStartHours;
    }

    public Byte getDesiredEndHours() {
        return desiredEndHours;
    }

    public void setDesiredEndHours(Byte desiredEndHours) {
        this.desiredEndHours = desiredEndHours;
    }

    public Boolean getDateFlexible() {
        return dateFlexible;
    }

    public void setDateFlexible(Boolean dateFlexible) {
        this.dateFlexible = dateFlexible;
    }

    public Boolean getHoursFlexible() {
        return hoursFlexible;
    }

    public void setHoursFlexible(Boolean hoursFlexible) {
        this.hoursFlexible = hoursFlexible;
    }

    public AppointmentRequestStatus getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(AppointmentRequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }
}
