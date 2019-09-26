package com.portifolio.paymentRest.models;

import java.math.BigDecimal;
import java.util.Random;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Entity
public class Boleto extends Payment{

	@NotBlank
	private String boletoNumber;
	
	public Boleto(BigDecimal amount, Client client, Buyer buyer) {
		super(amount, client, buyer);
		
	}

	public String getBoletoNumber() {
		return boletoNumber;
	}

	public void setBoletoNumber(String boletoNumber) {
		this.boletoNumber = boletoNumber;
	}
	
	@Override
	public TypePayment getTypePayment() {
		return TypePayment.BOLETO;
	}
	
	public void processPayment() {
		Random numberGenerator = new Random();
		super.setStatusPayment(StatusPayment.APPROVED);
		this.setBoletoNumber(String.valueOf(numberGenerator.nextInt(Integer.MAX_VALUE)));
	}

	@Override
	public String toString() {
		return "Boleto [boletoNumber=" + boletoNumber + ", Id= " + getId() + ", Amount= " + getAmount()
				+ ", Client= " + getClient() + ", Buyer= " + getBuyer() + ", StatusPayment="
				+ getStatusPayment() + ", " + super.toString();
	}
	
	
	
}
