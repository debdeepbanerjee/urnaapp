package com.urna.urnapatients.dto;


import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class AppointmentDto implements Serializable {
    private Long id ;

    private Long consultationId;

    private String uniquieKey;

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

    private Timestamp apptEndTime;

    private Long apptEndTimeN;

    private Long appointmentRequestId;

    private LocalDateTime scheduledDate;

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
