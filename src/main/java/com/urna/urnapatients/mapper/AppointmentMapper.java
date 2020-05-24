package com.urna.urnapatients.mapper;

import com.urna.urnapatients.dto.AppointmentDto;
import com.urna.urnapatients.models.Appointment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public abstract class AppointmentMapper implements EntityMapper<AppointmentDto, Appointment> {
    @Mappings({
            @Mapping(target = "doctorId", source = "doctor.id"),
            @Mapping(target = "doctorFirstName", source = "doctor.firstName"),
            @Mapping(target = "doctorLastName", source = "doctor.lastName"),
            @Mapping(target = "doctorMiddleName", source = "doctor.middleName"),
            @Mapping(target = "doctorQualifications", source = "doctor.qualifications"),
            @Mapping(target = "doctorSpeciality", source = "doctor.speciality"),
            @Mapping(target = "patientId", source = "patient.id"),
            @Mapping(target = "patientFirstName", source = "patient.firstName"),
            @Mapping(target = "patientLastName", source = "patient.lastName"),
            @Mapping(target = "patientMiddleName", source = "patient.middleName"),
    })
    public abstract AppointmentDto toDto(Appointment appointment);

    @Mappings({
            @Mapping(source = "doctorId", target = "doctor.id"),
            @Mapping(source = "patientId", target = "patient.id")
    })
    public abstract Appointment toEntity(AppointmentDto appointmentDto);
}
