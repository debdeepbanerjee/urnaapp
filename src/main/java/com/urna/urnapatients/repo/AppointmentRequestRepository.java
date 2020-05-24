package com.urna.urnapatients.repo;

import com.urna.urnapatients.models.AppointmentRequest;
import com.urna.urnapatients.models.AppointmentRequestStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRequestRepository extends JpaRepository<AppointmentRequest, Long> {
    List<AppointmentRequest> findByPatientIdAndRequestStatusOrderByCreatedAt(Long patientId, AppointmentRequestStatus requestStatus);
    List<AppointmentRequest> findByDoctorIdAndRequestStatusOrderByDesiredDate(Long doctorId, AppointmentRequestStatus requestStatus);
}
