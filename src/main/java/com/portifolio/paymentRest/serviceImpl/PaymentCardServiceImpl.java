package com.portifolio.paymentRest.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portifolio.paymentRest.dto.PaymentCardDto;
import com.portifolio.paymentRest.error.ResourceNotFoundException;
import com.portifolio.paymentRest.models.Buyer;
import com.portifolio.paymentRest.models.Client;
import com.portifolio.paymentRest.models.PaymentCard;
import com.portifolio.paymentRest.repository.PaymentCardRepository;
import com.portifolio.paymentRest.service.PaymentCardService;

@Service
public class PaymentCardServiceImpl implements PaymentCardService{
	
	@Autowired
	private final PaymentCardRepository cardRepository;
	
	@Autowired
	private final BuyerServiceImpl buyerService;
	
	@Autowired
	private final ClientServiceImpl clientService;
	
	public PaymentCardServiceImpl(PaymentCardRepository cardRepository, BuyerServiceImpl buyerService, ClientServiceImpl clientService) {
		this.cardRepository = cardRepository;
		this.buyerService = buyerService;
		this.clientService = clientService;
	}
	
	@Override
	public PaymentCard paymentCard(PaymentCardDto paymentCardDto) {
		PaymentCard paymentCard = paymentCardDto.extractCard();
		Buyer identifyBuyer = buyerService.identifyBuyer(paymentCardDto.extractBuyer());
		Client identifyClient = clientService.identifyClient(paymentCardDto.extractClient());
		paymentCard.setBuyer(identifyBuyer);
		paymentCard.setClient(identifyClient);
		PaymentCard paymentCardResponse = cardRepository.save(paymentCard);
		return paymentCardResponse;
	}

	@Override
	public PaymentCard findPaymentCardById(Long id){
		PaymentCard paymentCard = cardRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("payment not found for ID: " + id));
		return paymentCard;
	}
	
	
	

}
