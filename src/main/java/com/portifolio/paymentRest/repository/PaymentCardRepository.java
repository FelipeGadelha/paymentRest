package com.portifolio.paymentRest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portifolio.paymentRest.models.PaymentCard;

@Repository
public interface PaymentCardRepository extends JpaRepository<PaymentCard, Long>{

	
	
}
