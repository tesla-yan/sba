package com.sba.payment.repo;

import java.util.Date;

import com.sba.payment.entity.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PaymentRepository extends JpaRepository<Payment, Long>, PaymentRepositoryCustom {

	@Query(nativeQuery = true, value = "SELECT * FROM payment WHERE mentorId=?1 AND createdDate BETWEEN ?2 AND ?3")
	Page<Payment> findPaymentDtlsByMentorid(Long mentorId, Date startDate, Date endDate, Pageable pageable);

	@Query(nativeQuery = true, value = "SELECT * FROM payment WHERE createdDate BETWEEN ?1 AND ?2")
	Page<Payment> findPaymentDtlsByDateRange(Date startDate, Date endDate, Pageable pageable);
	
}
