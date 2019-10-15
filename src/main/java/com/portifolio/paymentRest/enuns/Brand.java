package com.portifolio.paymentRest.enuns;

public enum Brand {

	MASTERCARD("1"), 
	VISA("2"), 
	AMERICAN_EXPRESS("3"), 
	ELO("4"), 
	DINERS("5"), 
	HIPER("6"), 
	HIPERCARD("7");
	
	private String descricao;
	
	private Brand(String descriao) {
		this.descricao = descriao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
}
