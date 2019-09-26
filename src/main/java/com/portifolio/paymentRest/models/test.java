package com.portifolio.paymentRest.models;

import java.math.BigDecimal;

public class test {

	public static void main(String[] args) {
		Buyer buyer = new Buyer("felipe", "felipe@gadelha.com", "234234");
		Boleto boleto = new Boleto(new BigDecimal(10), new Client("isabella"), buyer);
		
		
		System.out.println(boleto.toString());
		boleto.processPayment();
		
		System.out.println(boleto.getStatusPayment());
		System.out.println(boleto.getBoletoNumber());
	}

}
