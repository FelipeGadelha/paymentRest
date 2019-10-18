package com.portifolio.paymentRest.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portifolio.paymentRest.dto.PaymentBoletoDto;
import com.portifolio.paymentRest.models.PaymentBoleto;
import com.portifolio.paymentRest.repository.BoletoRepository;
import com.portifolio.paymentRest.service.PaymentBoletoService;

@Service
public class PaymentBoletoServiceImpl implements PaymentBoletoService{
	
	@Autowired
	private final BoletoRepository boletoRepository;
	
	public PaymentBoletoServiceImpl(BoletoRepository boletoRepository) {
		this.boletoRepository = boletoRepository;
	}
	@Override
	public PaymentBoleto paymentBoleto(PaymentBoletoDto paymentBoletoDto) {
		PaymentBoleto paymentBoleto = paymentBoletoDto.extractBoleto();
		processPayment(paymentBoleto);
		PaymentBoleto paymentBoletoResponse = boletoRepository.save(paymentBoleto);
		return paymentBoletoResponse;
	}
	private void processPayment(PaymentBoleto paymentBoleto) {
		
		
	}
	
	
}
