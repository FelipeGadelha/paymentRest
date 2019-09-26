package com.portifolio.paymentRest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portifolio.paymentRest.models.Card;

@Repository
public interface CardRepository extends JpaRepository<Card, Long>{

	
	
}
