package com.portifolio.paymentRest.service;

import org.springframework.stereotype.Service;

import com.portifolio.paymentRest.dto.PaymentCardDto;
import com.portifolio.paymentRest.models.PaymentCard;
@Service
public interface PaymentCardService {

	PaymentCard paymentCard(PaymentCardDto paymentCardDto);

	PaymentCard findPaymentCardById(Long id);

	
	
	

}
