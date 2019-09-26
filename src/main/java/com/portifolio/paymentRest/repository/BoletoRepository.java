package com.portifolio.paymentRest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portifolio.paymentRest.models.Boleto;

@Repository
public interface BoletoRepository extends JpaRepository<Boleto, Long> {

	
}
