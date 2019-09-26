package com.portifolio.paymentRest.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.portifolio.paymentRest.models.Buyer;
import com.portifolio.paymentRest.models.Card;
import com.portifolio.paymentRest.models.Client;

public class CardDto {
	
	@NotNull
	@Digits(fraction = 2, integer = 20)
	@DecimalMin(value = "0.01")
	private BigDecimal amount;
	
	private String clientName;
	
	private String buyerName;
	
	private String buyerEmail;
	
	private String buyerCpf;
	
	private String cardHolderName;
	private String cardNumber;
	@JsonFormat(pattern="dd/MM/yyyy")
	private LocalDate cardExpirationDate;
	private String cardCvv;
	
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
