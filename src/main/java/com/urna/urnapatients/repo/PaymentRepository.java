package com.urna.urnapatients.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.urna.urnapatients.models.Payment;

public interface PaymentRepository extends CrudRepository<Payment, Long>  {

	@Query("SELECT p FROM Payment p where p.patientId=?1")
	public Iterable<Payment> findAllPaymentsByPatientId(Integer patientId);
	
	@Query("SELECT p FROM Payment p where p.patientId=?1 and p.paymentActive=?2")
	public Iterable<Payment> findAllActivePaymentsByPatientId(Integer patientId,Boolean paymentActive);
	
	@Query("SELECT p FROM Payment p where p.doctorId=?1")
	public Iterable<Payment> findAllPaymentsByDoctorId(Integer doctorId);
	
	
	
}
