package com.portifolio.paymentRest.dto;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import com.portifolio.paymentRest.models.Boleto;
import com.portifolio.paymentRest.models.Buyer;
import com.portifolio.paymentRest.models.Client;

public class BoletoDto {
	
	@NotNull
	@Digits(fraction = 2, integer = 20)
	@DecimalMin(value = "0.01")
	private BigDecimal amount;
	
	private String clientName;
	
	private String buyerName;
	
	private String buyerEmail;
	
	private String buyerCpf;

	public BoletoDto(BigDecimal amount, String clientName, String buyerName, String buyerEmail, String buyerCpf) {
		this.amount = amount;
		this.clientName = clientName;
		this.buyerName = buyerName;
		this.buyerEmail = buyerEmail;
		this.buyerCpf = buyerCpf;
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
	
	public Buyer extractBuyer(){
		return new Buyer(getBuyerName(), 
				getBuyerEmail(), 
				getBuyerCpf());
	}
	public Client extractClient() {
		return new Client(getClientName());
	}
	
	public Boleto extractBoleto() {
		return new Boleto(getAmount(), extractClient(), extractBuyer());
	}
	
}
