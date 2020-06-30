package com.urna.urnapatients.services;

import com.urna.urnapatients.dto.MedicalFileDto;
import com.urna.urnapatients.exceptions.UrnaException;
import com.urna.urnapatients.mapper.MedicalFileMapper;
import com.urna.urnapatients.models.Appointment;
import com.urna.urnapatients.models.Consultation;
import com.urna.urnapatients.models.MedicalFile;
import com.urna.urnapatients.repo.AppointmentRepository;
import com.urna.urnapatients.repo.ConsultationRepository;
import com.urna.urnapatients.repo.MedicalFileRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class MedicalRecordService {
    private final AppointmentRepository appointmentRepository;
    private final MedicalFileRepository medicalFileRepository;
    private final MedicalFileMapper medicalFileMapper;
    private final ConsultationRepository consultationRepository;

    public MedicalRecordService(AppointmentRepository appointmentRepository, MedicalFileRepository medicalFileRepository, MedicalFileMapper medicalFileMapper, ConsultationRepository consultationRepository) {
        this.appointmentRepository = appointmentRepository;
        this.medicalFileRepository = medicalFileRepository;
        this.medicalFileMapper = medicalFileMapper;
        this.consultationRepository = consultationRepository;
    }

    public List<MedicalFileDto> saveForAppointment(Long appointmentId, Long patientId, MultipartFile[] files) {
        if(files == null || files.length < 1) {
            throw new UrnaException("No files to save");
        }
        Appointment appointment = this.appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new UrnaException("Appointment not found"));

        if(appointment.getPatient().getId().longValue() != patientId ) {
            throw new UrnaException("Invalid Appointment. Patient mismatch");
        }
        return this.saveFiles(appointmentId, null, patientId, null, files);
    }

    public List<MedicalFileDto> saveForConsultation(Long consultationId, Long patientId, Long doctorId, MultipartFile[] files) {
        if(files == null || files.length < 1) {
            throw new UrnaException("No files to save");
        }
        if(patientId != null && doctorId != null) {
            throw new UrnaException("Both doctor and patient cannot upload a file");
        }
        if(patientId == null && doctorId == null) {
            throw new UrnaException("Either doctor or patient should upload a file");
        }
        Appointment appointment = this.appointmentRepository.findByConsultationId(consultationId);
        if(appointment == null) {
            throw new UrnaException("Invalid Consultation Id");
        }
        if(patientId != null && appointment.getPatient().getId().longValue() != patientId ) {
            throw new UrnaException("Invalid Consultation Id, patient id mismatch");
        }
        if(doctorId != null && appointment.getDoctor().getId().longValue() != doctorId ) {
            throw new UrnaException("Invalid Consultation Id, doctor id mismatch");
        }
        this.consultationRepository.findById(consultationId)
                .orElseThrow(() -> new UrnaException("Consultation not found"));

        return this.saveFiles(appointment.getId(), consultationId, patientId, doctorId, files);

    }

    private List<MedicalFileDto> saveFiles(Long appointmentId, Long consultationId, Long patientId,
                                           Long doctorId, MultipartFile[] files) {
        Set<MedicalFile> medicalFiles = new HashSet<>();
        for (MultipartFile file: files) {
            MedicalFile medicalFile = new MedicalFile();
            medicalFile.setAppointmentId(appointmentId);
            medicalFile.setPatientId(patientId);
            medicalFile.setDoctorId(doctorId);
            medicalFile.setConsultationId(consultationId);
            try {
                medicalFile.setFile(file.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
            medicalFile.setFileName(file.getOriginalFilename());
            medicalFile.setFileType(file.getContentType());
            medicalFiles.add(medicalFile);
        }
        return this.medicalFileMapper.toDto(this.medicalFileRepository.saveAll(medicalFiles));
    }
}
