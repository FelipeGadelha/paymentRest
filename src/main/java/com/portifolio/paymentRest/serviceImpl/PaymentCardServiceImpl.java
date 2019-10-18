package com.portifolio.paymentRest.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portifolio.paymentRest.dto.PaymentCardDto;
import com.portifolio.paymentRest.models.PaymentCard;
import com.portifolio.paymentRest.repository.CardRepository;
import com.portifolio.paymentRest.service.PaymentCardService;

@Service
public class PaymentCardServiceImpl implements PaymentCardService{
	
	@Autowired
	private final CardRepository cardRepository;	
	
	public PaymentCardServiceImpl(CardRepository cardRepository) {
		this.cardRepository = cardRepository;
	}
	
	@Override
	public PaymentCard paymentCard(PaymentCardDto paymentCardDto) {
		PaymentCard paymentCard = paymentCardDto.extractCard();
		processPayment(paymentCard);
		PaymentCard paymentCardResponse = cardRepository.save(paymentCard);
		return paymentCardResponse;
	}

	private void processPayment(PaymentCard paymentCard) {
//		if (!paymentCard.getCardExpirationDate().isAfter(LocalDate.now())) {
//			paymentCard.setStatusPayment(StatusPayment.CANCELLED);	
//		}else {
//			paymentCard.setStatusPayment(StatusPayment.CREATED);			
//		}

	}
	

}
