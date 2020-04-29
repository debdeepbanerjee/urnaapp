package com.urna.urnapatients.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.urna.urnapatients.models.Appointment;

public interface AppointmentRepository extends CrudRepository <Appointment,Long>{
	@Query("SELECT a FROM Appointment a where a.uniquieKey=?1")
	public Iterable<Appointment> getAppointmentByUniquieKey(String uniquieKey);
	
	@Query("SELECT a FROM Appointment a where a.uniquieKey=?1 and a.id=?2")
	public Iterable<Appointment> getAppointmentByUniquieKeyAndId(String uniquieKey,Long id);
}
