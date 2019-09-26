package com.portifolio.paymentRest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portifolio.paymentRest.models.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{

	
}
