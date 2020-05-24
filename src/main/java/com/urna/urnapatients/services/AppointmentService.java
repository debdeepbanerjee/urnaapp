package com.urna.urnapatients.services;

import com.urna.urnapatients.dto.AppointmentDto;
import com.urna.urnapatients.exceptions.AccessDeniedException;
import com.urna.urnapatients.exceptions.UrnaException;
import com.urna.urnapatients.mapper.AppointmentMapper;
import com.urna.urnapatients.models.Appointment;
import com.urna.urnapatients.models.Consultation;
import com.urna.urnapatients.models.Doctor;
import com.urna.urnapatients.models.Patient;
import com.urna.urnapatients.repo.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final AppointmentMapper appointmentMapper;
    private final ConsultationService consultationService;

    public AppointmentService(AppointmentRepository appointmentRepository, AppointmentMapper appointmentMapper, ConsultationService consultationService) {
        this.appointmentRepository = appointmentRepository;
        this.appointmentMapper = appointmentMapper;
        this.consultationService = consultationService;
    }

    public List<AppointmentDto> pendingAppointmentsForPatient(Long patientId) {
       return this.appointmentMapper.toDto(
                this.appointmentRepository.findByPatientIdAndConsultationIdIsNullOrderByScheduledDate(patientId)
        );
    }

    public List<AppointmentDto> pendingAppointmentsForDoctor(Long doctorId) {
        return this.appointmentMapper.toDto(
                this.appointmentRepository.findByDoctorIdAndConsultationIdIsNullOrderByScheduledDate(doctorId)
        );
    }

    public List<AppointmentDto> completedAppointmentsForPatient(Long patientId) {
        return this.appointmentMapper.toDto(
                this.appointmentRepository.findByPatientIdAndConsultationIdIsNotNullOrderByScheduledDateDesc(patientId)
        );
    }

    public List<AppointmentDto> completedAppointmentsForDoctor(Long doctorId) {
        return this.appointmentMapper.toDto(
                this.appointmentRepository.findByDoctorIdAndConsultationIdIsNotNullOrderByScheduledDateDesc(doctorId)
        );
    }

    public void createConsultation(Long appointmentId, Consultation consultation) {
        Optional<Appointment> appointmentOpt = this.appointmentRepository.findById(appointmentId);
        appointmentOpt.ifPresent(appointment -> {
            Consultation consultation1 = this.consultationService.insert(consultation);
            appointment.setConsultationId(consultation1.getId());
            this.appointmentRepository.save(appointment);
        });
    }

    public Optional<Consultation> getConsultation(Long appointmentId, Patient patient, Doctor doctor) {
        Optional<Appointment> appointmentOpt = this.appointmentRepository.findById(appointmentId);
        if(appointmentOpt.isPresent()) {
            Appointment appointment = appointmentOpt.get();
            Long consultationId = appointment.getConsultationId();
            if(consultationId == null) {
                throw new UrnaException("Consultation not found");
            }
            if((patient != null && appointment.getPatient().getId() == patient.getId()) ||
                    (doctor != null && appointment.getDoctor().getId() == doctor.getId())) {
                Optional<Consultation> consultationOptional = this.consultationService.findById(appointment.getConsultationId());
                return  consultationOptional;
            } else {
                throw new AccessDeniedException();
            }
        }
        throw new UrnaException("Appointment not found");
    }
}
