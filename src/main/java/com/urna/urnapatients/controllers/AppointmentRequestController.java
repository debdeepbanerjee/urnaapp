package com.urna.urnapatients.controllers;

import com.urna.urnapatients.dto.AppointmentRequestDto;
import com.urna.urnapatients.dto.AppointmentRequestTimeDto;
import com.urna.urnapatients.exceptions.AccessDeniedException;
import com.urna.urnapatients.exceptions.UrnaException;
import com.urna.urnapatients.models.Appointment;
import com.urna.urnapatients.models.AppointmentRequest;
import com.urna.urnapatients.models.Doctor;
import com.urna.urnapatients.models.Patient;
import com.urna.urnapatients.services.AppointmentRequestService;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/rest/urna/appointment-requests")
public class AppointmentRequestController {
    private final AppointmentRequestService requestService;

    public AppointmentRequestController(AppointmentRequestService requestService) {
        this.requestService = requestService;
    }

    @PostMapping
    @ResponseBody
    public AppointmentRequestDto create(@Valid @RequestBody AppointmentRequestDto appointmentRequest, @ApiIgnore HttpSession session) {
        Patient patient = (Patient) session.getAttribute("patient");
        if(patient == null) {
            throw new AccessDeniedException();
        }
        appointmentRequest.setPatientId(patient.getId());
        return this.requestService.create(appointmentRequest);
    }

    @PostMapping("/approve/{id}")
    @ResponseBody
    public Appointment approve(@PathVariable Long id, @RequestBody AppointmentRequestTimeDto appointmentRequestTimeDto, @ApiIgnore HttpSession session) {
        Doctor doctor = (Doctor) session.getAttribute("doctor");
        if(doctor == null) {
            throw new AccessDeniedException();
        }
        return this.requestService.approve(id, appointmentRequestTimeDto.getRequestTime());
    }

    @PostMapping("/reject/{id}")
    public void reject(@PathVariable Long id, @RequestBody String rejectReason, @ApiIgnore HttpSession session) {
        Doctor doctor = (Doctor) session.getAttribute("doctor");
        if(doctor == null) {
            throw new AccessDeniedException();
        }
        this.requestService.reject(id, rejectReason);
    }

    @GetMapping("/patient")
    public List<AppointmentRequestDto> getAllForPatient(@ApiIgnore HttpSession session) {
        Patient patient = (Patient) session.getAttribute("patient");
        if(patient == null) {
            throw new AccessDeniedException();
        }
        return this.requestService.getAllForPatient(patient.getId());
    }

    @GetMapping("/doctor")
    public List<AppointmentRequestDto> getAllForDoctor(@ApiIgnore HttpSession session) {
        Doctor doctor = (Doctor) session.getAttribute("doctor");
        if(doctor == null) {
            throw new AccessDeniedException();
        }
        return this.requestService.getAllForDoctor(doctor.getId());
    }
}
