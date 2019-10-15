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

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.portifolio.paymentRest.models.Buyer;
import com.portifolio.paymentRest.models.Card;
import com.portifolio.paymentRest.models.Client;

public class CardDto {
	
	@NotNull(message = "amount is required")
	@Digits(integer = 20, fraction = 2, message = "numeric value out of limit (<20 integers>, <2 fractioned> expected)")
	@DecimalMin(value = "0.01", message = "must be greater than or equal to 0.01")
	private BigDecimal amount;
	
	@NotNull(message = "customer name is required")
	@NotBlank(message = "customer name is required")
	private String clientName;
	
	@NotNull(message = "buyer name is required")
	@NotBlank(message = "buyer name is required")
	private String buyerName;
	
	@NotNull(message = "E-mail is required")
	@NotBlank(message = "E-mail is required")
	@Email(message = "E-mail is required")
	private String buyerEmail;
	
	@NotNull(message = "cpf is required")
	@CPF(message = "Invalid CPF")
	private String buyerCpf;
	
	@NotNull(message = "card hoder name is required")
	@NotBlank(message = "card hoder name is required")
	private String cardHolderName;
	
	@NotNull(message = "card number is required")
	@CreditCardNumber(ignoreNonDigitCharacters = true, message = "invalid identification code")
	private String cardNumber; ;

	@JsonProperty("expiration_Date")
	@JsonFormat(shape = Shape.STRING, pattern = "dd/MM/yyyy")
	@NotNull(message = "this field is required")
	private LocalDate cardExpirationDate;
	
	@NotNull(message = "cvv must contain 3 numbers")
	@NotBlank(message = "cvv must contain 3 numbers")
	@Size(min = 3, max = 3, message = "cvv must contain 3 numbers")
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
