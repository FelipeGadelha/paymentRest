package com.portifolio.paymentRest.service;

import org.springframework.stereotype.Service;

import com.portifolio.paymentRest.dto.PaymentBoletoDto;
import com.portifolio.paymentRest.models.PaymentBoleto;
@Service
public interface PaymentBoletoService {

	PaymentBoleto paymentBoleto(PaymentBoletoDto paymentBoletoDto);

	PaymentBoleto findPaymentBoletoById(Long id);
	
}
