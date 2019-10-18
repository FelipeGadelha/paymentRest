package com.portifolio.paymentRest.service;

import com.portifolio.paymentRest.dto.PaymentBoletoDto;
import com.portifolio.paymentRest.models.PaymentBoleto;

public interface PaymentBoletoService {

	PaymentBoleto paymentBoleto(PaymentBoletoDto paymentBoletoDto);
	
}
