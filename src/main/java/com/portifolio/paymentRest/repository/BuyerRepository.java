package com.portifolio.paymentRest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.portifolio.paymentRest.models.Buyer;

@Repository
public interface BuyerRepository extends JpaRepository<Buyer, Long>{

	Optional<Buyer> findByCpf(String buyerCpf);
}
