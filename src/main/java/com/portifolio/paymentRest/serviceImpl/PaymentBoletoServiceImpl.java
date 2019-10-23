package com.portifolio.paymentRest.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.portifolio.paymentRest.dto.PaymentBoletoDto;
import com.portifolio.paymentRest.error.ResourceNotFoundException;
import com.portifolio.paymentRest.models.Buyer;
import com.portifolio.paymentRest.models.Client;
import com.portifolio.paymentRest.models.PaymentBoleto;
import com.portifolio.paymentRest.repository.PaymentBoletoRepository;
import com.portifolio.paymentRest.service.PaymentBoletoService;

@Service
public class PaymentBoletoServiceImpl implements PaymentBoletoService{
	
	@Autowired
	private final PaymentBoletoRepository paymentBoletoRepository;
	
	@Autowired
	private final BuyerServiceImpl buyerServiceImpl;
	
	@Autowired
	private final ClientServiceImpl clientServiceImpl;
	
	public PaymentBoletoServiceImpl(PaymentBoletoRepository paymentBoletoRepository, BuyerServiceImpl buyerServiceImpl, ClientServiceImpl clientServiceImpl) {
		this.paymentBoletoRepository = paymentBoletoRepository;
		this.buyerServiceImpl = buyerServiceImpl;
		this.clientServiceImpl = clientServiceImpl;
	}
	@Override
	public PaymentBoleto paymentBoleto(PaymentBoletoDto paymentBoletoDto) {
		PaymentBoleto paymentBoleto = paymentBoletoDto.extractBoleto();
		Buyer identifyBuyer = buyerServiceImpl.identifyBuyer(paymentBoletoDto.extractBuyer());
		Client identifyClient = clientServiceImpl.identifyClient(paymentBoletoDto.extractClient());
		paymentBoleto.setClient(identifyClient);
		paymentBoleto.setBuyer(identifyBuyer);
		PaymentBoleto paymentBoletoResponse = paymentBoletoRepository.save(paymentBoleto);
		return paymentBoletoResponse;
	}
	
	@Override
	public PaymentBoleto findPaymentBoletoById(Long id){
		PaymentBoleto paymentBoleto = paymentBoletoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("payment not found for ID: " + id));
		return paymentBoleto;
	}
		
}
