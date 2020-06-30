package com.urna.urnapatients.repo;

import com.urna.urnapatients.models.MedicalFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicalFileRepository extends JpaRepository<MedicalFile, Long> {
}
