package com.urna.urnapatients.services;

import com.urna.urnapatients.controllers.utils.RandomPasswordOtpUtil;
import com.urna.urnapatients.dto.AppointmentRequestDto;
import com.urna.urnapatients.mapper.AppointmentRequestMapper;
import com.urna.urnapatients.models.*;
import com.urna.urnapatients.repo.AppointmentRepository;
import com.urna.urnapatients.repo.AppointmentRequestRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = false )
public class AppointmentRequestService {

    private final AppointmentRequestRepository appointmentRequestRepository;
    private final AppointmentRepository appointmentRepository;

    private final AppointmentRequestMapper appointmentRequestMapper;

    public AppointmentRequestService(AppointmentRequestRepository appointmentRequestRepository, AppointmentRepository appointmentRepository, AppointmentRequestMapper appointmentRequestMapper) {
        this.appointmentRequestRepository = appointmentRequestRepository;
        this.appointmentRepository = appointmentRepository;
        this.appointmentRequestMapper = appointmentRequestMapper;
    }

    public AppointmentRequestDto create(AppointmentRequestDto appointmentRequestDto) {
        AppointmentRequest appointmentRequest = this.appointmentRequestMapper.toEntity(appointmentRequestDto);
        appointmentRequest.setRejectReason(null);
        appointmentRequest.setRequestStatus(AppointmentRequestStatus.REQUESTED);
        return this.appointmentRequestMapper.toDto(this.appointmentRequestRepository.save(appointmentRequest));
    }


    public Appointment approve(Long appointmentRequestId, LocalDateTime scheduleDateTime) {
        AppointmentRequest appointmentRequest = this.appointmentRequestRepository
                .findById(appointmentRequestId)
                .orElseThrow(() -> new RuntimeException("appointment request not found"));
        appointmentRequest.setRequestStatus(AppointmentRequestStatus.APPROVED);
        appointmentRequest = this.appointmentRequestRepository.save(appointmentRequest);
        Appointment appointment = new Appointment();
        Doctor doctor = new Doctor();
        doctor.setId(appointmentRequest.getDoctor().getId());
        appointment.setDoctor(doctor);
        Patient patient = new Patient();
        patient.setId(appointmentRequest.getPatient().getId());
        appointment.setPatient(patient);
        appointment.setScheduledDate(scheduleDateTime);
        appointment.setUniquieKey(RandomPasswordOtpUtil.generateAlphaNumericPassword(10));
        appointment.setAppointmentRequestId(appointmentRequest.getId());
        return this.appointmentRepository.save(appointment);
    }

    public void reject(Long appointmentRequestId, String rejectReason) {
        AppointmentRequest appointmentRequest = this.appointmentRequestRepository
                .findById(appointmentRequestId)
                .orElseThrow(() -> new RuntimeException("appointment request not found"));
        appointmentRequest.setRequestStatus(AppointmentRequestStatus.REJECTED);
        appointmentRequest.setRejectReason(rejectReason);
        this.appointmentRequestRepository.save(appointmentRequest);
    }

    public List<AppointmentRequestDto> getAllForPatient(Long patientId) {
        return this.appointmentRequestMapper.toDto(
                this.appointmentRequestRepository.findByPatientIdAndRequestStatusOrderByCreatedAt(patientId, AppointmentRequestStatus.REQUESTED));
    }

    public List<AppointmentRequestDto> getAllForDoctor(Long doctorId) {
        return this.appointmentRequestMapper.toDto(
                this.appointmentRequestRepository.findByDoctorIdAndRequestStatusOrderByDesiredDate(doctorId, AppointmentRequestStatus.REQUESTED));
    }

}
