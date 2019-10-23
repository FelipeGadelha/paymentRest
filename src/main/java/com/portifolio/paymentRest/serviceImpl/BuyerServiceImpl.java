package com.portifolio.paymentRest.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portifolio.paymentRest.models.Buyer;
import com.portifolio.paymentRest.repository.BuyerRepository;
import com.portifolio.paymentRest.service.BuyerService;

@Service
public class BuyerServiceImpl implements BuyerService{

	@Autowired
	private final BuyerRepository buyerRepository;
	
	public BuyerServiceImpl(BuyerRepository buyerRepository) {
	this.buyerRepository = buyerRepository;
	}
	@Override
	public Buyer identifyBuyer(Buyer buyer) {
		Optional<Buyer> buyerId = buyerRepository.findByCpf(buyer.getCpf());
		if(buyerId.isPresent())
			return buyerId.get();
		return buyer;
	}
	
}
