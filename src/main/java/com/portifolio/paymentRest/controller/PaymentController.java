package com.portifolio.paymentRest.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portifolio.paymentRest.dto.PaymentBoletoDto;
import com.portifolio.paymentRest.dto.PaymentCardDto;
import com.portifolio.paymentRest.models.PaymentBoleto;
import com.portifolio.paymentRest.models.PaymentCard;
import com.portifolio.paymentRest.serviceImpl.PaymentBoletoServiceImpl;
import com.portifolio.paymentRest.serviceImpl.PaymentCardServiceImpl;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/v1/payment")
public class PaymentController {

	@Autowired
	private final PaymentCardServiceImpl paymentCardServiceImpl;
	
	@Autowired
	private final PaymentBoletoServiceImpl paymentBoletoServiceImpl;
	
	public PaymentController(PaymentCardServiceImpl paymentCardServiceImpl, PaymentBoletoServiceImpl boletoServiceImpl) {
		this.paymentCardServiceImpl = paymentCardServiceImpl;
		this.paymentBoletoServiceImpl = boletoServiceImpl;
	}
	
	@GetMapping(path = "/creditCard/{id}")
	@ApiOperation(value = "Return type payment credit-card based on id", response = PaymentCard.class)
	public ResponseEntity<?> findPaymentCreditCardById(@PathVariable Long id) {
		PaymentCard paymentCard = paymentCardServiceImpl.findPaymentCardById(id);
		return new ResponseEntity<>(paymentCard, HttpStatus.OK);
	}
	@GetMapping(path = "/boleto/{id}")
	@ApiOperation(value = "Return type payment boleto based on id", response = PaymentBoleto.class)
	public ResponseEntity<?> findPaymentBoletoById(@PathVariable Long id){
		PaymentBoleto paymentBoleto = paymentBoletoServiceImpl.findPaymentBoletoById(id);
		return new ResponseEntity<>(paymentBoleto, HttpStatus.OK);
		
	}
	
	@PostMapping(path = "/creditCard")
	public ResponseEntity<?> paymentByCreditCard(@Valid @RequestBody PaymentCardDto paymentCardDto){		
		PaymentCard paymentCardResponse = paymentCardServiceImpl.paymentCard(paymentCardDto);
		return new ResponseEntity<>(paymentCardResponse, HttpStatus.CREATED);
	}
	
	@PostMapping(path = "/boleto")
	public ResponseEntity<?> paymentByBoleto(@Valid @RequestBody PaymentBoletoDto paymentBoletoDto) {
		PaymentBoleto paymentBoletoResponse = paymentBoletoServiceImpl.paymentBoleto(paymentBoletoDto);
		return new ResponseEntity<>(paymentBoletoResponse, HttpStatus.CREATED);
	}		
}















