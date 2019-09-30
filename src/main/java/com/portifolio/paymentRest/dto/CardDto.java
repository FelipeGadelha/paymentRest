package com.portifolio.paymentRest.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.portifolio.paymentRest.models.Buyer;
import com.portifolio.paymentRest.models.Card;
import com.portifolio.paymentRest.models.Client;

public class CardDto {
	
	@NotNull
	@Digits(fraction = 2, integer = 20)
	@DecimalMin(value = "0.01")
	private BigDecimal amount;
	
	@NotNull(message = "customer name is required")
	@NotBlank
	private String clientName;
	@NotNull(message = "buyer name is required")
	@NotBlank
	private String buyerName;
	@Email(message = "E-mail is required")
	private String buyerEmail;
	@NotNull(message = "cpf is required")
	@CPF
	private String buyerCpf;
	@NotNull(message = "card hoder name is required")
	@NotBlank
	private String cardHolderName;
	@NotNull(message = "credit card number is required")
	@CreditCardNumber
	private String cardNumber; ;
//	@JsonDeserialize(using = LocalDateDeserializer.class)  
//	@JsonSerialize(using = LocalDateSerializer.class) 
//	@DateTimeFormat(iso = ISO.DATE)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate cardExpirationDate;
	
	@NotNull(message = "cvv is required")
	@Size(min = 3, max = 3, message = "must contain 3 numbers")
	private String cardCvv;
	
	public CardDto(BigDecimal amount,
			String clientName,
			String buyerName,
			String buyerEmail,
			String buyerCpf,
			String cardHolderName,
			String cardNumber,
			LocalDate cardExpirationDate,
			String cardCvv) {
		super();
		this.amount = amount;
		this.clientName = clientName;
		this.buyerName = buyerName;
		this.buyerEmail = buyerEmail;
		this.buyerCpf = buyerCpf;
		this.cardHolderName = cardHolderName;
		this.cardNumber = cardNumber;
		this.cardExpirationDate = cardExpirationDate;
		this.cardCvv = cardCvv;
	}

	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getClientName() {
		return clientName;
	}
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}
	public String getBuyerName() {
		return buyerName;
	}
	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}
	public String getBuyerEmail() {
		return buyerEmail;
	}
	public void setBuyerEmail(String buyerEmail) {
		this.buyerEmail = buyerEmail;
	}
	public String getBuyerCpf() {
		return buyerCpf;
	}
	public void setBuyerCpf(String buyerCpf) {
		this.buyerCpf = buyerCpf;
	}
	public String getCardHolderName() {
		return cardHolderName;
	}
	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public LocalDate getCardExpirationDate() {
		return cardExpirationDate;
	}
	public void setCardExpirationDate(LocalDate cardExpirationDate) {
		this.cardExpirationDate = cardExpirationDate;
	}
	public String getCardCvv() {
		return cardCvv;
	}
	public void setCardCvv(String cardCvv) {
		this.cardCvv = cardCvv;
	}
	
	public Buyer extractBuyer(){
		return new Buyer(getBuyerName(), 
				getBuyerEmail(), 
				getBuyerCpf());
	}
	public Client extractClient() {
		return new Client(getClientName());
	}
	public Card extractCard() {
		return new Card(getAmount(), 
				extractClient(), 
				extractBuyer(), 
				getCardHolderName(), 
				getCardNumber(), 
				getCardExpirationDate(), 
				getCardCvv());
	}
}
