package com.portifolio.paymentRest.service;

import org.springframework.stereotype.Service;

import com.portifolio.paymentRest.models.Buyer;
@Service
public interface BuyerService {

	Buyer identifyBuyer(Buyer buyer);

}
