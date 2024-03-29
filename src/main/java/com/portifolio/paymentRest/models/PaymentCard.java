package com.portifolio.paymentRest.models;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;

import com.portifolio.paymentRest.enuns.StatusPayment;
import com.portifolio.paymentRest.enuns.TypePayment;

@Entity
public class PaymentCard extends Payment{

	private static final long serialVersionUID = 1L;
	
	private String cardHolderName;
	private String cardNumber;
	private LocalDate cardExpirationDate;
	private String cardCvv;	
	
	public PaymentCard() {
	}
	
	public PaymentCard(BigDecimal amount, Client client, Buyer buyer, String cardHolderName, String cardNumber, LocalDate cardExpirationDate, String cardCvv) {
		super(amount, client, buyer, TypePayment.CREDIT_CARD);
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

	@Override
	public String toString() {
		return "Card [cardHolderName=" + cardHolderName + ", cardNumber=" + cardNumber + ", cardExpirationDate="
				+ cardExpirationDate + ", cardCvv=" + cardCvv + ", getId()=" + getId() + ", getAmount()=" + getAmount()
				+ ", getClient()=" + getClient() + ", getBuyer()=" + getBuyer() + ", toString()=" + super.toString() + 
				", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + "]";
	}
	
}
