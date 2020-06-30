package com.urna.urnapatients.controllers;

import com.urna.urnapatients.dto.MedicalFileDto;
import com.urna.urnapatients.exceptions.AccessDeniedException;
import com.urna.urnapatients.models.Doctor;
import com.urna.urnapatients.models.Patient;
import com.urna.urnapatients.services.MedicalRecordService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/rest/urna/medical-files")
public class MedicalFileController {
    private final MedicalRecordService recordService;

    public MedicalFileController(MedicalRecordService recordService) {
        this.recordService = recordService;
    }

    @PostMapping(value = "/appointments/{id}/uploads", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<MedicalFileDto> uploadPatientMedicalRecords(@PathVariable Long id, @RequestPart("files") MultipartFile[] files, @ApiIgnore HttpSession session) {
        if(session.getAttribute("patient") != null) {
            Patient patient = (Patient) session.getAttribute("patient");
            return this.recordService.saveForAppointment(id, patient.getId(), files);
        }
        throw new AccessDeniedException();
    }

    @PostMapping(value = "/consultation/{id}/uploads", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public List<MedicalFileDto> uploadConsultationRecords(@PathVariable Long id, @RequestPart("files") MultipartFile[] files, @ApiIgnore HttpSession session) {
        if(session.getAttribute("patient") != null) {
            Patient patient = (Patient) session.getAttribute("patient");
            return this.recordService.saveForConsultation(id, patient.getId(), null, files);
        }
        if(session.getAttribute("doctor") != null) {
            Doctor doctor = (Doctor) session.getAttribute("doctor");
            return this.recordService.saveForConsultation(id, null, doctor.getId(), files);
        }
        throw new AccessDeniedException();
    }
}
