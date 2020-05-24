package com.urna.urnapatients.mapper;

import com.urna.urnapatients.dto.SearchDoctorDto;
import com.urna.urnapatients.models.Doctor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class SearchDoctorMapper implements EntityMapper<SearchDoctorDto, Doctor> {
}
