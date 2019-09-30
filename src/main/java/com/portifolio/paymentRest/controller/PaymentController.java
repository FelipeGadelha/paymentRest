package com.portifolio.paymentRest.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portifolio.paymentRest.dto.BoletoDto;
import com.portifolio.paymentRest.dto.CardDto;
import com.portifolio.paymentRest.models.Boleto;
import com.portifolio.paymentRest.models.Card;
import com.portifolio.paymentRest.repository.BoletoRepository;
import com.portifolio.paymentRest.repository.BuyerRepository;
import com.portifolio.paymentRest.repository.CardRepository;
import com.portifolio.paymentRest.repository.ClientRepository;

@RestController
@RequestMapping("/v1")
public class PaymentController {

	@Autowired
	private final BoletoRepository boletoRepository;
	@Autowired
	private final CardRepository cardRepository;
	@Autowired
	private final BuyerRepository buyerRepository;
	@Autowired
	private final ClientRepository clientRepository;
	
	public PaymentController(BoletoRepository boletoRepository, CardRepository cardRepository, BuyerRepository buyerRepository, ClientRepository clientRepository) {
		this.boletoRepository = boletoRepository;
		this.cardRepository = cardRepository;
		this.buyerRepository = buyerRepository;
		this.clientRepository = clientRepository;
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<?> findPaymentById(){
		
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PostMapping(path = "/creditCard", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<?> paymentByCreditCard(@Valid @RequestBody CardDto cardDto) {
		buyerRepository.save(cardDto.extractBuyer());
		clientRepository.save(cardDto.extractClient());
		Card card = cardDto.extractCard();
		card.processPayment();
		cardRepository.save(card);
		return new ResponseEntity<>(cardRepository.save(card), HttpStatus.CREATED);
	}
	
	@PostMapping(path = "/boleto")
	public ResponseEntity<?> paymentByBoleto(@Valid @RequestBody BoletoDto boletoDto) {
		clientRepository.save(boletoDto.extractClient());
		buyerRepository.save(boletoDto.extractBuyer());
		Boleto boleto = boletoDto.extractBoleto();
		boleto.processPayment();
		
		boletoRepository.save(boleto);
		
		return new ResponseEntity<>(boletoRepository.save(boleto), HttpStatus.CREATED);
	}
		
}















