package com.portifolio.paymentRest.models;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;

@Entity
public class Card extends Payment{

	private String cardHolderName;
	private String cardNumber;
	private LocalDate cardExpirationDate;
	private String cardCvv;	
	
	public Card(BigDecimal amount, Client client, Buyer buyer, String cardHolderName, String cardNumber, LocalDate cardExpirationDate, String cardCvv) {
		super(amount, client, buyer);
		this.cardHolderName = cardHolderName;
		this.cardNumber = cardNumber;
		this.cardExpirationDate = cardExpirationDate;
		this.cardCvv = cardCvv;
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
	@Override
	public TypePayment getTypePayment() {
		return TypePayment.CREDIT_CARD;
	}
	public void processPayment() {
		super.setStatusPayment(StatusPayment.APPROVED);
		
	}
	@Override
	public String toString() {
		return "Card [cardHolderName=" + cardHolderName + ", cardNumber=" + cardNumber + ", cardExpirationDate="
				+ cardExpirationDate + ", cardCvv=" + cardCvv + ", getId()=" + getId() + ", getAmount()=" + getAmount()
				+ ", getClient()=" + getClient() + ", getBuyer()=" + getBuyer() + ", getStatusPayment()="
				+ getStatusPayment() + ", toString()=" + super.toString() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + "]";
	}

}
