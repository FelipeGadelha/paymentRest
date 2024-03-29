package com.portifolio.paymentRest.models;

import java.math.BigDecimal;
import java.util.Random;

import javax.persistence.Entity;

import com.portifolio.paymentRest.enuns.TypePayment;

@Entity
public class PaymentBoleto extends Payment {

	private String boletoNumber;

	public PaymentBoleto() {

	}
	public PaymentBoleto(BigDecimal amount, Client client, Buyer buyer) {
		super(amount, client, buyer, TypePayment.BOLETO);
		numberGenerator();
	}

	public String getBoletoNumber() {
		return boletoNumber;
	}
	public void setBoletoNumber(String boletoNumber) {
		this.boletoNumber = boletoNumber;
	}

	public void numberGenerator() {
		Random numberGenerator = new Random();
		this.setBoletoNumber(String.valueOf(numberGenerator.nextInt(Integer.MAX_VALUE)));
	}

	@Override
	public String toString() {
		return "Boleto [boletoNumber=" + boletoNumber + ", Id= " + getId() + ", Amount= " + getAmount() + ", Client= "
				+ getClient() + ", Buyer= " + getBuyer() + ", "
				+ super.toString();
	}
	

}
