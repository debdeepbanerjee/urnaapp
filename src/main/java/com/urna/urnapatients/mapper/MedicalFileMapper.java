package com.urna.urnapatients.mapper;

import com.urna.urnapatients.dto.MedicalFileDto;
import com.urna.urnapatients.models.MedicalFile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MedicalFileMapper extends EntityMapper<MedicalFileDto, MedicalFile>{
    default MedicalFile fromId(Long id) {
        if (id == null) {
            return null;
        }
        MedicalFile medicalFile = new MedicalFile();
        medicalFile.setId(id);
        return medicalFile;
    }

    @Mapping(source = "file", target = "file", ignore = true)
    MedicalFileDto toDto(MedicalFile entity);
}
