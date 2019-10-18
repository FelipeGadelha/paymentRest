package com.portifolio.paymentRest.service;

import com.portifolio.paymentRest.dto.PaymentCardDto;
import com.portifolio.paymentRest.models.PaymentCard;

public interface PaymentCardService {

	PaymentCard paymentCard(PaymentCardDto paymentCardDto);
	
	

}
